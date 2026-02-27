package frc.robot.hardware.Gyro;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.hardware.interfaces.GenericGyro;

public class Pigeon implements GenericGyro {
    private final Pigeon2 gyro;

    public Pigeon(int id) {
        gyro = new Pigeon2(id);
    }

    @Override
    public Rotation2d getRotation2d() {
        return gyro.getRotation2d();
    }

    @Override
    public double getYaw() {
        return gyro.getYaw().getValueAsDouble();
    }

    @Override
    public void reset() {
        gyro.setYaw(0);
    }
}
