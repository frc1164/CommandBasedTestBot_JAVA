package org.usfirst.frc.team1164.robot.subsystems;

import org.usfirst.frc.team1164.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem{
	private DoubleSolenoid ClawSolenoid;
	
	

	public Claw() {
		ClawSolenoid = new DoubleSolenoid(RobotMap.CL_solForwardID, RobotMap.CL_solReverseID);
		ClawSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void Close() {
		ClawSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void Open() {
		ClawSolenoid.set(DoubleSolenoid.Value.kForward);
	}


}
