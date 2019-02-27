/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1164.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team1164.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Victor liftMotor;
  private Encoder liftEncoder;
  private double distance;

public Lift(){

  liftMotor = new Victor(RobotMap.LiftMotor);

  liftEncoder = new Encoder(RobotMap.Lift_Encoder_ChannelA, RobotMap.Lift_Encoder_ChannelB, true, Encoder.EncodingType.k4X);
  liftEncoder.setMinRate(10);
  liftEncoder.setDistancePerPulse(1);
  liftEncoder.reset();

}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
 
  public double getRaw(){

  return liftEncoder.getRaw();
  }

  public double getDistance(){
    return liftEncoder.getDistance();
  }
  public void resetEncoder(){
    liftEncoder.reset();
  }

  public void setLiftSpeed(double speed){

    liftMotor.set(speed);

  }

 
}
