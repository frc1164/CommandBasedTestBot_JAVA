package org.usfirst.frc.team1164.robot.commands;

import org.usfirst.frc.team1164.robot.Robot;
import org.usfirst.frc.team1164.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class CloseClaw extends Command{
	
	
	public CloseClaw() {
		requires(Robot.kClaw);
	}
	
	public void initialize() {
		
	}
	
	public void execute() {
		Robot.kClaw.Close();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	public void end() {
		
	}
	
	public void interrupted() {
		
	}
	
	
}
