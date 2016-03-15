package org.usfirst.frc.team6201.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * 
 * @author David Matthews
 * 
 * called during auto phase. how we plan to tackle this mission here. 
 *
 */
public class Auto extends CommandGroup {
    
    public  Auto() {
    	//Originally was 2 sec. may be lower due to going into 1/2 way to wall. 
    	addSequential(new DriveTimeCmd(2.0));
	}
}
