
package org.usfirst.frc.team6201.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6201.robot.commands.Auto;
import org.usfirst.frc.team6201.robot.commands.DriveTimeCmd;
import org.usfirst.frc.team6201.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6201.robot.subsystems.Power;
import org.usfirst.frc.team6201.robot.subsystems.Roller;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 	
 * @contributor David Matthews 
 */
public class Robot extends IterativeRobot {

	public static final Drivetrain dt = new Drivetrain();
	public static final Roller roller = new Roller();
	public static OI oi;
	public static Power pdp;

    Command autonomousCommand;
    SendableChooser chooser;
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    
    public void robotInit() {

		oi = new OI();
		pdp = new Power();
        chooser = new SendableChooser();
        chooser.addDefault("Cross Obstacle", new Auto());
        chooser.addObject("Reach Obstacle", new DriveTimeCmd(0.6)); // added after reading.
        SmartDashboard.putData("Auto mode", chooser);
        
        Robot.dt.calibrateGyro(); // added after reading.
    	
        
   
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
    
    // will only work when robot is disabled
	public void disabledPeriodic() {
		
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
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * 
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    	SmartDashboard.putNumber("Total Current: ", DataCollection.totalCurrent);
		SmartDashboard.putNumber("gyro: ", DataCollection.gyro);
		SmartDashboard.putNumber("motorSpeedLeft: ", DataCollection.motorSpeedLeft);
		SmartDashboard.putNumber("motorSpeedRight: ", DataCollection.motorSpeedRight);
		
		Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
        Scheduler.getInstance().run();     
    }
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
