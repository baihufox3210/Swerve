package frc.robot.hardware.Factory;

import frc.robot.hardware.Motor.SparkFlex.SparkFlexMotor;
import frc.robot.hardware.Motor.SparkMax.SparkMaxMotor;
import frc.robot.hardware.Motor.TalonFX.TalonFXMotor;
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

    public static GenericMotor createMotor(int motorID, MotorModel motorModel) {
        return switch (motorModel) {
            case Neo, Neo550 -> new SparkMaxMotor(motorID, motorModel);
            case NeoVortex -> new SparkFlexMotor(motorID, motorModel);
            case KrakenX60 -> new TalonFXMotor(motorID, motorModel);
            default -> throw new IllegalArgumentException("Unknown motor model: " + motorModel);
        };
    }
}