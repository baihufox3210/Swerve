package frc.robot.subsystems.Drivetrain;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;

import com.revrobotics.spark.FeedbackSensor;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import frc.robot.hardware.Factory.GyroFactory.GyroModel;
import frc.robot.hardware.Factory.MotorFactory.MotorModel;

public class DrivetrainConstants {
    public static final MotorModel driveMotorType = MotorModel.SPARKMAX;
    public static final MotorModel turningMotorType = MotorModel.SPARKMAX;

    public static final GyroModel gyroType = GyroModel.PIGEON;

    public static final int[] driveMotorIDs = {11, 12, 13, 14};
    public static final int[] turningMotorIDs = {21, 22, 23, 24};

    public static final double TrackWidth = 0.635;
    public static final double WheelBase = 0.635;

    public static final int gyroID = 1;

    public static final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        new Translation2d(WheelBase / 2, TrackWidth / 2),
        new Translation2d(WheelBase / 2, -TrackWidth / 2),
        new Translation2d(-WheelBase / 2, TrackWidth / 2),
        new Translation2d(-WheelBase / 2, -TrackWidth / 2)
    );

    public static final Pose2d InitialPose = new Pose2d(0, 0, Rotation2d.kZero);

    public static final double[] AngleOffsets = {
        0.1521126,
        0.0067503,
        0.3277588,
        0.1364698
    };

    public static final class DriveMotorConfig {
        public static final double GearRatio = 5.08;

        public static final double maxSpeedMetersPerSecond = 5.0;

        public static final double WheelCircumference = Inches.of(3).times(Math.PI).in(Meters);

        public static final double PositionConversionFactor = 1 / GearRatio * WheelCircumference;
        public static final double VelocityConversionFactor = PositionConversionFactor / 60.0;

        public static final double kP = 0.15;
        public static final double kI = 0.0;
        public static final double kD = 0.01;

        public static final double kS = 0.0;
        public static final double kV = 0.0;
        public static final double kA = 0.0;
    }

    public static final class TurningMotorConfig {
        public static final int SupplyCurrentLimit = 20;

        public static final boolean EncoderInverted = true;

        public static final double GearRatio = 25;

        public static final double maxAngularSpeedRadiansPerSecond = 20.0;

        public static final double PositionConversionFactor = 2 * Math.PI;
        public static final double VelocityConversionFactor = PositionConversionFactor / 60.0;

        public static final double PositionWrapMin = 0;
        public static final double PositionWrapMax = 2 * Math.PI;

        public static final FeedbackSensor FeedbackSensorType = FeedbackSensor.kAbsoluteEncoder;

        public static final double kP = 1.0;
        public static final double kI = 0.0;
        public static final double kD = 0.1;

        public static final double kS = 0.0;
        public static final double kV = 0.0;
        public static final double kA = 0.0;
    }
}