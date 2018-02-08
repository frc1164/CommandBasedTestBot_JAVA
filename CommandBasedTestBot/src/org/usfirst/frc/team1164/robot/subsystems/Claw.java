package org.usfirst.frc.team1164.robot.subsystems;

import org.usfirst.frc.team1164.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem{
	@SuppressWarnings("unused")
	private Compressor ClawCompressor;
	private Solenoid sol0, sol1;
	
	private static final int sol0ID = RobotMap.CL_sol0ID;
	private static final int sol1ID = RobotMap.CL_sol1ID;
	private static final int canID = RobotMap.CL_canID;
	

	public Claw() {
		ClawCompressor = new Compressor();
		sol0 = new Solenoid(canID, sol0ID);
		sol1 = new Solenoid(canID, sol1ID);
	}
	

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void Close() {
		sol0.set(true);
		sol1.set(false);
	}
	
	public void Open() {
		sol0.set(false);
		sol1.set(true);
	}


}
