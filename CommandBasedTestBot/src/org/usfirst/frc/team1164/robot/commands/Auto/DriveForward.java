package org.usfirst.frc.team1164.robot.commands.Auto;

import org.usfirst.frc.team1164.logic.PIDMotion;
import org.usfirst.frc.team1164.robot.Robot;
import org.usfirst.frc.team1164.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForward extends Command {
	private double distance;
	private PIDMotion controller;
	
	public DriveForward(double distance) {
		SmartDashboard.putString("Enabled?", "1");
		this.distance = distance;
		requires(Robot.kChassis);
		
		double maxAcceleration = 3 * RobotMap.footToCm; 
		double maxVelocity = 4 * RobotMap.footToCm; 
		SmartDashboard.putString("Enabled?", "2");
		controller = new PIDMotion(maxAcceleration, maxVelocity, 0.5, 0, 0);
		SmartDashboard.putString("Enabled?", "3");
		controller.setEndpoint(distance);
		SmartDashboard.putString("Enabled?", "4");
	}
	
	public void initialize() {
		Robot.kChassis.resetEncoders();
	}
	
	public void execute() {
		SmartDashboard.putString("Enabled?", "5");
		double actualPos = Robot.kChassis.getAverageEncoder() * RobotMap.footToCm;
		SmartDashboard.putString("Enabled?", "6");
		
		double speed = controller.getOutput(actualPos);
		SmartDashboard.putString("Enabled?", "7");
		
		
		
		Robot.kChassis.setLeftMotorSpeed(speed);
		Robot.kChassis.setRightMotorSpeed(speed);
		
		
		SmartDashboard.putNumber("Distance", actualPos);
		SmartDashboard.putNumber("speed", speed);
		SmartDashboard.putNumber("LeftEncoder", Robot.kChassis.getLeftEncoder());
		SmartDashboard.putNumber("RightEncoder", Robot.kChassis.getRightEncoder());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	public void end() {
		
	}
	
	public void interrupted() {
		
	}

}
