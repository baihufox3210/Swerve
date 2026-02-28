package frc.robot.hardware.Factory;

import frc.robot.hardware.Gyro.NavX;
import frc.robot.hardware.Gyro.Pigeon;
import frc.robot.hardware.interfaces.GenericGyro;

public class GyroFactory {
    public static enum GyroModel {
        NAVX, PIGEON
    }

    public static GenericGyro createGyro(GyroModel gyroModel, int id) {
        switch (gyroModel) {
            case NAVX:
                return new NavX();

            case PIGEON:
                return new Pigeon(id);

            default:
                throw new IllegalArgumentException("Unsupported gyro type: " + gyroModel);
        }
    }
}
