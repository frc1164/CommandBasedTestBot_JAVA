package org.usfirst.frc.team1164.logic;

import org.usfirst.frc.team1164.robot.RobotMap;

import edu.wpi.first.wpilibj.filters.LinearDigitalFilter;

public class MotionProfiler {
	
	private PosCounter counter;
	private LinearDigitalFilter filter;
	private double curPos;
	private double curVel;
	
	public MotionProfiler(double aMax, double vMax) {
		
		curPos = 0;
		
		int ffGainLength = (int) (vMax / aMax * RobotMap.timeFrame);
		double[] ffGain = new double[ffGainLength];
		for (@SuppressWarnings("unused") double i : ffGain) {
			i = aMax;
		}
		
		counter = new PosCounter(ffGainLength);
		
		filter = new LinearDigitalFilter(counter, ffGain, null);
	}
	
	public void update() {
		curVel = filter.pidGet();
		curPos += curVel * RobotMap.timeFrame;
	}
	
	public double getVel() {
		return curVel;
	}
	
	public double getPos() {
		return curPos;  // this is what goes to PID controller
	}
	
}
