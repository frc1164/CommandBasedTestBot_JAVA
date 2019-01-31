/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1164.robot.commands.Auto;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import org.usfirst.frc.team1164.robot.Robot;
import org.usfirst.frc.team1164.robot.RobotMap;


public class findLine extends Command {

  double offset;
  final double tolerance = 0.1;

  private ShuffleboardTab tab = Shuffleboard.getTab("Options");
  private NetworkTableEntry maxSpeed = tab.add("Max Speed", 0.15).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
  
  public findLine() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.kChassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
 
    offset = Robot.kLineSensor.getDouble();
    System.out.println(offset + " " + (Math.signum(offset) * maxSpeed.getDouble(0.15)));
    Robot.kChassis.setLeftMotorSpeed(Math.signum(offset) * maxSpeed.getDouble(0.15));
    Robot.kChassis.setRightMotorSpeed(Math.signum(offset) * maxSpeed.getDouble(0.15));
  
  }//end execute

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;//(Math.abs(offset) < tolerance);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("Terminated");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
