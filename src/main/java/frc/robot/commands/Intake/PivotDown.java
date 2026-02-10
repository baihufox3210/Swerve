package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.Pivot.Pivot;
import frc.robot.subsystems.Intake.Pivot.Pivot.IntakeState;

public class PivotDown extends Command {
    private final Pivot pivot;
    private final Timer timer = new Timer();
    private static final double TIMEOUT_SECONDS = 5.0;

    public PivotDown() {
        pivot = Pivot.getInstance();
        addRequirements(pivot);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        pivot.setPosition(IntakeState.OUT);
    }

    @Override
    public boolean isFinished() {
        return pivot.isPivotAtPosition(IntakeState.OUT) || timer.hasElapsed(TIMEOUT_SECONDS);
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        pivot.stop();
    }
}
