package org.usfirst.frc.team1164.robot.commands.Auto;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1164.logic.PIDMotion;
import org.usfirst.frc.team1164.logic.PIDVMotion;
import org.usfirst.frc.team1164.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurn extends Command {
	
	private double TurnAngle;
	private double Speed;
	private PIDMotion turnController;

    public AutoTurn(double TurnAngle, double Speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kChassis);
    	
    	Preferences pref = Preferences.getInstance();
    	
    	this.TurnAngle = TurnAngle;
    	this.Speed = Speed;
    	
    	DriverStation.reportError("construct AutoTurn", true);
    	
    	turnController = new PIDVMotion(pref.getDouble("TurnMaxA", 0.0), 
				   						pref.getDouble("TurnMaxV", 0.0),
				   						pref.getDouble("TurnP", 0.0),
				   						pref.getDouble("TurnI", 0.0),
				   						pref.getDouble("TurnD", 0.0),
				   						pref.getDouble("TurnV", 0.0));	
    	
    }
    
    public AutoTurn(double TurnAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kChassis);
    	
    	Preferences pref = Preferences.getInstance();
    	
    	this.TurnAngle = TurnAngle;
    	//this.Speed = Speed;
    	
    	DriverStation.reportError("construct AutoTurn", true);
    	
    	turnController = new PIDVMotion(pref.getDouble("TurnMaxA", 0.0), 
				   						pref.getDouble("TurnMaxV", 0.0),
				   						pref.getDouble("TurnP", 0.0),
				   						pref.getDouble("TurnI", 0.0),
				   						pref.getDouble("TurnD", 0.0),
				   						pref.getDouble("TurnV", 0.0));	
    	
    	turnController.setEndpoint(TurnAngle);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kChassis.resetNavx();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*
    	SmartDashboard.putNumber("Navx Angle", Robot.kChassis.getNavxAngle());
    	
    	if (TurnAngle > 0) {
    		
    		Robot.kChassis.setRightMotorSpeed(this.Speed);
    		Robot.kChassis.setLeftMotorSpeed(-1*this.Speed);
    		
    	}
    	else if (TurnAngle < 0) {
    		
    		Robot.kChassis.setRightMotorSpeed(-1*this.Speed);
    		Robot.kChassis.setLeftMotorSpeed(this.Speed);
    		
    	}
    	*/
    	
    	double srn = 0.001 * (Math.random() - 0.5);
		
		double actualTurn = Robot.kChassis.getNavxAngle();
		
		double speed = turnController.getOutput(actualTurn);
		
		Robot.kChassis.setLeftMotorSpeed(speed);
		Robot.kChassis.setRightMotorSpeed(-speed);
		
		//SmartDashboard.putNumber("Distance", actualPos+srn);
		//SmartDashboard.putNumber("speed", speed);
		//SmartDashboard.putNumber("LeftEncoder", Robot.kChassis.getLeftEncoder());
		//SmartDashboard.putNumber("RightEncoder", Robot.kChassis.getRightEncoder());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//The navx returns opposite direction of intented
    	/*double currentAngle = -Robot.kChassis.getNavxAngle();
        if (this.TurnAngle > 0 && this.TurnAngle <= currentAngle) {
        	
        	//checks if target angle is positive.
        	
        	//checks if the positive value of the current angle is greater than the positive value of the target angle, 
        	//indicating it has turned a sufficient amount
        	Robot.kChassis.brake();
        	
        	return true;
        }
        else if (this.TurnAngle < 0 && this.TurnAngle >= currentAngle) {
        	
        	//checks if target angle is negative.
        	
        	//checks if the negative value of the current angle is less than the negative value of the target angle, 
        	//indicating it has turned a sufficient amount
        	
        	Robot.kChassis.brake();
        	
        	return true;
        }
        else {
        	// if the target angle has not been reached, the command continues to run
        	
        	return false;
        	}
        */
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
