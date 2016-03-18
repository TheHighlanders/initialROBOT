package org.usfirst.frc.team6201.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@author Adriana Massie 
 *
 */
public class AutoReachDefenseCmdGrp extends CommandGroup {
    
    public  AutoReachDefenseCmdGrp() {
    	addSequential (new AutoPneumaticsDownCmd());
    	addSequential(new DriveTimeCmd(0.6));
    }
}
