package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This command uses the current position of the joysticks to set the power to
 * the left and right motors of the robot. The joystick's position is passed
 * through a tangent curve to allow for precise control near the center region
 * of the joystick and to allow for fast rough control at higher speeds.
 *
 * @author David Matthews
 * @version Apr 16, 2016
 */
public class ArcadeDriveCmd extends Command
{
	/**
	 * The raw output reading of the attached joystick on the x axis after it
	 * has been put through scaledValTan(). The value of this corresponds to the
	 * desired rate of turn of the robot.
	 */
	private double			rawTurn;

	/**
	 * The raw output reading of the attached joystick on the y axis after it
	 * has been put through scaledValTan(). The value of this corresponds to the
	 * desired speed of the robot.
	 */
	private double			rawPower;

	/**
	 * The power to the motors has a cap. In order to allow for intuitive
	 * turning ability, by not saturating the motors, the amount of turning that
	 * is available decreases as the speed of the robot increases.
	 */
	private double			processedTurnOnce;

	/**
	 * The robot will almost always not turn at the desired turn angle. This
	 * value stores the
	 */
	private double			processedTurnTwice;

	/**
	 * This reserves 5% of our speed for turning to prevent saturation to allow
	 * for intuitive turning. The 5% is unlikely to be noticed by a user,
	 * especially with the much larger variation due to battery levels.
	 */
	private double			processedPower;

	/**
	 * The domain of the tangent function used for the y axis of the joystick.
	 */
	private final double	TANDOMAIN_Y		= 1.3;

	/**
	 * The domain of the tangent function used for the x axis of the joystick.
	 */
	private final double	TANDOMAIN_X		= 1.2;

	/**
	 * This scales the input gyro rate to allow the desired turn rate to match
	 * the actual turn rate.
	 */
	private final double	pTurnGain		= 0.05;

	/**
	 * This scales the error of our desired turn rate to it's affect on the
	 * motors.
	 */
	private final double	gyroRateGain	= 0.05;

	/**
	 * This method uses the tangent function as a joystick sensitivity transfer
	 * function to allow for precise control at the center region without
	 * sacrificing fast movement at the outer region.
	 * 
	 * @param rawVal
	 *            The unedited value from of the joystick attached to the
	 *            driverstation.
	 * @param domain
	 *            A lower number will make the sensitivity curve more linear,
	 *            whereas a higher number will make the robot have more precise
	 *            control close to zero, and have less precise control at the
	 *            outer regions.
	 * @return A number between -1 and 1.
	 */
	private double scaledValTan(double rawVal, double domain)
	{
		return Math.tan(rawVal * domain) / (Math.tan(domain));
	}

	/**
	 * This command will require the drivetrain to run.
	 */
	public ArcadeDriveCmd()
	{
		requires(Robot.dt);
	}

	/**
	 * Is called when this command first runs.
	 */
	protected void initialize()
	{
		Robot.dt.resetGyro();
	}

	/**
	 * When this command is scheduled to run it gets the joystick position from
	 * the driverstation and sets the motors to the appropriate power level to
	 * achieve the desired result
	 */
	protected void execute()
	{
		rawTurn = scaledValTan(Robot.oi.getXAxisOfLogitech(), TANDOMAIN_X);
		rawPower = scaledValTan(Robot.oi.getYAxisOfLogitech(), TANDOMAIN_Y);
		processedPower = 0.95 * rawPower;
		processedTurnOnce = (1 - rawPower) * rawTurn;
		processedTurnTwice = (processedTurnOnce - gyroRateGain * Robot.dt.getGyroRate()) * pTurnGain
				+ processedTurnOnce;
		SmartDashboard.putNumber("rPower: ", Robot.oi.getYAxisOfLogitech());
		SmartDashboard.putNumber("rTurn: ", Robot.oi.getXAxisOfLogitech());
		SmartDashboard.putNumber("pPower: ", processedPower);
		SmartDashboard.putNumber("p1Turn: ", processedTurnOnce);
		SmartDashboard.putNumber("p2Turn: ", processedTurnTwice);
		SmartDashboard.putNumber("GyroAngle: ", Robot.dt.getGyroAngle());
		SmartDashboard.putNumber("GyroRate: ", Robot.dt.getGyroRate());

		if (RobotMap.fowardOrReverse == 1)
		{
			Robot.dt.driveLR(RobotMap.fowardOrReverse * (processedPower + processedTurnTwice),
					RobotMap.fowardOrReverse * (processedPower - processedTurnTwice));
		}
		else
		{
			Robot.dt.driveLR(RobotMap.fowardOrReverse * (processedPower - processedTurnTwice),
					RobotMap.fowardOrReverse * (processedPower + processedTurnTwice));
		}
	}

	/**
	 * True when the scheduler should stop running the execute part of this
	 * command.
	 */
	protected boolean isFinished()
	{
		return false;
	}

	/**
	 * Called once after isFinished () returns true.
	 */
	protected void end()
	{
		Robot.dt.stop();
	}

	/**
	 * Called when this command is interrupted. Ex. when a button is pressed
	 * which it's associated command requires the drivetrain, this command will
	 * be interrupted.
	 */
	protected void interrupted()
	{
		this.end();
	}
}
