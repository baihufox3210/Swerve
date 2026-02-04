package frc.robot.subsystems.Intake.Roller.module;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class RollerModule {
    private final SparkMax rollerMotor;

    public RollerModule(int rollerMotorID) {
        rollerMotor = new SparkMax(rollerMotorID, MotorType.kBrushless);

        rollerMotor.configure(
            RollerConfigs.rollerConfig(),
            ResetMode.kResetSafeParameters,
            PersistMode.kNoPersistParameters
        );
    }
    
    public void setSpeed(double speed) {
        rollerMotor.set(speed);
    }
}
