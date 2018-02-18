package org.usfirst.frc.team1164.robot.commands.Auto;

import org.usfirst.frc.team1164.logic.PIDMotion;
import org.usfirst.frc.team1164.logic.PIDVMotion;
//import org.usfirst.frc.team1164.logic.Preferences;
import org.usfirst.frc.team1164.robot.Robot;
import org.usfirst.frc.team1164.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;

public class DriveForward extends Command {
	private double distance;
	private PIDMotion controller;
	
	public DriveForward(double distance) {
		
		Preferences pref = Preferences.getInstance();
		
		SmartDashboard.putString("Enabled?", "1");
		this.distance = distance;
		requires(Robot.kChassis);

		SmartDashboard.putString("Enabled?", "2");
		controller = new PIDVMotion(pref.getDouble("StraightMaxA", 0.0), 
								   pref.getDouble("StraightMaxV", 0.0),
								   pref.getDouble("StraightP", 0.0),
								   pref.getDouble("StraightI", 0.0),
								   pref.getDouble("StraightD", 0.0),
								   pref.getDouble("StraightV", 0.0));										
												  
		SmartDashboard.putString("Enabled?", "3");
		controller.setEndpoint(distance);
		SmartDashboard.putString("Enabled?", "4");
	}
	
	public void initialize() {
		Robot.kChassis.resetEncoders();
	}
	
	public void execute() {
		double srn = 0.001 * (Math.random() - 0.5);
		
		double actualPos = Robot.kChassis.getAverageEncoderFt();
		
		double speed = controller.getOutput(actualPos);
		
		Robot.kChassis.setLeftMotorSpeed(speed);
		Robot.kChassis.setRightMotorSpeed(speed);
		
		
		SmartDashboard.putNumber("Distance", actualPos+srn);
		SmartDashboard.putNumber("Speed", speed);
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
