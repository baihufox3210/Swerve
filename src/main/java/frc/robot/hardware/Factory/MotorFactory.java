package frc.robot.hardware.Factory;

import frc.robot.hardware.Motor.SparkFlex.SparkFlexMotor;
import frc.robot.hardware.Motor.SparkMax.SparkMaxMotor;
import frc.robot.hardware.Motor.TalonFX.TalonFXMotor;
import frc.robot.hardware.interfaces.GenericMotor;

public class MotorFactory {
    public static enum MotorType {
        SPARKMAX, SPARKFLEX, TALONFX
    }

    public static GenericMotor createMotor(int motorID, MotorType motorType) {
        switch (motorType) {
            case SPARKMAX:
                return new SparkMaxMotor(motorID);

            case SPARKFLEX:
                return new SparkFlexMotor(motorID);

            case TALONFX:
                return new TalonFXMotor(motorID);

            default:
                throw new IllegalArgumentException("Unsupported motor type: " + motorType);
        }
    }
}