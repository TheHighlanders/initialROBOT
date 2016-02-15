package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author David Matthews
 * 
 * sets the robot to think that the foward is the front.
 *
 */
public class FowardDriveCmd extends Command {

    public FowardDriveCmd() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.fowardOrReverse = -1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;

    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
