package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTimeCmd extends Command {

	// how long have we been driving?
	private Timer timer = new Timer ();
	
	// how long do we want to drive?
	private double drivingTime;
	
    public DriveTimeCmd(double drivingTime) {
        this.drivingTime = drivingTime;
    }

    protected void initialize() {
    	//we start driving
    	timer.start ();
    }

    protected void execute() {
    	
    	while (timer.get()>this.drivingTime)
    	{
    		Robot.dt.driveLR(1.0, 1.0);
    	}
    	
    }

    protected boolean isFinished() {
        
  // have we been driving as long as we want to
    	return timer.get()>this.drivingTime;
    }

    protected void end() {
    	Robot.dt.stop();
    }


    protected void interrupted() {
    	this.end();
    }
}
