package frc.robot.subsystems.Shooter.module;

import frc.robot.utils.SparkMaxConfiguration;

public class ShooterConfigs {
    public static SparkMaxConfiguration shootingConfig() {
        return new SparkMaxConfiguration(44, false);
    }

    public static SparkMaxConfiguration feederConfig() {
        return new SparkMaxConfiguration(44, true);
    }
}
