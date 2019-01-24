/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1164.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 * Add your docs here.
 */
public class LineSensor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static final int BAUDRATE = 9600;
  private SerialPort arduino = new SerialPort(BAUDRATE, SerialPort.Port.kUSB1);

  public LineSensor(){

  }// of constructor

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void getRaw(){
    //print raw serial data
    //System.out.println(arduino.readString());

    //validation serial data
    //format: int1  int2  int3  int4  int5  pos
    String recevedString = arduino.readString();
    int[] sensorValues = new int[5];

    try {
      for(int i = 0; i < 5; i++){//read data into an array
        sensorValues[i] = Integer.parseInt(receivedString.substring(0,receivedString.indexOf('\t')));
        receivedString = recevedString.substring(receivedString.indexOf('\t'));
        System.out.print(sensorValues[i]);
      }// end data processing
      System.out.println();
    } catch (StringIndexOutOfBoundsException e) {
      //TODO: handle exception
      return;
    }// end try_catch
  
  }// of method getRaw

  
}// of Subsystem LineSeneor
