
package org.usfirst.frc.team6201.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6201.robot.Robot;

/**
 *@author David Matthews
 *
 */
public class TankDriveCmd extends Command {


	
    public TankDriveCmd() {
       requires(Robot.dt);
    }

    protected void initialize() {
    }

    protected void execute() {

    	
    	Robot.dt.driveLR(Robot.oi.getLeftJoystickY(), Robot.oi.getRightJoystickY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	this.end();
    
    }
}
