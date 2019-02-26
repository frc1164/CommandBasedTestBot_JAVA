package org.usfirst.frc.team1164.robot.commands;

import org.usfirst.frc.team1164.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RunWinchWithJoystick extends Command {
	public RunWinchWithJoystick() {
		requires(Robot.kWinch);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
