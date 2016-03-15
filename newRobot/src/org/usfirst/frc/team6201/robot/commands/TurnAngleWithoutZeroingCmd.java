package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author David Matthews
 * 
 * Turns the robot from its the last gyro rest to the destination of @param rotation degrees.
 *
 */
public class TurnAngleWithoutZeroingCmd extends Command {

	private double turn;
	private double scalarOnTurn = 1;
	private double rotation;
	private double acceptedError;
	
    public TurnAngleWithoutZeroingCmd(double rotation, double acceptedError) {
    	requires(Robot.dt);
    	this.rotation = rotation;
        this.acceptedError = acceptedError;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	while (!(Math.abs(Robot.dt.getGyroAngle() - rotation) <= acceptedError))
    	{
    		turn = scalarOnTurn*(Robot.dt.getGyroAngle() - rotation);
    		Robot.dt.driveLR(turn, -turn);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
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
