package org.usfirst.frc.team1164.robot.commands.Auto;

import org.usfirst.frc.team1164.logic.PIDMotion;
import org.usfirst.frc.team1164.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForward extends Command {
	private double distance;
	private PIDMotion controller;
	
	public DriveForward(double distance) {
		this.distance = distance;
		requires(Robot.kChassis);
		controller = new PIDMotion();
	}
	
	public void initialize() {
		Robot.kChassis.resetEncoders();
	}
	
	public void execute() {
		double speed = controller.update();
		
		Robot.kChassis.setLeftMotorSpeed(speed);
		Robot.kChassis.setRightMotorSpeed(speed);
		
		
		SmartDashboard.putNumber("Distance", distance);
		SmartDashboard.putNumber("speed", speed);
		SmartDashboard.putNumber("LeftEncoder", Robot.kChassis.getLeftEncoder());
		SmartDashboard.putNumber("RightEncoder", Robot.kChassis.getRightEncoder());
	}

	@Override
	protected boolean isFinished() {
		return controller.isDone();
	}
	
	public void end() {
		
	}
	
	public void interrupted() {
		
	}

}
