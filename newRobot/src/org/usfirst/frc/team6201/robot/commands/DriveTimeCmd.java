package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * FIXME: can we do this without a while loop? While loops will keep this
 * command running longer than 20ms and thus will annoy the scheduler. We should
 * try and move the check to see if the robot has moved far enough to the
 * isFinished method
 *
 * Sets the robot to drive forward for a set amount of time.
 *
 * @author David Matthews
 * @version Apr 17, 2016
 */
public class DriveTimeCmd extends Command
{
	/**
	 * This holds our error for driving in a straight line. TODO: should we use
	 * gyro rate or gyro angle? gyro angle by definition has drift...
	 */
	private double	calibrated;

	/**
	 * This timer stores how long we have been driving.
	 */
	private Timer	timer	= new Timer();

	/**
	 * This stores the desired time that the robot will drive for.
	 */
	private double	drivingTime;

	/**
	 * This constructor sets the time that the robot is desired to drive in a
	 * straight line for. This command requires the drivetrain subsystem.
	 * 
	 * @param drivingTime
	 *            The time that the robot should drive in a straight line for.
	 */
	public DriveTimeCmd(double drivingTime)
	{
		this.drivingTime = drivingTime;
		requires(Robot.dt);
	}

	/**
	 * Called once when this command is scheduled to run. Starts the timer.
	 */
	protected void initialize()
	{
		timer.start();
	}

	/**
	 * When this command is scheduled to run it sets the motors to drive such
	 * that the gyro thinks the robot is not turning.
	 */
	protected void execute()
	{

		while (timer.get() < drivingTime)
		{
			calibrated = ((0.8 - 0.05 * Robot.dt.getGyroRate()) * 0.05);
			Robot.dt.driveLR(-0.80 - calibrated, -0.8 - 0.12 + calibrated);
		}

	}

	/**
	 * Returns true.
	 */
	protected boolean isFinished()
	{
		return true;
	}

	/**
	 * Called once after isFinished() returns true.
	 */
	protected void end()
	{
		Robot.dt.stop();
	}

	/**
	 * Called when this command is interrupted.
	 */
	protected void interrupted()
	{
		this.end();
	}
}
