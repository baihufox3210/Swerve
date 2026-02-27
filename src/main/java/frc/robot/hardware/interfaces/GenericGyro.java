package frc.robot.hardware.interfaces;

import edu.wpi.first.math.geometry.Rotation2d;

public interface GenericGyro {
    Rotation2d getRotation2d();
    double getYaw();

    void reset();
}
