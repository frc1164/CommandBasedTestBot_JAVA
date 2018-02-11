package org.usfirst.frc.team1164.logic;

import org.usfirst.frc.team1164.robot.commands.Auto.AutoRun;
import org.usfirst.frc.team1164.robot.commands.Auto.AutoTurn;
import org.usfirst.frc.team1164.robot.commands.Auto.MidSwitch;
import org.usfirst.frc.team1164.robot.commands.Auto.ScoreScale;
import org.usfirst.frc.team1164.robot.commands.Auto.ScoreSwitch;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class autoDecissionMattrix {
	public static Command decide(int mode, String gameData) {
		Command autoCommand;
		
		if (mode == 1) {
			if(gameData.charAt(0) == 'L') {
				SmartDashboard.putString("AutoCommand", "Score Switch Right");
				autoCommand = new ScoreSwitch(ScoreSwitch.LEFT);
			} 	
			else if (gameData.charAt(1) == 'L') {
				SmartDashboard.putString("AutoCommand", "Score Scale Right");
				autoCommand = new ScoreScale(ScoreScale.LEFT);
			} 
			else {
				SmartDashboard.putString("AutoCommand", "AutoRun");
				autoCommand = new AutoRun();
			}
		}
		else if (mode == 2) {
			if(gameData.charAt(0) == 'R') {
				SmartDashboard.putString("AutoCommand", "MidSwitch");
				autoCommand = new MidSwitch();
			}
			else {
				SmartDashboard.putString("AutoCommand", "AutoRun");
				autoCommand = new AutoRun();
			}
		}
		else if (mode == 3) {
			if(gameData.charAt(0) == 'R') {
				SmartDashboard.putString("AutoCommand", "Score Switch Left");
				autoCommand = new ScoreSwitch(ScoreSwitch.RIGHT);
			} 
			else if (gameData.charAt(1) == 'R') {
				SmartDashboard.putString("AutoCommand", "Score Scale Left");
				autoCommand = new ScoreScale (ScoreScale.RIGHT);
			} 
			else {
				SmartDashboard.putString("AutoCommand", "AutoRun");
				autoCommand = new AutoRun();
			}
		}
		else {
			autoCommand = new AutoTurn(90, 0.1);
		}
		return autoCommand;
	}
}
