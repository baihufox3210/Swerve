package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.controls.DriverControls;
import frc.robot.subsystems.Drivetrain.Drivetrain;

public class DriveCommands {
    public static Command setXCommand(Drivetrain drivetrain) {
        return drivetrain.run(drivetrain::stopWithXMode).withName("XCommand");
    }

    public static Command defaultDrive(Drivetrain drivetrain, DriverControls controls) {
        return drivetrain.run(
            () -> {
                drivetrain.drive(
                    getChassisSpeeds(
                        drivetrain.getPose().getRotation(),
                        controls
                    )
                );
            }
        ).withName("defaultDrive");
    }

    private static ChassisSpeeds getChassisSpeeds(Rotation2d rotation, DriverControls controls) {
        if (controls.isFieldRelative()) {
            return ChassisSpeeds.fromFieldRelativeSpeeds(
                controls.getXSpeed() * DrivetrainConstants.maxSpeed,
                controls.getYSpeed() * DrivetrainConstants.maxSpeed,
                controls.getRotSpeed() ,
                rotation
            );
        } else {
            return new ChassisSpeeds(
                controls.getXSpeed(),
                controls.getYSpeed(),
                controls.getRotSpeed()
            );
        }
    }
}
