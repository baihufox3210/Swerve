package frc.robot.hardware.Motor.SparkMax.Encoder;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

import frc.robot.hardware.interfaces.GenericEncoder;

public class SparkMaxRelativeEncoder implements GenericEncoder {
    private final RelativeEncoder encoder;

    public SparkMaxRelativeEncoder(SparkMax motor) {
        encoder = motor.getEncoder();
    }

    @Override
    public double getPosition() {
        return encoder.getPosition();
    }

    @Override
    public double getVelocity() {
        return encoder.getVelocity();
    }

    @Override
    public void setPosition(double position) {
        encoder.setPosition(position);
    }
}
