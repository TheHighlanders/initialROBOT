package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPneumaticsDownCmd extends Command {
	
	private Timer timer = new Timer ();
	
    public AutoPneumaticsDownCmd() {
    	requires(Robot.pneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//we start driving
    	timer.start ();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
	
    	while(timer.get() < 0.5)
    	{ 
    		Robot.pneumatics.setBottom(true);
    	}
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pneumatics.setBottom(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
