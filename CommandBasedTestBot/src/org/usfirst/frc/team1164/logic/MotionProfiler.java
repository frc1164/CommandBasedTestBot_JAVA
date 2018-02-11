package org.usfirst.frc.team1164.logic;
import edu.wpi.first.wpilibj.PIDOutput;


public class MotionProfiler implements PIDOutput {
	private double velocityMax;
	private double accelerationMax;
	private static final double time = 0.002;
	
	
	public MotionProfiler () {
		pidGet();
	}


	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}
}