
package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.ArcadeDriveCmd;
/// not used
// import org.usfirst.frc.team6201.robot.commands.TankDriveCmd;


import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;


/**
 * 
 * @author David Matthews
 * 
 * This is the subsystem for the drivetrain
 * 
 *
 */


public class Drivetrain extends PIDSubsystem {

	//  Motors
	private Victor leftDrive1 = new Victor (RobotMap.LEFT_DRIVE1);
	private Victor rightDrive1 = new Victor (RobotMap.RIGHT_DRIVE1);
	private Victor leftDrive2 = new Victor (RobotMap.LEFT_DRIVE2);
	private Victor rightDrive2 = new Victor (RobotMap.RIGHT_DRIVE2);
	
	// Sensors
	private Encoder leftEncoder = new Encoder (RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B);
	private	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	// PID values
	public static double P = 0;
	public static double I = 0;
	public static double D = 0;

	public Drivetrain () {
		super ("Drivetrain", P, I, D);
		
		leftEncoder.setDistancePerPulse(RobotMap.SET_DISTANCE_PER_PULSE);
	}
	
	public void initDefaultCommand() {
		
		setDefaultCommand(new ArcadeDriveCmd());
   
	}
    
    //sets the power of the left and right motors.
    // Power can be between -1 and 1.
    // 0 is stop, extremum  are full speed.
    public void driveLR (double leftPower, double rightPower) {
     	rightDrive2.set(-rightPower);
    	leftDrive1.set(leftPower);
    	rightDrive1.set(-rightPower);
    	leftDrive2.set(leftPower);
    }
    
    //stops the drivetrain
    public void stop () {
    	this.driveLR(0,0);
    }
    
    //resets the encoder count
    public void resetEncoders () {
    	leftEncoder.reset();
    }
    
    //calibrates the gyro
    public void calibrateGyro(){
    	gyro.calibrate();
    }
    
    //resets the gyro to 0
    public void resetGyro(){
    	gyro.reset();
    }
    
    //gets the current gyro angle
    public double getGyroAngle(){
    	return gyro.getAngle();
    }
    
    //gets the current gyro rate
    public double getGyroRate(){
    	return gyro.getRate();
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return leftEncoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		this.driveLR(output, output);
		
	}
    
    
}

