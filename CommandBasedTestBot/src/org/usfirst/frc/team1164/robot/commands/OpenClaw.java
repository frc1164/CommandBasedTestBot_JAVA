package org.usfirst.frc.team1164.robot.commands;

import org.usfirst.frc.team1164.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class OpenClaw extends Command {
	
	public OpenClaw() {
		requires(Robot.kClaw);
	}
	
	public void Initialize() {
		
	}
	
	public void Execute() {
		Robot.kClaw.OpenClaw();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void End() {
		
	}

}
