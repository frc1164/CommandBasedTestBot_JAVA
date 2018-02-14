package org.usfirst.frc.team1164.logic;

import org.usfirst.frc.team1164.robot.RobotMap;

public class PIDController {
	private double prevError;
	private double goal;
	
	private double kP;
	private double kI;
	private double kD;
	
	private double intergral;
	
	private double allowance = 6;
	private double inAllowance = 0;
	
	public PIDController(double kP, double kI, double kD) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		
		prevError = 0;
		intergral = 0;
	}
	
	public void setGoal(double goal) {
		this.goal = goal;
	}
	
	public double getNextVel(double currentPos) {
		double error = goal - currentPos;
		intergral += (error * RobotMap.timeFrame);
		double derivative = (error - prevError) / RobotMap.timeFrame;
		double output = (error * kP) + (intergral * kI) + (derivative * kD);
		prevError = error;
		return output;
	}
	
	public boolean isDone(double currentPos) {
		if (prevError > prevError - allowance && prevError < prevError + allowance) {
			inAllowance++;
		}
		else {
			inAllowance = 0;
		}
		if (inAllowance == 5) {
			return true;
		}
		return false;
	}
}
