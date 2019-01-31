/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1164.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.concurrent.DelayQueue;
import java.util.regex.Pattern;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 * Add your docs here.
 */
public class LineSensor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static final int BAUDRATE = 9600;
  private static final int bufferSize = 5;
  private SerialPort arduino = new SerialPort(BAUDRATE, SerialPort.Port.kUSB1);
  private static Double prevValue = 0.0;
  public LineSensor(){
    arduino.setReadBufferSize(bufferSize);
  }// of constructor

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public String getString(){
    //return String from buffer, of length MaxStringLength
    //String string = new String(getRaw(), arduino.getBytesReceived() - (bufferSize+1), bufferSize);
    String string = arduino.readString();
    return string;
  }// of method getRaw

  public byte[] getRaw(){
    //return raw data as byte array
    return arduino.read(arduino.getBytesReceived());
  }//end getRaw

  public Double getDouble(){
    String dataIn = getString();
    Double parseData;
    if(dataIn.indexOf('-') == -1){// if data in is positive
      if(dataIn.indexOf('%') == 0){
        parseData = Double.parseDouble(dataIn.substring(1,4));
      } else if(dataIn.indexOf('%') == 4){
        parseData = Double.parseDouble(dataIn.substring(0, 3));
      } else{
        return prevValue;
      }//end inner else_if 
    } else{//if data in is negative
      if(dataIn.charAt(0) == '-'){
        parseData = Double.parseDouble(dataIn.substring(0,3));
      } else{
        return prevValue;
      }//end inner if_else
    }//end outer if_else
    prevValue = parseData;
    return parseData;
  }//end getDouble
  
}// of Subsystem LineSeneor
