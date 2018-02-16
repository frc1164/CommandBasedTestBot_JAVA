package org.usfirst.frc.team1164.robot.subsystems;

import org.usfirst.frc.team1164.robot.RobotMap;
import org.usfirst.frc.team1164.robot.commands.DriveTankWithJoystick;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;




public class Chassis extends Subsystem {
	private Victor Right1, Right2, Left1, Left2;
	private Encoder LeftEncoder, RightEncoder;
	private AHRS Navx;
	
	
	

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveTankWithJoystick());
	}
	
	public Chassis() {
		Left1 = new Victor(RobotMap.CHV_Left_1);
		Left2 = new Victor(RobotMap.CHV_Left_2);
		Right1 = new Victor(RobotMap.CHV_Right_1);
		Right2 = new Victor(RobotMap.CHV_Right_2);
		try {
			Navx = new AHRS(SPI.Port.kMXP);
		}
		catch (RuntimeException ex){
			DriverStation.reportError("could not connect to Navx: " + ex.getMessage(), true);
			
		}
		LeftEncoder = new Encoder(RobotMap.CHE_Left_channelA, RobotMap.CHE_Left_channelB,
				RobotMap.CHE_Left_reversed, Encoder.EncodingType.k2X);
		RightEncoder = new Encoder(RobotMap.CHE_Right_channelA, RobotMap.CHE_Right_channelB, 
				RobotMap.CHE_Right_reversed, Encoder.EncodingType.k2X);
		
		LeftEncoder.reset();
		RightEncoder.reset();
		LeftEncoder.setDistancePerPulse(RobotMap.kDistancePerPulse);
		RightEncoder.setDistancePerPulse(RobotMap.kDistancePerPulse);
		
		Right1.setInverted(true);
		Right2.setInverted(true);
		
		Navx.reset();
		
	}
	
	public void setLeftMotorSpeed(double speed) {
		Left1.set(speed);
		Left2.set(speed);
	}
	
	public void setRightMotorSpeed(double speed) {
		Right1.set(speed);
		Right2.set(speed);
	}
	
	
	public void resetEncoders() {
		LeftEncoder.reset();
		RightEncoder.reset();
	}
	
	public double getLeftEncoder() {
		return LeftEncoder.getDistance();
	}
	
	public double getRightEncoder() {
		return RightEncoder.getDistance();
	}
	public double getAverageEncoder() {
		return (LeftEncoder.getDistance() * RightEncoder.getDistance())/2;
	}
	
	public double getNavxAngle() {
		return Navx.getAngle();
	}
	public void resetNavx() {
		Navx.reset();
	}
	public void brake() {
		Right1.set(0);
		Right2.set(0);
		Left1.set(0);
		Left2.set(0);
	}
}