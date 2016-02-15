package org.usfirst.frc.team6201.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author David Matthews
 * 
 * calls the needed commands to switch what we will think of as the front of the robot
 * 
 * switches the output of the controller,
 * and switches the camera we are receiving feed from
 *
 */

public class CameraAndDriveCmdGrp extends CommandGroup {
    
    public  CameraAndDriveCmdGrp() {
    	
    	addParallel(new ArcadeDriveCmd());
    	addParallel(new CameraRunCmd());
    }
}
