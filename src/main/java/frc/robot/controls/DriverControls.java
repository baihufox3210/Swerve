package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class DriverControls {
    private final Joystick joystick = new Joystick(0);

    private boolean isFieldRelative = false;

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

    public void setFieldRelative(boolean fieldRelative) {
        isFieldRelative = fieldRelative;
    }

    public void toggleFieldRelative() {
        isFieldRelative = !isFieldRelative;
    }

    public JoystickButton intakeButton() {
        return new JoystickButton(joystick, 0);
    }

    public JoystickButton outtakeButton() {
        return new JoystickButton(joystick, 2);
    }
}