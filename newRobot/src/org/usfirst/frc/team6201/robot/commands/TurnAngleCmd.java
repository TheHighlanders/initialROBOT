package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author David Matthews
 * 
 * Turns the robot from its current angle X degrees.
 *
 */
public class TurnAngleCmd extends Command {

	private boolean targetFound = false;
	/**
	 * The initial angle as measured by the gyroscope
	 */
	private double initialAngle;
	
	/**
	 * The target final angle as measured by the gyroscope. 
	 */
	private double targetAngle;
	
	/**
	 * How slow we turn.
	 */
	private double scalarOnTurn = 1/3;
	
	/**
	 * How much we should be turning.
	 */
	private double deltaAngle;
	
	/**
	 * How much error is acceptable between our target turn amount and our actual turn amount.
	 * 
	 * This is measured as a percent.
	 */
	private double acceptedError;
	
	/**
	 * 
	 * @param deltaAngle	Angle which we should be turnin
	 * @param acceptedError	Error in target angle vs. measured angle that is acceptable as a percent.
	 */
    public TurnAngleCmd(double deltaAngle, double acceptedError) {
    	requires(Robot.dt);
    	this.deltaAngle = deltaAngle;
        this.acceptedError = acceptedError;
    }
	
    private double error () {
    	return (Robot.dt.getGyroAngle() - targetAngle);
    }
    /**
     * Before we start moving, get the initialAngle from the gyroscope,
     * and calculate the target angle.
     */
    protected void initialize() {
    	initialAngle = Robot.dt.getGyroAngle();
    	targetAngle = initialAngle + deltaAngle;
    	
    }
    

    /**
     * If currError is within acceptedError of the deltaAngle stops driving.
     * Else drives slowly towards in the correct direction.
     * 
     * If we plan to use this in a competition we might want to create a mapping function for hitting the target angle.
     */
    protected void execute() {
    	
    	double currError = error();
    	if(currError >= -acceptedError*deltaAngle && currError <= acceptedError*deltaAngle) {
    		targetFound = true;
    	} else if (currError >=0) {
    		Robot.dt.driveLR(-0.1, 0.1);
 
    	} else if (currError <0) {
    		Robot.dt.driveLR(0.1, -0.1);
    	}

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return targetFound;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
