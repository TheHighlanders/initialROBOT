package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTimeCmd extends Command {

	private Timer timer = new Timer ();
	private double drivingTime;
	
    public DriveTimeCmd(double drivingTime) {
        this.drivingTime = drivingTime;
    }

    protected void initialize() {
    	timer.start ();
    	Robot.dt.driveLR(1.0, 1.0);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        
  
    	return timer.get()>this.drivingTime;
    }

    protected void end() {
    	Robot.dt.stop();
    }


    protected void interrupted() {
    	this.end();
    }
}
