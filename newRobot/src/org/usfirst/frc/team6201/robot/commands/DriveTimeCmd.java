package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTimeCmd extends Command {

	
	private double calibrated;
	// how long have we been driving?
	private Timer timer = new Timer ();
	
	// how long do we want to drive?
	private double drivingTime;
	
    public DriveTimeCmd(double drivingTime) {
        this.drivingTime = drivingTime;
        requires(Robot.dt);
    }

    protected void initialize() {
    	//we start driving
    	timer.start ();

    }

    protected void execute() {
    	
    	while(timer.get()< drivingTime){
    		calibrated = ((0.8 - 0.05*Robot.dt.getGyroRate())*0.05); // uses the gyro as a feedback loop to drive at the desired turn rate. 
  
    		Robot.dt.driveLR(0.80+calibrated, 0.8+0.12-calibrated );
    	}

    }

    protected boolean isFinished() {
        
  // have we been driving as long as we want to
    	return true;
    }

    protected void end() {
    	Robot.dt.stop();
    }


    protected void interrupted() {
    	this.end();
    }
}
