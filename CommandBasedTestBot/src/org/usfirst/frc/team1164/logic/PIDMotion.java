package org.usfirst.frc.team1164.logic;

import org.usfirst.frc.team1164.robot.RobotMap;

public class PIDMotion {
	
	MotionProfiler MP;
	PIDController P;
	
	public PIDMotion() {
		MP = new MotionProfiler(RobotMap.maxAcceleration, RobotMap.maxVelocity);
		
		P = new PIDController(0.5, 0, 0);
		P.setGoal(0);
	}
	
	public double update() {
		MP.update();
		return P.getNextVel(MP.getPos());
	}
	
	public boolean isDone() {
		return P.isDone(MP.getPos());
	}
}
