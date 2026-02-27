package frc.robot.hardware.Motor.SparkFlex.Encoder;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;

import frc.robot.hardware.interfaces.GenericEncoder;

public class SparkFlexRelativeEncoder implements GenericEncoder {
    private final RelativeEncoder encoder;

    public SparkFlexRelativeEncoder(SparkFlex motor) {
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
