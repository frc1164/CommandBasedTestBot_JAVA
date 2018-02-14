/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1164.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/* For example to map the left and right motors, you could define the
	following variables to use with your drivetrain subsystem.
	public static int leftMotor = 1;
	public static int rightMotor = 2;

	If you are using multiple modules, make sure to define both the port
	number and the module. For example you with a rangefinder:
	public static int rangefinderPort = 1;
	public static int rangefinderModule = 1;
	*/
	
	public static final double kDistancePerPulse = 0.249364;
	public static final double timeFrame = 0.002; //seconds
	
// base robot options
	// Subsystem names
	/** 
	 * These are defined in robot/Robot.java
	 * Chassis = kChassis
	 * Claw = kClaw
	 * Winch = kWinch
	 */
	
	public static final double maxAcceleration = 2;
	public static final double maxVelocity = 2;
	
	
// controller options
	
// chassis options
	// victors
	public static int CHV_Left_1 = 2;
	public static int CHV_Left_2 = 3;
	
	public static int CHV_Right_1 = 1;
	public static int CHV_Right_2 = 4;
	
	// encoders
	public static int CHE_Left_channelA = 0;
	public static int CHE_Left_channelB = 1;
	public static boolean CHE_Left_reversed = true;
	
	public static int CHE_Right_channelA = 2;
	public static int CHE_Right_channelB = 3;
	public static boolean CHE_Right_reversed = false;
	
// claw options
	public static int CL_sol0ID = 0;
	public static int CL_sol1ID = 1;
	public static int CL_canID = 0;
	
// winch options
	//victors
	public static int WIV_winch1 = 5;
	public static int WIV_winch2 = 6;
	public static int WIV_winch3 = 7;
}
