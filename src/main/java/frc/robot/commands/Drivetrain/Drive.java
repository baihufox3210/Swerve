package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.math.MathUtil;

public class Drive extends Command {
    private final Drivetrain drivetrain;
    private final CommandXboxController controller;

    private static final double DEADBAND = 0.1;

    public Drive(CommandXboxController controller) {
        this.controller = controller;
        drivetrain = Drivetrain.getInstance();
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double xSpeed = applyDeadband(controller.getLeftY());
        double ySpeed = applyDeadband(controller.getLeftX());
        double rotSpeed = applyDeadband(controller.getRightX());

        drivetrain.drive(xSpeed, ySpeed, rotSpeed);
    }

    private double applyDeadband(double value) {
        return MathUtil.applyDeadband(value, DEADBAND);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}