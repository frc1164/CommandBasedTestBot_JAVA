/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1164.robot.commands.Auto;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1164.logic.PID;
import org.usfirst.frc.team1164.logic.Util;
import org.usfirst.frc.team1164.robot.Robot;
import org.usfirst.frc.team1164.robot.RobotMap;


public class findLine extends Command {

  double speed;
  int offset, goal;
  final double tolerance = 0.1;

  private ShuffleboardTab tab = Shuffleboard.getTab("Options");
  private NetworkTableEntry maxSpeed = tab.add("Max Speed", 0.15).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry P, I, D, PIDgoal;
  private PID linePID; 
  public findLine() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.kChassis);
    P = tab.add("kP", 0).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
    I = tab.add("kI", 0).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
    D = tab.add("kD", 0).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
    PIDgoal = tab.add("PID Goal", 0).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
    linePID = new PID(P.getDouble(RobotMap.P_Gain), I.getDouble(RobotMap.I_Gain), D.getDouble(RobotMap.D_Gain));
    System.out.println(this);
  }//end constructor

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    linePID.reset();
    linePID.setGains(P.getDouble(RobotMap.P_Gain), I.getDouble(RobotMap.I_Gain), D.getDouble(RobotMap.D_Gain));
  }//end initialize

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    goal = (int)PIDgoal.getDouble(7500);
    offset = Robot.kLineSensor.getInt();
    speed = 0.01* linePID.update(goal, offset);
    Robot.kChassis.setLeftMotorSpeed(speed);
    Robot.kChassis.setRightMotorSpeed(speed);
  
    SmartDashboard.putNumber("PID Output", linePID.update(goal, offset));
    SmartDashboard.putNumber("offset", offset);
    SmartDashboard.putNumber("Motor Speed", speed);

  }//end execute

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(offset) < tolerance);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }//end end 

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
