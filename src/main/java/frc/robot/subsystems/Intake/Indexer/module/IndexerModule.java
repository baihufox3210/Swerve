package frc.robot.subsystems.Intake.Indexer.module;

import com.revrobotics.spark.SparkMax;

public class IndexerModule {
    private final SparkMax indexerMotor;

    public IndexerModule(int indexerMotorID) {
        indexerMotor = new SparkMax(indexerMotorID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);

        indexerMotor.configure(
            IndexerConfigs.indexerConfig(),
            com.revrobotics.ResetMode.kResetSafeParameters,
            com.revrobotics.PersistMode.kNoPersistParameters
        );
    }

    public void setSpeed(double speed) {
        indexerMotor.set(speed);
    }
}
