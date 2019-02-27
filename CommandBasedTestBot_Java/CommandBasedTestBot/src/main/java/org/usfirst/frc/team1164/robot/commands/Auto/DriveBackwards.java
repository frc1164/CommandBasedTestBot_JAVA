package org.usfirst.frc.team1164.robot.commands.Auto;

import org.usfirst.frc.team1164.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBackwards extends Command {
	private double distance;
	private double speed;
	
	public DriveBackwards(double distance, double speed) {
		this.distance = -distance;
		this.speed = -speed;
		requires(Robot.kChassis);
		
	}
	
	public void initialize() {
		Robot.kChassis.ResetEncoders();
	}
	
	public void execute() {
		Robot.kChassis.setLeftMotorSpeed(speed);
		Robot.kChassis.setRightMotorSpeed(speed);
		
		
		SmartDashboard.putNumber("Distance", distance);
		SmartDashboard.putNumber("speed", speed);
		SmartDashboard.putNumber("LeftEncoder", Robot.kChassis.GetLeftEncoder());
		SmartDashboard.putNumber("RightEncoder", Robot.kChassis.GetLeftEncoder());
	}

	@Override
	protected boolean isFinished() {
		double leftEncoder = Robot.kChassis.GetLeftEncoder();
		double rightEncoder = Robot.kChassis.GetRightEncoder();
		
		boolean checkLeft = leftEncoder <= distance || leftEncoder > 0;
		boolean checkRight = rightEncoder <= distance || leftEncoder > 0;
		
		return (checkLeft || checkRight);
	}
	
	public void end() {
		
	}
	
	public void interrupted() {
		
	}

}
