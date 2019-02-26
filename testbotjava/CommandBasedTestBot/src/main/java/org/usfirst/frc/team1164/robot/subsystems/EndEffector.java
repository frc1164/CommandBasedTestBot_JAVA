/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1164.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;



/**
 * Add your docs here.
 */
public class EndEffector extends Subsystem {
  VictorSP Victor0;
  DoubleSolenoid Sol0, Sol1;
  DigitalInput Limitswitch0, Limitswitch1;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {

  
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public EndEffector(){
    Victor0 = new VictorSP(7);
    Sol0 = new DoubleSolenoid(0,1);
    Sol1 = new DoubleSolenoid(2,3);
    Limitswitch0 = new DigitalInput(4);
    Limitswitch1 = new DigitalInput(5);
  }

  public void DropEndEffector() {
    Sol0.set(Value.kForward);
  }

  public void RiseEndEffector() {
    Sol0.set(Value.kReverse);
  }
  
  public void ExtendHatchGrabber() {
     Sol1.set(Value.kForward);
  }

  public void RetractHatchGrabber() {
     Sol1.set(Value.kReverse);
  }

  public void GrabBall(double speed) {
    Victor0.set(speed);
  }

  public void EjectBall(double speed) {
    Victor0.set(-speed);
  }

  public boolean Ballstop() {
    return LimitSwitch0.get();
  }

  public boolean EndEffectorStop() {
    return LimitSwitch1.get();
  }

  public void Brake() {
    Victor0.set(0);
  }
}


