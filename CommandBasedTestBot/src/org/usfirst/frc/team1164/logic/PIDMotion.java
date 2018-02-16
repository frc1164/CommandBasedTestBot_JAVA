package org.usfirst.frc.team1164.logic;

import org.usfirst.frc.team1164.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDMotion {
	
	MotionProfiler MP;
	PIDController P;
	
	public PIDMotion(double maxAcceleration, double maxVelocity, double kP, double kI, double kD) {
		
		MP = new MotionProfiler(maxAcceleration, maxVelocity);
		
		P = new PIDController(kP, kI, kD);
		P.setNextPoint(0);
	}
	
	public double getOutput(double actualPos) {
		MP.update();
		P.setNextPoint(MP.getPos());
		return P.getOutput(actualPos);
	}
	
	public void setEndpoint(double goal) {
		MP.setEndpoint(goal);
	}
	
	
	public boolean isDone(int deadbandAllowance) {
		return P.isDone(deadbandAllowance);
	}
}
