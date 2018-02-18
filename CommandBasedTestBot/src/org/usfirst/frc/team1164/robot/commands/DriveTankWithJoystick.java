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
		
		double fSpeed = OI.getJoystick().getRawAxis(3);
		double bSpeed = -OI.getJoystick().getRawAxis(2);
		double speed = fSpeed + bSpeed;
		
		Robot.kChassis.setLeftMotorSpeed(speed);
		Robot.kChassis.setRightMotorSpeed(speed);
		
		
//		Robot.kChassis.setLeftMotorSpeed(OI.getJoystick().getRawAxis(1));
//		Robot.kChassis.setRightMotorSpeed(OI.getJoystick().getRawAxis(5));
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	public void end() {
		
	}
	
	public void interrupted() {
		
	}
}
