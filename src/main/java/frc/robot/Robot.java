package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Intake.PivotDown;

public class Robot extends TimedRobot {
  	private Command autonomousCommand;
  	private final RobotContainer robotContainer;

  	public Robot() {
    	robotContainer = new RobotContainer();
  	}

 	public void robotInit() {
  	}

  	@Override
		public void robotPeriodic() {
  		CommandScheduler.getInstance().run();
  	}

  	@Override
  	public void disabledInit() {}

  	@Override
  	public void disabledPeriodic() {}

  	@Override
  	public void disabledExit() {}

  	@Override
  	public void autonomousInit() {
    new PivotDown().schedule();
    
    autonomousCommand = robotContainer.getAutonomousCommand();

    if (autonomousCommand != null) {
      		autonomousCommand.schedule();
    	}
  	}

  	@Override
  	public void autonomousPeriodic() {}

  	@Override
  	public void autonomousExit() {}

  	@Override
  	public void teleopInit() {
    	if (autonomousCommand != null) {
      		autonomousCommand.cancel();
    	}
    	new PivotDown().schedule();
  	}

  	@Override
  	public void teleopPeriodic() {}

  	@Override
 	public void teleopExit() {}
}
