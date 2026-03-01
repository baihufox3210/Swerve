package frc.robot.hardware.Factory;

import frc.robot.hardware.Motor.SparkFlex.SparkFlexMotor;
import frc.robot.hardware.Motor.SparkMax.SparkMaxMotor;
import frc.robot.hardware.Motor.TalonFX.TalonFXMotor;
import frc.robot.hardware.config.MotorConfig;
import frc.robot.hardware.interfaces.GenericMotor;

public class MotorFactory {
    public static enum MotorModel {
        Neo(40, 44), 
        Neo550(20, 25),
        NeoVortex(40, 44),
        KrakenX60(40, 44);

        public final int statorCurrentLimit;
        public final int supplyCurrentLimit;

        MotorModel(int statorCurrentLimit, int supplyCurrentLimit) {
            this.statorCurrentLimit = statorCurrentLimit;
            this.supplyCurrentLimit = supplyCurrentLimit; 
        }
    }

    public static GenericMotor createMotor(int motorID, MotorModel motorModel, MotorConfig motorConfig) {
        return switch (motorModel) {
            case Neo, Neo550 -> new SparkMaxMotor(motorID, motorModel, motorConfig);
            case NeoVortex -> new SparkFlexMotor(motorID, motorModel, motorConfig);
            case KrakenX60 -> new TalonFXMotor(motorID, motorModel, motorConfig);
            default -> throw new IllegalArgumentException("Unknown motor model: " + motorModel);
        };
    }
}