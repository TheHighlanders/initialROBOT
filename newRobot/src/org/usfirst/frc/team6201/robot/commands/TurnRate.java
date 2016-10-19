package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is used to get the robot to turn at a specified rate of turning in
 * degrees per second.
 * 
 * @author David Matthews
 * 
 */
public class TurnRate extends Command {

	private double targetTurnRate;
	private double leftMotorSpeed = 0;
	private double rightMotorSpeed = 0;

	/**
	 * 
	 * @param rateOfTurning
	 *            The rate that the robot should turn at.
	 */
	public TurnRate(int rateOfTurning) {
		requires(Robot.dt);
		targetTurnRate = rateOfTurning;
	}

	private double error() {
		return (Robot.dt.getGyroRate() - targetTurnRate);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double currError = error();

		if (currError >= 0) {
			
			leftMotorSpeed -= Math.min(currError * 0.1, 0.5);
			rightMotorSpeed +=  Math.min(currError * 0.1, 0.5);
		} else {
			leftMotorSpeed +=  Math.min(currError * 0.1, 0.5);
			rightMotorSpeed -=  Math.min(currError * 0.1, 0.5);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
