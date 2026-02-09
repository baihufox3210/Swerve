package frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Shooter.module.ShooterModule;

public class Shooter extends SubsystemBase{
    private static Shooter shooter;
    private final ShooterModule shooterModule;

    public Shooter() {
        shooterModule = new ShooterModule(
            ShooterConstants.shootingMotorID,
            ShooterConstants.feederMotorID
        );
    }

    public void shooting() {
        shooterModule.setSpeed(
            ShooterConstants.shootingSpeed,
            ShooterConstants.feederSpeed
        );
    }

    public void stop() {
        shooterModule.setSpeed(0, 0);
    }

    public static Shooter getInstance() {
        if (shooter == null) shooter = new Shooter();
        return shooter;
    }
}
