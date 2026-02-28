package frc.robot.hardware.Motor.TalonFX;

import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecondPerSecond;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.hardware.Factory.MotorFactory.MotorModel;
import frc.robot.hardware.Motor.TalonFX.Encoder.TalonFXRelativeEncoder;
import frc.robot.hardware.config.MotorConfig;
import frc.robot.hardware.interfaces.GenericEncoder;
import frc.robot.hardware.interfaces.GenericMotor;

public class TalonFXMotor implements GenericMotor {
    private final MotorModel motorModel;

    private final TalonFX motor;

    private final MotionMagicVoltage motionMagic;
    private final MotionMagicVelocityVoltage motionMagicVelocity;

    private final NeutralOut neutralOut = new NeutralOut();

    public TalonFXMotor(int motorID, MotorModel motorModel) {
        this.motorModel = motorModel;

        motor = new TalonFX(motorID);

        motionMagic = new MotionMagicVoltage(0);
        motionMagicVelocity = new MotionMagicVelocityVoltage(0);
    }

    @Override
    public void configure(MotorConfig motorConfigs) {
        TalonFXConfiguration config = new TalonFXConfiguration();

        config.MotorOutput.withNeutralMode(NeutralModeValue.Brake);

        if (motorConfigs.inverted) config.MotorOutput.withInverted(InvertedValue.Clockwise_Positive);
        else config.MotorOutput.withInverted(InvertedValue.CounterClockwise_Positive);

        config.Feedback.withSensorToMechanismRatio(motorConfigs.positionConversionFactor);

        config.CurrentLimits
            .withStatorCurrentLimit(motorModel.statorCurrentLimit)
            .withSupplyCurrentLimit(motorModel.supplyCurrentLimit);

        config.withSlot0(
            new Slot0Configs()
                .withKP(motorConfigs.kP).withKI(motorConfigs.kI).withKD(motorConfigs.kD)
                .withKS(motorConfigs.kS).withKV(motorConfigs.kV).withKA(motorConfigs.kA)
                .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign)
        );

        config.withMotionMagic(
            new MotionMagicConfigs()
                .withMotionMagicCruiseVelocity(RotationsPerSecond.of(motorConfigs.cruiseVelocity))
                .withMotionMagicAcceleration(RotationsPerSecondPerSecond.of(motorConfigs.maxAcceleration))
        );

        motor.getConfigurator().apply(config);
    }

    @Override
    public GenericEncoder getEncoder() {
        return new TalonFXRelativeEncoder(motor);
    }

    @Override 
    public GenericEncoder getAbsoluteEncoder() {
        DriverStation.reportError(
            "[Hardware Error] TalonFX ID " + motor.getDeviceID() + 
            ": Attempted to access Absolute Encoder, but none is configured.", 
            true
        );

        return new GenericEncoder() {
            @Override
            public double getPosition() {return 0;}
            @Override
            public double getVelocity() {return 0;}
            @Override
            public void setPosition(double position) {}
        };
    }

    @Override
    public void set(double speed) {
        motor.setControl(new DutyCycleOut(speed));
    }

    @Override
    public void setVelocity(double speed) {
        motor.setControl(motionMagicVelocity.withVelocity(speed).withSlot(0));
    }

    @Override
    public void setPosition(double position) {
        motor.setControl(motionMagic.withPosition(position).withSlot(0));
    }

    @Override
    public void stop() {
        motor.setControl(neutralOut);
    }
}