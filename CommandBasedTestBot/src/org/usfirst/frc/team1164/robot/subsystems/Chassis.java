package org.usfirst.frc.team1164.robot.subsystems;

import org.usfirst.frc.team1164.robot.OI;
import org.usfirst.frc.team1164.robot.RobotMap;
import org.usfirst.frc.team1164.robot.commands.DriveTankWithJoystick;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;



public class Chassis extends Subsystem {
	private Victor Right1, Right2, Left1, Left2;
	private Encoder LeftEncoder, RightEncoder;
	

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveTankWithJoystick());
	}
	
	public Chassis() {
		Left1 = new Victor(RobotMap.CHV_Left_1);
		Left2 = new Victor(RobotMap.CHV_Left_2);
		Right1 = new Victor(RobotMap.CHV_Right_1);
		Right2 = new Victor(RobotMap.CHV_Right_2);
		
		LeftEncoder = new Encoder(RobotMap.CHE_Left_channelA, RobotMap.CHE_Left_channelB,
				RobotMap.CHE_Left_reversed, Encoder.EncodingType.k2X);
		RightEncoder = new Encoder(RobotMap.CHE_Right_channelA, RobotMap.CHE_Right_channelB, 
				RobotMap.CHE_Right_reversed, Encoder.EncodingType.k2X);
		
		LeftEncoder.reset();
		RightEncoder.reset();
		
		Left1.setInverted(true);
		Left2.setInverted(true);
	}
	
	public void DriveStick() {
		double LeftStick = OI.getJoystick().getRawAxis(1);
		double RightStick = OI.getJoystick().getRawAxis(3);
		if (LeftStick < -0.05 || LeftStick > 0.05) {
			Left1.set(LeftStick);
			Left2.set(LeftStick);
		} else {
			Left1.set(0.0);
			Left2.set(0.0);
		}
		
		if (RightStick < -0.05 || RightStick > 0.05) {
			Right1.set(RightStick);
			Right2.set(RightStick);
		} else {
			Right1.set(0.0);
			Right2.set(0.0);
		}
	}
	
	public void Brake() {
		Right1.set(0);
		Right2.set(0);
		Left1.set(0);
		Left2.set(0);
	}
	
	public double DriveForward(double TargetDistance, double speed) {
		double EncoderAvg = (LeftEncoder.getDistance() + RightEncoder.getDistance()) / 2;
		double inches = EncoderAvg/RobotMap.kEncoder;
		
		// required smartdash board info
		
		Right1.set(speed);
		Right2.set(speed);
		Left1.set(speed);
		Left2.set(speed);
		
		return inches;
	}
	
	public void ResetEncoders() {
		LeftEncoder.reset();
		RightEncoder.reset();
	}
}