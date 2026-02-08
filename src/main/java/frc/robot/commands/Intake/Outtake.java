package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.Indexer.Indexer;
import frc.robot.subsystems.Intake.Roller.Roller;

public class Outtake extends Command {
    private final Roller roller;
    // private final Indexer indexer;

    public Outtake() {
        roller = Roller.getInstance();
        // indexer = Indexer.getInstance();
        addRequirements(roller);
    }

    @Override
    public void execute() {
        roller.intake();
        // indexer.intake();
    }

    @Override
    public void end(boolean interrupted) {
        roller.stop();
        // indexer.stop();
    }
}