package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.DriveCommands;
import frc.robot.controls.DriverControls;
import frc.robot.subsystems.Drivetrain.Drivetrain;

public class RobotContainer {
	private final Drivetrain drivetrain = Drivetrain.getInstance();
	private final DriverControls controls = new DriverControls();

	private boolean isFieldRelative = true;

	public RobotContainer() {
		drivetrain.setDefaultCommand(
			DriveCommands.defaultDrive(drivetrain, controls)
		);
	}
	public Command getAutonomousCommand() {
		return Commands.print("No autonomous command configured!");
	}
}