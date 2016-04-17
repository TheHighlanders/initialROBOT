package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * @author David Matthews
 * @author Max Nadeau The subsystem for controlling the ball picking up device.
 */
public class Pneumatics extends PIDSubsystem
{
	private Solenoid		top		= new Solenoid(RobotMap.TOP_PNEUMATIC);
	private Solenoid		bottom	= new Solenoid(RobotMap.BOTTOM_PNEUMATIC);
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

	public void setTop(boolean value)
	{
		top.set(value);
	}

	public void setBottom(boolean value)
	{
		bottom.set(value);
	}

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
