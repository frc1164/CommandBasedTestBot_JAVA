/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1164.robot;

import org.usfirst.frc.team1164.robot.commands.CloseClaw;
import org.usfirst.frc.team1164.robot.commands.OpenClaw;
import org.usfirst.frc.team1164.robot.commands.SetTransmissionHigh;
import org.usfirst.frc.team1164.robot.commands.SetTransmissionLow;
import org.usfirst.frc.team1164.robot.commands.EjectBall;
import org.usfirst.frc.team1164.robot.commands.DropEndEffector;
import org.usfirst.frc.team1164.robot.commands.ExtendHatchGrabber;
import org.usfirst.frc.team1164.robot.commands.GrabBall;
import org.usfirst.frc.team1164.robot.commands.RetractHatchGrabber;
import org.usfirst.frc.team1164.robot.commands.RaiseEndEffector;
import org.usfirst.frc.team1164.robot.commands.Brake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static int remotePort = 0;
	private static Joystick stick = new Joystick(remotePort);
	public static Joystick operator = new Joystick(1);
	Button buttonOpenClaw = new JoystickButton(stick, 1);
	Button buttonCloseClaw = new JoystickButton(stick, 3);

	Button buttonTransmissionHigh = new JoystickButton(stick, 6);
	Button buttonTransmissionLow = new JoystickButton(stick, 5);

	Button DropEndEffector = new JoystickButton(operator, 2);
	Button RaiseEndEffector = new JoystickButton(operator, 3);
	Button ExtendHatchGrabber = new JoystickButton(operator, 1);
	Button RetractHatchGrabber = new JoystickButton(operator, 4);
	

	public static int leftPort = 1;
	public static int rightPort = 5;
	
	public OI() {
		
		DropEndEffector.whileHeld(new DropEndEffector());
		RaiseEndEffector.whenPressed(new RaiseEndEffector());
		ExtendHatchGrabber.whenPressed(new ExtendHatchGrabber());
		RetractHatchGrabber.whenPressed(new RetractHatchGrabber());
		
	}
	
	public static Joystick getJoystick() {
		return stick;
	}
	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
