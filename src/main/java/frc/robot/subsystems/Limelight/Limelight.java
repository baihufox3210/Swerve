package frc.robot.subsystems.Limelight;

import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.PoseEstimate;
import frc.robot.subsystems.Gyro.Gyro;

public class Limelight {
    private static Limelight limelight;

    private final String limelightName = "limelight";

    Gyro gyro = Gyro.getInstance();

    public Limelight() {
        LimelightHelpers.setPipelineIndex(limelightName, 0);
        LimelightHelpers.setLEDMode_ForceOn(limelightName);
    }

    public static Limelight getInstance() {
        if (limelight == null) limelight = new Limelight();
        return limelight;
    }

    public boolean hasAprilTagTarget() {
        return LimelightHelpers.getTV(limelightName);
    }

    public PoseEstimate getMeasurement() {
        LimelightHelpers.SetRobotOrientation(limelightName, gyro.getYaw(), 0, 0, 0, 0, 0);
        PoseEstimate limelightMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(limelightName);
        return limelightMeasurement;
    }
}