package frc.robot.hardware.Motor.SparkMax;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.MAXMotionConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.hardware.Factory.MotorFactory.MotorModel;
import frc.robot.hardware.Motor.SparkMax.Encoder.SparkMaxAbsoluteEncoder;
import frc.robot.hardware.Motor.SparkMax.Encoder.SparkMaxRelativeEncoder;
import frc.robot.hardware.config.MotorConfig;
import frc.robot.hardware.interfaces.GenericEncoder;
import frc.robot.hardware.interfaces.GenericMotor;

public class SparkMaxMotor implements GenericMotor {
    private final MotorModel motorModel;
    private final MotorConfig motorConfig;

    private final SparkMax motor;
    private final SparkClosedLoopController closedLoopController;
    
    
    public SparkMaxMotor(int motorID, MotorModel motorModel, MotorConfig motorConfig) {
        this.motorModel = motorModel;
        this.motorConfig = motorConfig;

        motor = new SparkMax(motorID, MotorType.kBrushless);
        closedLoopController = motor.getClosedLoopController();
    }

    @Override
    public void configure() {
        SparkMaxConfig config = new SparkMaxConfig();
        MAXMotionConfig motionConfig = new MAXMotionConfig();

        config.idleMode(IdleMode.kBrake);

        config.inverted(motorConfig.inverted);

        config.smartCurrentLimit(motorModel.statorCurrentLimit);
        config.secondaryCurrentLimit(motorModel.supplyCurrentLimit);
        
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
        return new SparkMaxRelativeEncoder(motor);
    }

    @Override 
    public GenericEncoder getAbsoluteEncoder() {
        return new SparkMaxAbsoluteEncoder(motor);
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