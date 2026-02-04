package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake.Roller.Roller;

public class Intake extends SubsystemBase{
    public static Intake intake;

    private final Roller roller = Roller.getInstance();

    public void intake() {
        roller.intake();
    }

    public void outtake() {
        roller.outtake();
    }
 
    public static Intake getInstance() {
        if (intake == null) intake = new Intake();
        return intake;
    }
}