
package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.ArcadeDriveCmd;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * @author David Matthews This is the subsystem for the drivetrain
 */

public class Drivetrain extends PIDSubsystem
{

	// Motors
	private VictorSP		leftDrive1	= new VictorSP(RobotMap.LEFT_DRIVE1);

	private VictorSP		rightDrive1	= new VictorSP(RobotMap.RIGHT_DRIVE1);

	private VictorSP		leftDrive2	= new VictorSP(RobotMap.LEFT_DRIVE2);

	private VictorSP		rightDrive2	= new VictorSP(RobotMap.RIGHT_DRIVE2);

	// Sensors
	private Encoder			leftEncoder	= new Encoder(RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B);

	private ADXRS450_Gyro	gyro		= new ADXRS450_Gyro();

	// PID values
	public static double	P			= 0;

	public static double	I			= 0;

	public static double	D			= 0;

	public Drivetrain()
	{
		super("Drivetrain", P, I, D);
		leftEncoder.setDistancePerPulse(RobotMap.SET_DISTANCE_PER_PULSE);
		rightDrive1.setInverted(true);
		rightDrive2.setInverted(true);
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new ArcadeDriveCmd());
	}

	/**
	 * Controls the power to the motors on the drivetrain
	 * 
	 * @param leftPower
	 *            The power between -1 and 1 to the left side of the drivetrain.
	 * @param rightPower
	 *            The power between -1 and 1 to the right side of the
	 *            drivetrain.
	 */
	public void driveLR(double leftPower, double rightPower)
	{
		rightDrive2.set(rightPower);
		leftDrive1.set(leftPower);
		rightDrive1.set(rightPower);
		leftDrive2.set(leftPower);
	}

	/**
	 * Sets all the drive motors to a speed of 0.
	 */
	public void stop()
	{
		this.driveLR(0, 0);
	}

	/**
	 * Resets the encoders to 0.
	 */
	public void resetEncoders()
	{
		leftEncoder.reset();
	}

	/**
	 * Runs a 5 second calibration on the gyro. For this to go correctly, the
	 * robot must be still.
	 */
	public void calibrateGyro()
	{
		gyro.calibrate();
	}

	/**
	 * Resets the angle that the gyro is reading to 0;
	 */
	public void resetGyro()
	{
		gyro.reset();
	}

	/**
	 * A getter method for the angle that the gyro is currently reading TODO:
	 * see how the drift of our two gyros compare. TODO: research how to derive
	 * roll and pitch from accelerometer reading
	 * 
	 * @return A double holding the current angle of the gyro.
	 */
	public double getGyroAngle()
	{
		return gyro.getAngle();
	}

	/**
	 * A getter method for the current angle of turning that the gyro is reading
	 * in deg/sec.
	 * 
	 * @return The current rate of turn that the gyro is reading in deg/sec.
	 */
	public double getGyroRate()
	{
		return gyro.getRate();
	}

	@Override
	protected double returnPIDInput()
	{
		return leftEncoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output)
	{

		this.driveLR(output, output);

	}

}
