package org.usfirst.frc.team1164.robot.commands;

import org.usfirst.frc.team1164.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MyAutoCommand extends Command {
	private double distance;
	private double speed;
	
	public MyAutoCommand(double distance, double speed) {
		this.distance = distance;
		this.speed = speed;
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
		double absLeft = Math.abs(Robot.kChassis.GetLeftEncoder());
		double absRight = Math.abs(Robot.kChassis.GetRightEncoder());
		
		boolean checkLeft = absLeft >= distance;
		boolean checkRight = absRight >= distance;

		SmartDashboard.putNumber("AbsLeft", absLeft);
		SmartDashboard.putNumber("AbsRight", absRight);
		
		return (checkLeft || checkRight);
	}
	
	public void end() {
		
	}
	
	public void interrupted() {
		
	}

}
