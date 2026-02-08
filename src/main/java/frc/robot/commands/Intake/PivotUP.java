package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.Pivot.Pivot;
import frc.robot.subsystems.Intake.Pivot.Pivot.IntakeState;

public class PivotUP extends Command {
    private final Pivot pivot;

    public PivotUP() {
        pivot = Pivot.getInstance();
        addRequirements(pivot);
    }

    @Override
    public void execute() {
        pivot.setPosition(IntakeState.IN);
    }

    @Override
    public boolean isFinished() {
        return pivot.isPivotAtPosition(IntakeState.IN);
    }
}
