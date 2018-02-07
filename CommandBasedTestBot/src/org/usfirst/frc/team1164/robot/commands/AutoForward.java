package org.usfirst.frc.team1164.robot.commands;

import org.usfirst.frc.team1164.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AutoForward extends Command{
	private double TargetDistance;
	private double Speed;
	private double distance;
	
	public AutoForward(double TargetDistance, double Speed) {
		requires(Robot.kChassis);
		this.TargetDistance = TargetDistance;
		this.Speed = Speed;
		distance = 0;
	}
	
	public void initialize() {
		
	}
	
	public void execute() {
		distance = Robot.kChassis.DriveForward(TargetDistance, Speed);
	}

	@Override
	protected boolean isFinished() {
		return TargetDistance <= distance;
	}
	
	public void end() {
		Robot.kChassis.Brake();
	}
	
	public void interrupted() {
		
	}

}
