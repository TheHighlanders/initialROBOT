package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TopPneumaticOnCmd extends Command
{
	/**
	 * Requires the pneumatics subsystem.
	 */
	public TopPneumaticOnCmd()
	{
		requires(Robot.pneumatics);
	}

	/**
	 * Opens the top solenoid.
	 */
	protected void initialize()
	{
		Robot.pneumatics.setTop(true);
	}

	/**
	 * Does nothing.
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
	 * Closes the top pneumatics when the command is finished.
	 */
	protected void end()
	{
		Robot.pneumatics.setTop(false);
	}

	/**
	 * Closes the top pnumeatics when the command is interrupted.
	 */
	protected void interrupted()
	{
		Robot.pneumatics.setTop(false);
	}

}
