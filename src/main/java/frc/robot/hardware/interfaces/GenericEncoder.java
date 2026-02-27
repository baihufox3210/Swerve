package frc.robot.hardware.interfaces;

public interface GenericEncoder {
    double getPosition();
    double getVelocity();

    void setPosition(double position);
}