/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1164.robot.commands.Auto;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1164.logic.MotionProfiler;
import org.usfirst.frc.team1164.logic.PID;
import org.usfirst.frc.team1164.robot.Robot;


public class MakeLiftGoUp extends Command {

  private MotionProfiler liftProfiler;
  private PID liftPID;

  private double r;

  private double out,speed;

  private double p,i,d;

  private double v, vmax, amax;
  


  public double K_V;
  public double setPoint = 24;
  public MakeLiftGoUp() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.kLift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    vmax = Robot.kLift.V_MAX.getDouble(0.0);
    amax = Robot.kLift.A_MAX.getDouble(0.0);
    p = Robot.kLift.kP.getDouble(0.0);
    i = Robot.kLift.kI.getDouble(0.0);
    d = Robot.kLift.kD.getDouble(0.0);
    K_V = Robot.kLift.kV.getDouble(0.0);

    liftProfiler = new MotionProfiler(amax, vmax);
    liftPID = new PID(p,i,d);

    liftPID.setGains(p, i, d);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // profiler set setpoit(setpoint)
    // profiler.update()
    // r = profiler.getPos()
    // output = pid.update(r, encoder);
    // output = output + (kv * profiler.getVel());
    //
    // do stuff with output to motor
    
    liftProfiler.setEndpoint(setPoint) ;//Tells profiler where you want it to go
    liftProfiler.update(); //updates references 

    r = liftProfiler.getPos(); //Incremental update on where it wants to be
    SmartDashboard.putNumber("Motion Profiler Output", r);
    out = liftPID.update(r, Robot.kLift.getDistance()); //Updates references and current positions
    speed = out + (K_V * liftProfiler.getVel()); //
    SmartDashboard.putNumber("Current Speed", speed);
    Robot.kLift.setLiftSpeed(speed); 
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
