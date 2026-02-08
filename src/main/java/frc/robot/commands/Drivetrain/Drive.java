package frc.robot.commands.Drivetrain;

import java.util.function.Supplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain.Drivetrain;

public class Drive extends Command{
    private final Drivetrain drivetrain;
    private final Supplier<ChassisSpeeds> chassisSpeeds;

    public Drive(Supplier<ChassisSpeeds> chassisSpeeds) {
        drivetrain = Drivetrain.getInstance();
        this.chassisSpeeds = chassisSpeeds;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.drive(chassisSpeeds.get());
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(
            new ChassisSpeeds(0, 0, 0)
        );
    }
}