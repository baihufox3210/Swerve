package frc.robot.subsystems.Intake.Pivot.module;

import static edu.wpi.first.units.Units.Amps;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.subsystems.Intake.Pivot.PivotConstants;

public class PivotConfigs {
    public static TalonFXConfiguration pivotConfig() {
        TalonFXConfiguration config = new TalonFXConfiguration();

        config.MotorOutput
            .withInverted(InvertedValue.Clockwise_Positive)
            .withNeutralMode(NeutralModeValue.Brake);

        config.Feedback
            .withSensorToMechanismRatio(PivotConstants.GearRatio);

        config.CurrentLimits
            .withStatorCurrentLimit(Amps.of(30))
            .withSupplyCurrentLimit(Amps.of(44));

        config.withSlot0(PivotConstants.pivotPID);
        config.withMotionMagic(PivotConstants.pivotMagic);
        
        return config;
    }
}
