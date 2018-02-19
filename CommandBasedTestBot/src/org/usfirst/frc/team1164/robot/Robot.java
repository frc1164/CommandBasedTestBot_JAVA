/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1164.robot;

import org.usfirst.frc.team1164.logic.autoDecissionMattrix;
import org.usfirst.frc.team1164.robot.commands.Auto.AutoTurn;
import org.usfirst.frc.team1164.robot.commands.Auto.DriveForward;
import org.usfirst.frc.team1164.robot.subsystems.Chassis;
import org.usfirst.frc.team1164.robot.subsystems.Claw;
import org.usfirst.frc.team1164.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static final Chassis kChassis = new Chassis();
	public static final Claw kClaw = new Claw();
	public static final Winch kWinch = new Winch();
	
	public static OI m_oi;

//	private Command m_autonomousCommand;
	
//	private Command autoForward;
	//private Command autoCommand;
	private Command autocommand;
	
	
	private int mode = 1;
	private SendableChooser<Integer> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();

		m_chooser.addDefault("Position 1", 1);
		m_chooser.addObject("Position 2", 2);
		m_chooser.addObject("Position 3", 3);
		m_chooser.addObject("Testing", 4);
		SmartDashboard.putData("Positions", m_chooser);

		
	}
	
	public void robotPeriodic() {
		SmartDashboard.putString("Encoder Avg",String.format("%f", kChassis.getAverageEncoderFt()));
		SmartDashboard.putString("NavX Angle",String.format("%f", kChassis.getNavxAngle()));
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		mode = m_chooser.getSelected();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		//autocommand = autoDecissionMattrix.decide(mode, gameData);
		SmartDashboard.putString("Enabled?", "NO");
		Preferences pref = Preferences.getInstance();
		autocommand = new DriveForward(pref.getDouble("DistanceFt", 0.0));
		//autocommand = new AutoTurn(pref.getDouble("TurnDeg", 0.0));
		
		
		if (autocommand != null) {
			autocommand.start();
		}
		
	}
	

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autocommand != null) {
			autocommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		SmartDashboard.putNumber("Left Encoder", kChassis.getLeftEncoder());
		SmartDashboard.putNumber("Right Encoder", kChassis.getRightEncoder());
	}
}
