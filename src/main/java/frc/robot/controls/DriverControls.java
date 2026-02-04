package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;

public class DriverControls {
    private final Joystick joystick = new Joystick(0);

    private boolean isFieldRelative = true;

    public double getXSpeed() {
        return -joystick.getY();
    }

    public double getYSpeed() {
        return -joystick.getX();
    }

    public double getRotSpeed() {
        return -joystick.getRawAxis(4);
    }

    public boolean isFieldRelative() {
        return isFieldRelative;
    }

    public void toggleFieldRelative() {
        isFieldRelative = !isFieldRelative;
    }
}