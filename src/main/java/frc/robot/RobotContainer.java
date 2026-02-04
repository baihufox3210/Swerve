package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.IntakeCommands;
import frc.robot.controls.DriverControls;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.subsystems.Intake.Intake;

public class RobotContainer {
	private final DriverControls controls = new DriverControls();

	private final Drivetrain drivetrain = Drivetrain.getInstance();
	private final Intake intake = Intake.getInstance();

	public RobotContainer() {
		drivetrain.setDefaultCommand(
			DriveCommands.defaultDrive(drivetrain, controls)
		);

		configureButtonBindings();
	}

	private void configureButtonBindings() {
		controls.intakeButton().whileTrue(
			IntakeCommands.intakeCommand(intake)
		);

		// controls.outtakeButton().toggleOnTrue(
		// 	IntakeCommands.outtakeCommand(intake)
		// );
	}

	public Command getAutonomousCommand() {
		return Commands.print("No autonomous command configured!");
	}
}