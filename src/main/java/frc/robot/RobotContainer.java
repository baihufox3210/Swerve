package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.controls.DriverControls;
import frc.robot.subsystems.Drivetrain.Drivetrain;

import frc.robot.commands.Drivetrain.Drive;
import frc.robot.commands.Intake.Intake;
import frc.robot.commands.Intake.Outtake;
import frc.robot.commands.Shooter.Shooting;

public class RobotContainer {
	private final DriverControls controls = new DriverControls();

	private final Drivetrain drivetrain = Drivetrain.getInstance();

	public RobotContainer() {
		drivetrain.setDefaultCommand(
			new Drive(
				() -> drivetrain.getChassisSpeeds(
					controls.getXSpeed(),
					controls.getYSpeed(), 
					controls.getRotSpeed()
				)
			)
		);

		configureButtonBindings();
	}

	private void configureButtonBindings() {
		controls.intakeButton().whileTrue(new Intake());
		controls.outtakeButton().whileTrue(new Outtake());
		controls.shootingButton().whileTrue(new Shooting());
	}

	public Command getAutonomousCommand() {
		try {
			PathPlannerPath path = PathPlannerPath.fromPathFile("Auto Path");
			return AutoBuilder.followPath(path);
		} catch (Exception e) {
			DriverStation.reportError("Big oops: " + e.getMessage(), e.getStackTrace());
			return Commands.none();
		}
	}
}