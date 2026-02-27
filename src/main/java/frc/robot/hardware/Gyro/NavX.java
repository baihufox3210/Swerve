package frc.robot.hardware.Gyro;


import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.hardware.interfaces.GenericGyro;

public class NavX implements GenericGyro {
    private final AHRS gyro;

    public NavX() {
        gyro = new AHRS(NavXComType.kMXP_SPI);
    }

    @Override
    public Rotation2d getRotation2d() {
        return gyro.getRotation2d();
    }

    @Override
    public double getYaw() {
        return gyro.getYaw();
    }

    @Override
    public void reset() {
        gyro.reset();
    }
}
