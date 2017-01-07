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

	private double turningPower = 0;
	private double currAngle = 0;
	private double scalarOnTurn = 1/3;
	private double targetAngle;
	private double acceptedErrorInTargetAngle;
	private boolean onTarget = false;
	
	/**
	 * Constructor for this command to make it rotate a set number of degrees clockwise with given accuracy
	 * 
	 * @param angleAmount number of degrees to rotate the robot. Positive is rotate clockwise, negative is rotate counterclockwise 
	 * @param acceptedError percent error acceptable for this command to end.
	 */
    public TurnAngleCmd(double angleAmount, double acceptedError) {
    	requires(Robot.dt);
    	this.targetAngle = angleAmount;
        this.acceptedErrorInTargetAngle = acceptedError;
    }

    protected void initialize() {
       	Robot.dt.resetGyro();
    }

    /**
     * Calculates the power to set the motors to so that we move toward the target angle. 
     */
    private void getError() {
    	turningPower = scalarOnTurn*(currAngle - targetAngle);
    }
    
    /**
     * 
     * @return true if we are on target, false if not.
     */
    private boolean onTarget(){
    	return (currAngle + acceptedErrorInTargetAngle*targetAngle >= targetAngle ||currAngle + acceptedErrorInTargetAngle*targetAngle < targetAngle);
    }
    protected void execute() {
    	
    	if (!onTarget)
    	{
    		currAngle = Robot.dt.getGyroAngle();
    		getError();
    		Robot.dt.driveLR(turningPower, -turningPower);
    		if (onTarget()){
    			onTarget = true;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return onTarget;
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
