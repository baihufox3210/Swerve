package frc.robot.subsystems.Intake.Roller;

import frc.robot.constants.Intake.RollerConstants;
import frc.robot.subsystems.Intake.Roller.module.RollerModule;

public class Roller {
    private static Roller roller;
    private final RollerModule rollerModule;

    public Roller() {
        rollerModule = new RollerModule(RollerConstants.rollerMotorID);
    }

    public void intake() {
        rollerModule.setSpeed(RollerConstants.intakeSpeed);
    }

    public void outtake() {
        rollerModule.setSpeed(RollerConstants.outtakeSpeed);
    }

    public void stop() {
        rollerModule.setSpeed(0);
    }

    public static Roller getInstance() {
        if (roller == null)
            roller = new Roller();
        return roller;
    }
}
