package frc.robot.subsystems.Intake.Indexer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake.Indexer.module.IndexerModule;

public class Indexer extends SubsystemBase {
    private static Indexer indexer;
    private final IndexerModule indexerModule;

    public Indexer() {
        indexerModule = new IndexerModule(IndexerConstants.indexerMotorID);
    }

    public void intake() {
        indexerModule.setSpeed(IndexerConstants.intakeSpeed);
    }

    public void outtake() {
        indexerModule.setSpeed(IndexerConstants.outtakeSpeed);
    }

    public void stop() {
        indexerModule.setSpeed(0);
    }

    public static Indexer getInstance() {
        if (indexer == null) indexer = new Indexer();
        return indexer;
    }
}
