package frc.robot.hardware.interfaces;

import frc.robot.hardware.config.MotorConfig;

public interface GenericMotor {
    void configure(MotorConfig configs);
    
    void set(double speed);
    void setVelocity(double speed);
    void setPosition(double position);
    void stop();

    GenericEncoder getEncoder();
    GenericEncoder getAbsoluteEncoder();
}