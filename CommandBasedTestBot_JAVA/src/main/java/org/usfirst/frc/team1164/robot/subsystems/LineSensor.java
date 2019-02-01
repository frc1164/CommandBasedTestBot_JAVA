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
  private int bufferSize = 5;
  private SerialPort arduino;
  private Double prevValue;
  private char endLineChar;
  private int maxStringLen;
  public LineSensor(){
    arduino = new SerialPort(BAUDRATE, SerialPort.Port.kUSB1);
    arduino.setReadBufferSize(bufferSize);
    prevValue = 0.0;
    endLineChar = '%';
    maxStringLen = 6;
  }// of default constructor

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public String getString(){
    //return String from buffer
    String string = arduino.readString();
    return string;
  }// of method getRaw

  public byte[] getRaw(){
    //return raw data as byte array
    return arduino.read(arduino.getBytesReceived());
  }//end getRaw
  /**
   * <p>Read in a Double value from the Serial Line</p>
   * 
   * @return the parsed double from the Serial line
   */
  public Double getDouble(){
    String dataIn = getString();
    Double parseData;
    if(dataIn.indexOf('-') == -1){// if data in is positive
      if(dataIn.indexOf(endLineChar) == 0){
        parseData = Double.parseDouble(dataIn.substring(1,4));
      } else if(dataIn.indexOf(endLineChar) == 4){
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

  public Double newGetDouble(){
    String dataIn = getString();
    Double parseData;
    if(dataIn.length() < maxStringLen*2) return prevValue; //if data is too short to parse use last recorded value

    int index;
    index = dataIn.lastIndexOf(endLineChar);
    dataIn = dataIn.substring(0,index);
    index = dataIn.lastIndexOf(endLineChar);
    dataIn = dataIn.substring(index+1);
    return Double.parseDouble(dataIn);
    }//end robustGetDouble
  
}// of Subsystem LineSeneor
