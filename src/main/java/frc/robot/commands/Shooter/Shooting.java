package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter.Shooter;

public class Shooting extends Command {
    private final Shooter shooter;
    // private final Indexer indexer;

    public Shooting() {
        shooter = Shooter.getInstance();
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.shooting();
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stop();
    }
}
