package frc.robot.hardware.interfaces;

public interface GenericMotor {
    void configure();
    
    void set(double speed);
    void setVelocity(double speed);
    void setPosition(double position);
    void stop();

    GenericEncoder getEncoder();
    GenericEncoder getAbsoluteEncoder();
}