package org.usfirst.frc.team1164.robot.commands;

import org.usfirst.frc.team1164.robot.OI;
import org.usfirst.frc.team1164.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTankWithJoystick extends Command{
	
	public DriveTankWithJoystick() {
		requires(Robot.kChassis);
	}
	
	public void initialize() {
		
	}
	
	public void execute() {
		
		double fSpeed = OI.getJoystick().getRawAxis(OI.forwardPort);
		double bSpeed = -OI.getJoystick().getRawAxis(OI.backwardPort);
		double speed = fSpeed + bSpeed;
		
		double turn = OI.getJoystick().getRawAxis(OI.turnPort);

		
		double rSpeed = speed - (turn > 0 ? (OI.turnPercent * speed * turn) : 0);
		double lSpeed = speed - (turn < 0 ? (OI.turnPercent * speed * -turn) : 0);
		
		
//		SmartDashboard.putNumber("fSpeed", fSpeed);
//		SmartDashboard.putNumber("bSpeed", bSpeed);
//		SmartDashboard.putNumber("Speed", speed);
//		SmartDashboard.putNumber("turn", turn);
//		SmartDashboard.putNumber("rSpeed", rSpeed);
//		SmartDashboard.putNumber("lSpeed", lSpeed);
		
		Robot.kChassis.setLeftMotorSpeed(lSpeed);
		Robot.kChassis.setRightMotorSpeed(rSpeed);
		
		
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
