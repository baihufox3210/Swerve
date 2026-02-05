package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake.Indexer.Indexer;
import frc.robot.subsystems.Intake.Roller.Roller;

public class Intake extends SubsystemBase {
    public static Intake intake;

    private final Roller roller = Roller.getInstance();
    private final Indexer indexer = Indexer.getInstance();

    public void intake() {
        roller.intake();
        indexer.intake();
    }

    public void outtake() {
        roller.outtake();
        indexer.outtake();
    }

    public void stop() {
        roller.stop();
        indexer.stop();
    }

    public static Intake getInstance() {
        if (intake == null) intake = new Intake();
        return intake;
    }
}