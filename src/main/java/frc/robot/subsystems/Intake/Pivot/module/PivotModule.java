package frc.robot.subsystems.Intake.Pivot.module;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Angle;
import frc.robot.subsystems.Intake.Pivot.PivotConstants;
import frc.robot.subsystems.Intake.Pivot.Pivot.IntakeState;

public class PivotModule {
    private final TalonFX pivotMotor;
    private MotionMagicVoltage pivotMagic;

    public PivotModule(int pivotMotorID) {
        pivotMotor = new TalonFX(pivotMotorID, PivotConstants.bus);
        pivotMotor.getConfigurator().apply(PivotConfigs.pivotConfig());
    }

    public Angle getPose() {
        return pivotMotor.getPosition().getValue();
    }
    
    public void setControl(IntakeState state) {
        pivotMotor.setControl(pivotMagic.withPosition(state.getValue()));
    }
}
