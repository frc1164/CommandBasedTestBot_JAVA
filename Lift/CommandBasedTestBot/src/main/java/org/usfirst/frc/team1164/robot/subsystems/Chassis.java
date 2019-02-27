package org.usfirst.frc.team1164.robot.subsystems;

import org.usfirst.frc.team1164.robot.OI;
import org.usfirst.frc.team1164.robot.RobotMap;
import org.usfirst.frc.team1164.robot.commands.DriveTankWithJoystick;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;




public class Chassis extends Subsystem {
	private Victor Right1, Right2, Left1, Left2;
	private Encoder LeftEncoder, RightEncoder;
	private AHRS Navx;
	private DoubleSolenoid Transmission;
	

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveTankWithJoystick());
	}
	
	public Chassis() {
		Left1 = new Victor(RobotMap.CHV_Left_1);
		Left2 = new Victor(RobotMap.CHV_Left_2);
		Right1 = new Victor(RobotMap.CHV_Right_1);
		Right2 = new Victor(RobotMap.CHV_Right_2);
		
		Transmission = new DoubleSolenoid(RobotMap.CHT_Forward_channel, RobotMap.CHT_Reverse_channel);
		
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
		
		Right1.setInverted(RobotMap.CHV_RightInverted);
		Right2.setInverted(RobotMap.CHV_RightInverted);
		Left1.setInverted(RobotMap.CHV_LeftInverted);
		Left2.setInverted(RobotMap.CHV_LeftInverted);
		
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
	
	
	public void ResetEncoders() {
		LeftEncoder.reset();
		RightEncoder.reset();
	}
	
	public double GetLeftEncoder() {
		return LeftEncoder.getDistance();
	}
	
	public double GetRightEncoder() {
		return RightEncoder.getDistance();
	}
	public double GetNavxAngle() {
		return Navx.getAngle();
	}
	public void ResetNavx() {
		Navx.reset();
	}
	

	public void SetLowGear() {
		Transmission.set(DoubleSolenoid.Value.kForward);
	}
	
	public void SetHighGear() {
		Transmission.set(DoubleSolenoid.Value.kReverse);
	}
}