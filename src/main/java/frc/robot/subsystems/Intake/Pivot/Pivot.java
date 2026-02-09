package frc.robot.subsystems.Intake.Pivot;

import static edu.wpi.first.units.Units.Degrees;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake.Pivot.module.PivotModule;

public class Pivot extends SubsystemBase {
    private static Pivot pivot;

    private PivotModule pivotModule;

    public Pivot() {
        pivotModule = new PivotModule(PivotConstants.pivotMotorID);
    }

    public IntakeState getCurrentPosition() {
        Angle currentAngle = pivotModule.getPose();
        if (currentAngle.isNear(IntakeState.IN.getValue(), PivotConstants.tolerance)) return IntakeState.IN;
        else if (currentAngle.isNear(IntakeState.OUT.getValue(), PivotConstants.tolerance)) return IntakeState.OUT;
        else return IntakeState.TRANSLATING;
    }

    public void setPosition(IntakeState state) {
        pivotModule.setControl(state);
    }

    public boolean isPivotAtPosition(IntakeState state) {
        return pivotModule.getPose().isNear(state.getValue(), PivotConstants.tolerance);
    }

    public static Pivot getInstance() {
        if (pivot == null)  pivot = new Pivot();
        return pivot;
    }

    public enum IntakeState {
        IN(Degrees.of(0)),
        OUT(Degrees.of(360 * 0.3)),
        TRANSLATING(Degrees.of(-1));

        private final Angle deg;

        IntakeState(Angle deg) {
            this.deg = deg;
        }

        public Angle getValue() {
            return deg;
        }
    }
}
