package org.usfirst.frc.team1164.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1164.robot.Robot;

/**
 *
 */
public class SetTransmissionHigh extends Command {

    public SetTransmissionHigh() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kChassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kChassis.SetHighGear();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
