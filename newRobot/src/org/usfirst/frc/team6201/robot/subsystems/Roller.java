package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * The subsystem for controlling the ball picking up device.
 * 
 * @author David Matthews
 * @author Max Nadeau
 */
public class Roller extends PIDSubsystem
{
	/**
	 * The controller for the motor that runs the rollers.
	 */
	private Spark			motor	= new Spark(RobotMap.ROLLER);

	public Timer			timer	= new Timer();

	public static double	P		= 0;

	public static double	I		= 0;

	public static double	D		= 0;

	public Roller()
	{
		super("Roller", P, I, D);
	}
/**
 * Not used.
 */
	public void initDefaultCommand()
	{

	}

	/**
	 * This method is used to collect and shoot balls.
	 * 
	 * @param power
	 *            The speed of the rollers. Positive if pulling in a ball,
	 *            negative if rolling a ball out.
	 */
	public void roll(double power)
	{
		motor.set(power);
	}

	/**
	 * Stops the rollers.
	 */
	public void stop()
	{
		roll(0.0);
	}

	@Override
	protected double returnPIDInput()
	{
		return 0;
	}

	@Override
	protected void usePIDOutput(double arg0)
	{

	}
}
