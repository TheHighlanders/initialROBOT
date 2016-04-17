package org.usfirst.frc.team6201.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets the robot's driving direction to be towards the front of the robot.
 * 
 * @author David Matthews
 */
public class ForwardDriveCmd extends Command
{
	/**
	 * Does nothing
	 */
	public ForwardDriveCmd()
	{
	}

	/**
	 * Called once when the scheduler first calls this command.
	 */
	protected void initialize()
	{
		ArcadeDriveCmd.setDrivingDirection(1);
	}

	/**
	 * Does nothing
	 */
	protected void execute()
	{
	}

	/**
	 * Returns true.
	 */
	protected boolean isFinished()
	{
		return true;
	}

	/**
	 * Does nothing
	 */
	protected void end()
	{
	}

	/**
	 * Does nothing
	 */
	protected void interrupted()
	{
	}
}
