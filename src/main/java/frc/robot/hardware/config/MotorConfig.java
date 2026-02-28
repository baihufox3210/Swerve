package frc.robot.hardware.config;

import com.revrobotics.spark.FeedbackSensor;

public class MotorConfig {   
    public double voltageCompensation = 12.0;
    
    public double kP = 0.0;
    public double kI = 0.0;
    public double kD = 0.0;
    public double kA = 0.0;
    public double kV = 0.0;
    public double kS = 0.0;

    public boolean inverted = false;
    public boolean encoderInverted = false;

    public double angleOffset = 0;

    public double positionConversionFactor = 1.0;
    public double velocityConversionFactor = 1.0;

    public double cruiseVelocity = 4.0;
    public double maxAcceleration = 2.0;
    public double allowedProfileError = 0.07;

    public FeedbackSensor feedbackSensorType = FeedbackSensor.kPrimaryEncoder;

    public record PositionWrap(double min, double max) {}

    public PositionWrap positionWrap = null;

    public MotorConfig() {}

    public MotorConfig setInverted(boolean inverted) {
        this.inverted = inverted;
        return this;
    }

    public MotorConfig setEncoderInverted(boolean encoderInverted) {
        this.encoderInverted = encoderInverted;
        return this;
    }

    public MotorConfig withKP(double kP) {
        this.kP = kP;
        return this;
    }

    public MotorConfig withKI(double kI) {
        this.kI = kI;
        return this;
    }

    public MotorConfig withKD(double kD) {
        this.kD = kD;
        return this;
    }

    public MotorConfig withKA(double kA) {
        this.kA = kA;
        return this;
    }

    public MotorConfig withKV(double kV) {
        this.kV = kV;
        return this;
    }

    public MotorConfig withKS(double kS) {
        this.kS = kS;
        return this;
    }

    public MotorConfig setAngleOffset(double angleOffset) {
        this.angleOffset = angleOffset;
        return this;
    }

    public MotorConfig setPositionWrap(double min, double max) {
        this.positionWrap = new PositionWrap(min, max);
        return this;
    }

    public MotorConfig setFeedbackSensorType(FeedbackSensor sensor) {
        this.feedbackSensorType = sensor;
        return this;
    }

    public MotorConfig setEncoderConversion(double positionConversionFactor, double velocityConversionFactor) {
        this.positionConversionFactor = positionConversionFactor;
        this.velocityConversionFactor = velocityConversionFactor;
        return this;
    }
}