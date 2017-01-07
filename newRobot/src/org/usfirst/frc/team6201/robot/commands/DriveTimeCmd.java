package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author David Matthews
 */

//TODO: add comments, clean up
public class DriveTimeCmd extends Command {

	/**
	 * 
	 */
	private double calibratedPower;
	/**
	 * how long have we been driving?
	 */
	private Timer timeDriven = new Timer ();

	/**
	 * how long do we want to drive?
	 */
	private double targetDrivingTime;
	
	/**
	 * have we been driving long enough? 
	 */
	private boolean stillDriving = true;
	
	/**
	 * Constructor requires Robot.dt
	 * 
	 * @param drivingTime How much time in seconds we want to drive for.
	 */
    public DriveTimeCmd(double drivingTime) {
        this.targetDrivingTime = drivingTime;
        requires(Robot.dt);
    }

    /**
     * Starts the timer.
     */
    protected void initialize() {
    	timeDriven.start ();
    }

    /**
     * if we have not been driving long enough, we attempt to drive straight.
     * 
     * To drive straight, we look at how fast we are turning, and attempt to spin one of the motors faster to drive straight.
     */
    protected void execute() {
    	
    	if(stillDriving){
    		calibratedPower = ((0.8 - 0.05*Robot.dt.getGyroRate())*0.05); // uses the gyro as a feedback loop to drive at the desired turn rate. 
  
    		Robot.dt.driveLR(-0.80-calibratedPower, -0.8-0.12+calibratedPower );
    	}
    	if (timeDriven.get()>= targetDrivingTime) {
    		stillDriving = false;
    	}
    }

    
    protected boolean isFinished() {
    	return stillDriving;
    }

    protected void end() {
    	Robot.dt.stop();
    }


    protected void interrupted() {
    	this.end();
    }
}
