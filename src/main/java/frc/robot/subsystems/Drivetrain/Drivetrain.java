package frc.robot.subsystems.Drivetrain;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.hardware.Factory.GyroFactory;
import frc.robot.hardware.interfaces.GenericGyro;
import frc.robot.subsystems.Drivetrain.DrivetrainConstants.DriveMotorConfig;
import frc.robot.subsystems.Drivetrain.DrivetrainConstants.TurningMotorConfig;
import frc.robot.subsystems.Drivetrain.module.SwerveModule;

public class Drivetrain extends SubsystemBase {
    private static Drivetrain instance;

    private final GenericGyro gyro;
    private final SwerveModule[] swerveModules;
    private final SwerveDrivePoseEstimator poseEstimator;

    private Drivetrain() {
        gyro = GyroFactory.createGyro(DrivetrainConstants.gyroModel, DrivetrainConstants.gyroID);

        swerveModules = new SwerveModule[4];
        for (int i = 0; i < 4; i++) {
            swerveModules[i] = new SwerveModule(
                DrivetrainConstants.driveMotorIDs[i],
                DrivetrainConstants.turningMotorIDs[i],
                DrivetrainConstants.AngleOffsets[i]
            );
        }

        poseEstimator = new SwerveDrivePoseEstimator(
            DrivetrainConstants.kinematics,
            gyro.getRotation2d(),
            getModulePositions(),
            DrivetrainConstants.InitialPose
        );

        gyro.reset();
    }

    @Override
    public void periodic() {
        poseEstimator.update(
            gyro.getRotation2d(),
            getModulePositions()
        );
    }

    private SwerveModulePosition[] getModulePositions() {
        SwerveModulePosition[] positions = new SwerveModulePosition[4];
        for (int i = 0; i < 4; i++) positions[i] = swerveModules[i].getPosition();
        return positions;
    }

    public void drive(double xSpeed, double ySpeed, double rot) {
        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            xSpeed * DriveMotorConfig.maxSpeedMetersPerSecond,
            ySpeed * DriveMotorConfig.maxSpeedMetersPerSecond,
            rot * TurningMotorConfig.maxAngularSpeedRadiansPerSecond,
            gyro.getRotation2d()
        );

        drive(chassisSpeeds);
    }

    private void drive(ChassisSpeeds chassisSpeeds) {
        ChassisSpeeds discretizedSpeeds = ChassisSpeeds.discretize(chassisSpeeds, 0.02);
        
        var swerveModuleStates = DrivetrainConstants.kinematics.toSwerveModuleStates(discretizedSpeeds);
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, DriveMotorConfig.maxSpeedMetersPerSecond);

        setSwerveModuleStates(swerveModuleStates);
    }

    private void setSwerveModuleStates(SwerveModuleState[] desiredStates) {
        for (int i = 0; i < 4; i++) swerveModules[i].setDesiredState(desiredStates[i]);
    }

    public void stop() {
        for (SwerveModule module : swerveModules) module.stop();
    }

    public static Drivetrain getInstance() {
        if (instance == null) instance = new Drivetrain();
        return instance;
    }
}
