package frc.robot.hardware.Motor.SparkMax.Encoder;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.hardware.interfaces.GenericEncoder;

public class SparkMaxAbsoluteEncoder implements GenericEncoder {
    private final AbsoluteEncoder encoder;

    public SparkMaxAbsoluteEncoder(SparkMax motor) {
        encoder = motor.getAbsoluteEncoder();
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
        DriverStation.reportError("Cannot set position of an absolute encoder", false);
    }
}
