package org.usfirst.frc.team1164.logic;

import org.usfirst.frc.team1164.robot.RobotMap;

import edu.wpi.first.wpilibj.filters.LinearDigitalFilter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotionProfiler {
	
	private PosCounter counter;
	private LinearDigitalFilter filter;
	private double curPos;
	private double curVel;
	private double endpoint;
	private double aMax;
	private double vMax;
	
	public MotionProfiler(double aMax, double vMax) {
		
		
		// sets the current position of the robot and endpoint
		curPos = 0;
		endpoint = 0;
		this.aMax = aMax;
		this.vMax = vMax;
		SmartDashboard.putString("start", String.format("%f and %f", aMax, vMax));
		
		// creates the acceleration block
		int ffGainLength = (int) (vMax / (aMax * RobotMap.timeFrame));
		SmartDashboard.putString("start2", String.format("%d", ffGainLength));
		double[] ffGain = new double[ffGainLength];
		for (@SuppressWarnings("unused") double i : ffGain) {
			i = aMax;
		}
		
		// creates the position block
		counter = new PosCounter(ffGainLength);
		
		// creates the filter "convultion"
		double[] fbgains = new double[0];
		filter = new LinearDigitalFilter(counter, ffGain, fbgains);
	}
	
	public void update() {
		// does the next convultion step
		curVel = filter.pidGet();
		curPos += curVel * RobotMap.timeFrame;
		SmartDashboard.putString(String.format("%f %f", curVel, curPos), "bad");
	}
	
	public void setEndpoint(double endpoint) {
		// sets the final goal
		this.endpoint = endpoint * RobotMap.footToCm;
		counter.setEndPoint((int) (endpoint/(vMax*RobotMap.timeFrame)));
	}
	
	public double getVel() {
		// return most recent velocity
		return curVel;
	}
	
	public double getPos() {
		// returns most recent position
		return curPos;  
	}
	
}
