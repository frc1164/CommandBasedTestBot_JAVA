package org.usfirst.frc.team1164.logic;

public class PIDVMotion extends PIDMotion {
	
	private double kV;
	
	public PIDVMotion (double maxAcceleration, double maxVelocity, double kP, double kI, double kD, double kV) {
		super(maxAcceleration, maxVelocity, kP, kI, kD);
		this.kV = kV;
	}
	
	public double getOutput(double actualPos) {
		MP.update();
		P.setNextPoint(MP.getPos());
		return P.getOutput(actualPos) + (MP.getVel() * kV);
	}
}
