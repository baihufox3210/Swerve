package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class DriverControls {
    private static DriverControls driverControls;

    private final Joystick joystick = new Joystick(0);

    public double getXSpeed() {
        return joystick.getY();
    }

    public double getYSpeed() {
        return joystick.getX();
    }

    public double getRotSpeed() {
        return joystick.getRawAxis(4);
    }

    public JoystickButton intakeButton() {
        return new JoystickButton(joystick, 1);
    }

    public JoystickButton outtakeButton() {
        return new JoystickButton(joystick, 3);
    }

    public static DriverControls getInstance() {
        if (driverControls == null) driverControls = new DriverControls();
        return driverControls;
    }
}