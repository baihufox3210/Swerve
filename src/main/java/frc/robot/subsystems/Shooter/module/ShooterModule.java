package frc.robot.subsystems.Shooter.module;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ShooterModule {
    private final SparkMax shootingMotor;
    private final SparkMax feederMotor;

    public ShooterModule(int shootingMotorID, int feederMotorID) {
        shootingMotor = new SparkMax(shootingMotorID, MotorType.kBrushless);
        feederMotor = new SparkMax(feederMotorID, MotorType.kBrushless);

        shootingMotor.configure(
            ShooterConfigs.shootingConfig(),
            ResetMode.kResetSafeParameters,
            PersistMode.kNoPersistParameters
        );
        
        feederMotor.configure(
            ShooterConfigs.feederConfig(),
            ResetMode.kResetSafeParameters,
            PersistMode.kNoPersistParameters
        );
    }

    public void setSpeed(double shootingSpeed, double feederSpeed) {
        shootingMotor.set(shootingSpeed);
        feederMotor.set(feederSpeed);
    }
}