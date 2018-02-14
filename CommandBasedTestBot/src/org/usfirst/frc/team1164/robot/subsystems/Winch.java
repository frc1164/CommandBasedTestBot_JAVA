package org.usfirst.frc.team1164.robot.subsystems;

import org.usfirst.frc.team1164.robot.OI;
import org.usfirst.frc.team1164.robot.commands.RunWinchWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {
	private Victor Winch1, Winch2, Winch3;
	private Joystick Stick;
	
	public Winch() {
		Winch1 = new Victor(5);
		Winch2 = new Victor(6);
		Winch3 = new Victor(7);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunWinchWithJoystick());
	}
	
	
	public void driveWinch() {
		double StickValue = Stick.getRawAxis(2);
		
		if (OI.getJoystick().getRawButton(2)) {
			Winch1.set(1);
			Winch2.set(1);
			Winch3.set(1);
		}
		
		else {
			Winch1.set(StickValue);
			Winch2.set(StickValue);
			Winch3.set(StickValue);
		}
	}
}
