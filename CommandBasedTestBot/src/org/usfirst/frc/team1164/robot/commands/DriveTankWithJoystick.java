package org.usfirst.frc.team1164.robot.commands;

import org.usfirst.frc.team1164.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTankWithJoystick extends Command{
	
	public DriveTankWithJoystick() {
		requires(Robot.kChassis);
	}
	
	public void initialize() {
		
	}
	
	public void execute() {
		Robot.kChassis.DriveStick();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	public void end() {
		
	}
	
	public void interrupted() {
		
	}
}
