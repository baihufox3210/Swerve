package frc.robot.subsystems.Drivetrain.module;

import frc.robot.hardware.config.MotorConfig;
import frc.robot.subsystems.Drivetrain.DrivetrainConstants.DriveMotorConfig;
import frc.robot.subsystems.Drivetrain.DrivetrainConstants.TurningMotorConfig;

public class DrivetrainConfig {
    private static MotorConfig driveMotorConfig;
    private static MotorConfig turningMotorConfig;

    public static MotorConfig getDriveMotorConfig() {
        driveMotorConfig = new MotorConfig();

        driveMotorConfig.setEncoderConversion(
            DriveMotorConfig.PositionConversionFactor,
            DriveMotorConfig.VelocityConversionFactor
        );

        driveMotorConfig.withKP(DriveMotorConfig.kP);
        driveMotorConfig.withKI(DriveMotorConfig.kI);
        driveMotorConfig.withKD(DriveMotorConfig.kD);

        driveMotorConfig.withKS(DriveMotorConfig.kS);
        driveMotorConfig.withKV(DriveMotorConfig.kV);
        driveMotorConfig.withKA(DriveMotorConfig.kA);

        return driveMotorConfig;
    }

    public static MotorConfig getTurningMotorConfig(double angleOffset) {
        turningMotorConfig = new MotorConfig();
        
        turningMotorConfig.setEncoderConversion(
            TurningMotorConfig.PositionConversionFactor,
            TurningMotorConfig.VelocityConversionFactor
        );

        turningMotorConfig.setEncoderInverted(TurningMotorConfig.EncoderInverted);

        turningMotorConfig.setPositionWrap(
            TurningMotorConfig.PositionWrapMin,
            TurningMotorConfig.PositionWrapMax
        );

        turningMotorConfig.setFeedbackSensorType(TurningMotorConfig.FeedbackSensorType);

        turningMotorConfig.withKP(TurningMotorConfig.kP);
        turningMotorConfig.withKI(TurningMotorConfig.kI);
        turningMotorConfig.withKD(TurningMotorConfig.kD);

        turningMotorConfig.withKS(TurningMotorConfig.kS);
        turningMotorConfig.withKV(TurningMotorConfig.kV);
        turningMotorConfig.withKA(TurningMotorConfig.kA);

        turningMotorConfig.setAngleOffset(angleOffset);

        return turningMotorConfig;
    }
}
