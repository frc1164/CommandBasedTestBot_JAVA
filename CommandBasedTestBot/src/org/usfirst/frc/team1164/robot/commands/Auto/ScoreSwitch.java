package org.usfirst.frc.team1164.robot.commands.Auto;
import org.usfirst.frc.team1164.robot.commands.Auto.AutoTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1164.robot.commands.OpenClaw;
/**
 *
 */
public class ScoreSwitch extends CommandGroup {

	public static final boolean RIGHT = true;
	public static final boolean LEFT = false;
	
    public ScoreSwitch(boolean side) {
    	if (side == RIGHT) {
    		addSequential(new DriveForward(366, .5));
    		addSequential(new AutoTurn(45.0,.25));
    		addSequential(new OpenClaw());
    	}
    	else {
    		addSequential(new DriveForward(366, .5));
    		addSequential(new AutoTurn(-45.0, .25));
    		addSequential(new OpenClaw());
    	}
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
