package frc.robot.subsystems.Intake.Roller;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake.Roller.module.RollerModule;

public class Roller extends SubsystemBase{
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
