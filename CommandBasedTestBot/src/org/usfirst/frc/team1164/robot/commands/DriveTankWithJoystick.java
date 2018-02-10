package org.usfirst.frc.team1164.robot.commands;

import org.usfirst.frc.team1164.robot.OI;
import org.usfirst.frc.team1164.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTankWithJoystick extends Command{
	
	public DriveTankWithJoystick() {
		requires(Robot.kChassis);
	}
	
	public void initialize() {
		
	}
	
	public void execute() {
		Robot.kChassis.setLeftMotorSpeed(OI.getJoystick().getRawAxis(1));
		Robot.kChassis.setRightMotorSpeed(OI.getJoystick().getRawAxis(3));
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	public void end() {
		
	}
	
	public void interrupted() {
		
	}
}
