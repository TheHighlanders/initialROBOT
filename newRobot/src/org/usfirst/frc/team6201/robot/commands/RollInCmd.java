package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 *@author David Matthews
 *@author Max Nadeau
 *
 *Sets the rollers to pick up the ball
 */
public class RollInCmd extends Command {

    public RollInCmd() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.roller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.roller.timer.reset();
    	Robot.roller.timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	Robot.roller.roll(RobotMap.PICKUP_BALL);
    }

    // Make this return true when this Command no longer needs to run execute()
 // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.roller.timer.get()>4.0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.roller.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
