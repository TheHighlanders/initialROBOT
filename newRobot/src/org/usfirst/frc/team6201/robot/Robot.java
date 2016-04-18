
package org.usfirst.frc.team6201.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team6201.robot.commands.AutoCrossDefenseCmdGrp;
import org.usfirst.frc.team6201.robot.commands.AutoReachDefenseCmdGrp;
import org.usfirst.frc.team6201.robot.commands.WhichCameraCmd;

import org.usfirst.frc.team6201.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6201.robot.subsystems.Pneumatics;
import org.usfirst.frc.team6201.robot.subsystems.Roller;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author David Matthews
 * @author Adriana Massie
 */
public class Robot extends IterativeRobot
{

	public static final Drivetrain	dt			= new Drivetrain();

	public static final Roller		roller		= new Roller();

	public static final Pneumatics	pneumatics	= new Pneumatics();

	public static OI				oi;

	public static WhichCameraCmd	wcc			= new WhichCameraCmd();

	Command							autonomousCommand;

	SendableChooser					chooser;

	/**
	 * This method is called when the robot first turns on. In this method, we
	 * initialize the cameras, the OI, and the gyro. The robot should be
	 * stationary for the first 10 seconds after it is turned on. This method
	 * also adds to the smartdashboard the option for the robot driver to choose
	 * if the robot should reach the defense or should try and cross it.
	 */
	public void robotInit()
	{

		Robot.dt.calibrateGyro();
		wcc.initialize();
		oi = new OI();
		chooser = new SendableChooser();
		chooser.addDefault("Cross Obstacle", new AutoCrossDefenseCmdGrp());
		chooser.addObject("Reach Obstacle", new AutoReachDefenseCmdGrp());
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit()
	{

	}

	/**
	 * When the robot is disabled we can edit values on the robot via the
	 * smartdashboard. TODO: We should use this in the future to edit parameters
	 * like the gyro for controlling turning rate.
	 */
	public void disabledPeriodic()
	{

		// Displays PID Values
		SmartDashboard.putNumber("P: ", Drivetrain.P);
		SmartDashboard.putNumber("I: ", Drivetrain.I);
		SmartDashboard.putNumber("D: ", Drivetrain.D);

		// Edits PID Values
		Drivetrain.P = SmartDashboard.getNumber("P: ", Drivetrain.P);
		Drivetrain.I = SmartDashboard.getNumber("I: ", Drivetrain.I);
		Drivetrain.D = SmartDashboard.getNumber("D: ", Drivetrain.D);

		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro You
	 * can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit()
	{
		autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * The scheduler will run all the scheduler commands in a loop that takes
	 * approximately 20ms. We should try to keep each call of the execute method
	 * in each command we use take under this amount of time.
	 */
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * Called at the end of autonomousPeriodic and just before teleopInit. This
	 * is currently being used to ensure that the autonomous commands are not
	 * running at the start of teleop.
	 */
	public void teleopInit()
	{
		if (autonomousCommand != null) autonomousCommand.cancel();
	}

	/**
	 * The scheduler will run all the scheduler commands in a loop that takes
	 * approximately 20ms. We should try to keep each call of the execute method
	 * in each command we use take under this amount of time.
	 */
	public void teleopPeriodic()
	{

		Scheduler.getInstance().run();
	}

	/**
	 * The scheduler will run all the scheduler commands in a loop that takes
	 * approximately 20ms. We should try to keep each call of the execute method
	 * in each command we use take under this amount of time.
	 */
	public void testPeriodic()
	{
		LiveWindow.run();
	}
}
