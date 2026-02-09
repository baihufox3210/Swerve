package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.controls.DriverControls;
import frc.robot.subsystems.Drivetrain.Drivetrain;

import frc.robot.commands.Drivetrain.Drive;
import frc.robot.commands.Intake.Intake;
import frc.robot.commands.Intake.Outtake;

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
	}

	public Command getAutonomousCommand() {
		return Commands.print("No autonomous command configured!");
	}
}