package org.usfirst.frc.team1164.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1164.robot.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalOutput;

/**
 *
 */
public class CustomDriveWithXbox extends Command {
	
	private double LStickValue;
	private double RStickValue;
	private double LTriggerValue;
	private double RTriggerValue;
	
	
	private DigitalOutput Arduino = new DigitalOutput(5);
	private double[] Motors = {0,0};



    public CustomDriveWithXbox() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kChassis);
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.LStickValue = OI.getJoystick().getRawAxis(RobotMap.LAxis);
    	this.RStickValue = OI.getJoystick().getRawAxis(RobotMap.RAxis);
    	this.LTriggerValue = OI.getJoystick().getRawAxis(RobotMap.LTriggerAxis);
    	this.RTriggerValue = OI.getJoystick().getRawAxis(RobotMap.RTriggerAxis);

		//Drive forward and backward
		this.Motors[0] = this.RTriggerValue - this.LTriggerValue;
		this.Motors[1] = this.RTriggerValue - this.LTriggerValue;

		//Turning slowly (Assuming LAxis is the slow turning axis)
		this.Motors[0] = (1 - this.RStickValue) * this.Motors[0];
		this.Motors[1] = (1 + this.RStickValue) * this.Motors[3];

		//Turning quickly (Assuming RAxis is the fast turning axis)
		this.Motors[0] = this.Motors[0] - (0.5 * this.LStickValue);
		this.Motors[1] = this.Motors[1] + (0.5 * this.LStickValue);
		
		Robot.kChassis.setRightMotorSpeed(this.Motors[0]);
		Robot.kChassis.setLeftMotorSpeed(this.Motors[1]);

		SmartDashboard.putNumberArray("Motor Values", this.Motors);
		
		if (OI.getJoystick().getRawButton(6) == true){
			Arduino.set(true);
		}
		else {
			Arduino.set(false);
		}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
