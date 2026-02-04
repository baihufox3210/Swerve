package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.Intake;

public class IntakeCommands {
    public static Command intakeCommand(Intake intake) {
        return intake.run(intake::intake).finallyDo(intake::stop).withName("IntakeCommand");
    }

    public static Command outtakeCommand(Intake intake) {
        return intake.run(intake::outtake).finallyDo(intake::stop).withName("OuttakeCommand");
    }
}