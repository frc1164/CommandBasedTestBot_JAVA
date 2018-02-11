package org.usfirst.frc.team1164.robot.subsystems;

import org.usfirst.frc.team1164.robot.OI;
import org.usfirst.frc.team1164.robot.RobotMap;
import org.usfirst.frc.team1164.robot.commands.DriveTankWithJoystick;
import org.usfirst.frc.team1164.robot.commands.CustomDriveWithXbox;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;




public class Chassis extends Subsystem {
	private Victor Right1, Right2, Left1, Left2;
	private Encoder LeftEncoder, RightEncoder;
	private AHRS Navx;
	

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CustomDriveWithXbox());
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
		
		Right1.setInverted(RobotMap.CHV_Right_Invert);
		Right2.setInverted(RobotMap.CHV_Right_Invert);
		
		Left1.setInverted(RobotMap.CHV_Left_Invert);
		Left2.setInverted(RobotMap.CHV_Left_Invert);
		
		Navx.reset();
		
	}
	
	public void setLeftMotorSpeed(double speed) {
		Left1.set(speed*RobotMap.speedReducer);
		Left2.set(speed*RobotMap.speedReducer);
	}
	
	public void setRightMotorSpeed(double speed) {
		Right1.set(speed*RobotMap.speedReducer);
		Right2.set(speed*RobotMap.speedReducer);
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
	public void Brake() {
		Right1.set(0);
		Right2.set(0);
		Left1.set(0);
		Left2.set(0);
	}
	
	public void DriveMotors(double[] Motors) {
		Right1.set(Motors[0]);
		Right2.set(Motors[1]);
		Left1.set(Motors[2]);
		Left2.set(Motors[3]);

		
	}
}