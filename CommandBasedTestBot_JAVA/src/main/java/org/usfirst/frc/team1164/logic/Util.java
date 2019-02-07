/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1164.logic;

/**
 * Add your docs here.
 */
public class Util {
    public static double limit(double max, double value){
        if(Math.abs(value) >= max) return max;
        return value;
    }//end limit
}//end class util
