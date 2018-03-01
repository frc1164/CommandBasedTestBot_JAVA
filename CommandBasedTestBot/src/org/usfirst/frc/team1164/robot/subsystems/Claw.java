package org.usfirst.frc.team1164.robot.subsystems;

import org.usfirst.frc.team1164.robot.RobotMap;
import org.usfirst.frc.team1164.robot.commands.AutoClaw;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;

public class Claw extends Subsystem{
	@SuppressWarnings("unused")
	private Compressor ClawCompressor;
	private Solenoid sol0, sol1;
	private AnalogInput sonic;
	

	public Claw() {
		ClawCompressor = new Compressor();
		sol0 = new Solenoid(RobotMap.CL_canID, RobotMap.CL_sol0ID);
		sol1 = new Solenoid(RobotMap.CL_canID, RobotMap.CL_sol1ID);
		
		sonic = new AnalogInput(3);
		
		sol0.set(false);
		sol1.set(true);
	}
	
	

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new AutoClaw());
	}
	
	public void Close() {
		sol0.set(false);
		sol1.set(true);
	}
	
	public void Open() {
		sol0.set(true);
		sol1.set(false);
	}
	
	public double GetUltrasonicVolts() {
		return sonic.getVoltage();
	}


}
