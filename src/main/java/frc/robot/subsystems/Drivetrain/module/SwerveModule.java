package frc.robot.subsystems.Drivetrain.module;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.hardware.Factory.MotorFactory;
import frc.robot.hardware.interfaces.GenericEncoder;
import frc.robot.hardware.interfaces.GenericMotor;
import frc.robot.subsystems.Drivetrain.DrivetrainConstants;

public class SwerveModule {
    private final GenericMotor driveMotor;
    private final GenericMotor turningMotor;

    private final GenericEncoder driveEncoder;
    private final GenericEncoder turningEncoder;

    private SwerveModuleState desiredState;

    public SwerveModule(int driveMotorID, int turningMotorID, double angleOffset) {
        driveMotor = MotorFactory.createMotor(driveMotorID, DrivetrainConstants.driveMotorModel);
        turningMotor = MotorFactory.createMotor(turningMotorID, DrivetrainConstants.turningMotorModel);

        driveEncoder = driveMotor.getEncoder();
        turningEncoder = turningMotor.getAbsoluteEncoder();

        driveMotor.configure(DrivetrainConfig.getDriveMotorConfig());
        turningMotor.configure(DrivetrainConfig.getTurningMotorConfig(angleOffset));

        desiredState = new SwerveModuleState();
        desiredState.angle = new Rotation2d(turningEncoder.getPosition());
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(
            driveEncoder.getVelocity(),
            new Rotation2d(turningEncoder.getPosition())
        );
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
            driveEncoder.getPosition(),
            new Rotation2d(turningEncoder.getPosition())
        );
    }

    public void resetEncoder() {
        driveEncoder.setPosition(0);
    }

    public void setDesiredState(SwerveModuleState desiredState) {
        desiredState.optimize(new Rotation2d(turningEncoder.getPosition()));

        driveMotor.setVelocity(desiredState.speedMetersPerSecond);
        turningMotor.setPosition(desiredState.angle.getRadians());

        this.desiredState = desiredState;
    }

    public void stop() {
        driveMotor.stop();
        turningMotor.stop();
    }
}