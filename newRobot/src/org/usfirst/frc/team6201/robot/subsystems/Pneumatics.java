package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * This subsystem controls the robots pneumatic arm that is used for lifting the
 * port cullis, and crossing the chaval de fris.
 * 
 * @author David Matthews
 * @author Max Nadeau
 */
public class Pneumatics extends PIDSubsystem
{
	/**
	 * The solenoid that pushes the pneumatic arm to the ground.
	 */
	private Solenoid		top		= new Solenoid(RobotMap.TOP_PNEUMATIC);

	/**
	 * The solenoid that raises the pneumatic arm to the ceiling.
	 */
	private Solenoid		bottom	= new Solenoid(RobotMap.BOTTOM_PNEUMATIC);

	/**
	 * Used to control how long the solenoid's are open for.
	 */
	public Timer			timer	= new Timer();

	public static double	P		= 0;

	public static double	I		= 0;

	public static double	D		= 0;

	public Pneumatics()
	{
		super("Pneumatic", P, I, D);
	}

	public void initDefaultCommand()
	{

	}

	/**
	 * This controls if the top solenoid is open or closed.
	 *
	 * @param value
	 *            True if this solenoid is open, false if not.
	 */
	public void setTop(boolean value)
	{
		top.set(value);
	}

	/**
	 * This controls if the bottom solenoid is open or closed.
	 * 
	 * @param value
	 *            True if this solenoid is open, false if not.
	 */
	public void setBottom(boolean value)
	{
		bottom.set(value);
	}

	/**
	 * Closes both solenoids.
	 */
	public void stop()
	{
		setTop(false);
		setTop(false);
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
