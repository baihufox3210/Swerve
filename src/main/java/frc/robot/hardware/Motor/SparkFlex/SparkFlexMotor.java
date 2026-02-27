package frc.robot.hardware.Motor.SparkFlex;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.MAXMotionConfig;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.hardware.Motor.SparkFlex.Encoder.SparkFlexAbsoluteEncoder;
import frc.robot.hardware.Motor.SparkFlex.Encoder.SparkFlexRelativeEncoder;
import frc.robot.hardware.config.MotorConfig;
import frc.robot.hardware.interfaces.GenericEncoder;
import frc.robot.hardware.interfaces.GenericMotor;

public class SparkFlexMotor implements GenericMotor {
    private final SparkFlex motor;
    private final SparkClosedLoopController closedLoopController;
    
    public SparkFlexMotor(int motorID) {
        motor = new SparkFlex(motorID, MotorType.kBrushless);
        closedLoopController = motor.getClosedLoopController();
    }

    @Override
    public void configure(MotorConfig motorConfig) {
        SparkFlexConfig config = new SparkFlexConfig();
        MAXMotionConfig motionConfig = new MAXMotionConfig();

        config.idleMode(IdleMode.kBrake);

        config.inverted(motorConfig.inverted);

        config.smartCurrentLimit(motorConfig.supplyCurrentLimit);
        config.voltageCompensation(motorConfig.voltageCompensation);

        motionConfig.cruiseVelocity(motorConfig.cruiseVelocity);
        motionConfig.maxAcceleration(motorConfig.maxAcceleration);
        motionConfig.allowedProfileError(motorConfig.allowedProfileError);

        config.encoder
            .positionConversionFactor(motorConfig.positionConversionFactor)
            .velocityConversionFactor(motorConfig.velocityConversionFactor);

        config.absoluteEncoder
            .zeroOffset(motorConfig.angleOffset)
            .positionConversionFactor(motorConfig.positionConversionFactor)
            .velocityConversionFactor(motorConfig.velocityConversionFactor)
            .inverted(motorConfig.encoderInverted);

        config.closedLoop
            .pid(motorConfig.kP, motorConfig.kI, motorConfig.kD);
            // .feedForward
            //     .kS(motorConfig.kS)
            //     .kV(motorConfig.kV)
            //     .kA(motorConfig.kA);
        
        config.closedLoop.feedbackSensor(motorConfig.feedbackSensorType);
        config.closedLoop.apply(motionConfig);

        if (motorConfig.positionWrap != null) {
            config.closedLoop
                .positionWrappingEnabled(true)
                .positionWrappingInputRange(
                    motorConfig.positionWrap.min(),
                    motorConfig.positionWrap.max()
                );
        }

        motor.configure(
            config,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters
        );
    }

    @Override
    public GenericEncoder getEncoder() {
        return new SparkFlexRelativeEncoder(motor);
    }

    @Override 
    public GenericEncoder getAbsoluteEncoder() {
        return new SparkFlexAbsoluteEncoder(motor);
    }

    @Override
    public void set(double speed) {
        motor.set(speed);
    }

    @Override
    public void setVelocity(double speed) {
        closedLoopController.setSetpoint(
            speed,
            ControlType.kVelocity
        );
    }

    @Override
    public void setPosition(double position) {
        closedLoopController.setSetpoint(
            position,
            ControlType.kPosition
        );
    }
    
    @Override
    public void stop() {
        motor.stopMotor();
    }
}