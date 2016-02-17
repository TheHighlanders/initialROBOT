package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Max Nadeau
 *@author David Matthews
 *
 *Tells the robot to release the ball
 */
public class RollOutCmd extends Command {

    public RollOutCmd() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.roller);
    }

 // Called just before this Command runs the first time
    protected void initialize() {

    	Robot.roller.timer.reset();
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.roller.timer.start();
    	Robot.roller.roll(RobotMap.RELEASE_BALL);
    }

    // Make this return true when this Command no longer needs to run execute()
 // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.roller.timer.get() >= 2);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.roller.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
