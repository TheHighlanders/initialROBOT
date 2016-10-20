
package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.ArcadeDriveCmd;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
	private VictorSP leftDrive1 = new VictorSP (RobotMap.LEFT_DRIVE1);
	private VictorSP rightDrive1 = new VictorSP (RobotMap.RIGHT_DRIVE1);
	private VictorSP leftDrive2 = new VictorSP (RobotMap.LEFT_DRIVE2);
	private VictorSP rightDrive2 = new VictorSP (RobotMap.RIGHT_DRIVE2);
	
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
    
   /**
    * Sets the power on the left and right motor speed controllers.
    * @param leftPower -1 to 1 inclusive.
    * @param rightPower -1 to 1 inclusive.
    */
    public void driveLR (double leftPower, double rightPower) {
    	SmartDashboard.putNumber("GyroAngle: ", Robot.dt.getGyroAngle());
    	SmartDashboard.putNumber("GyroRate: ", Robot.dt.getGyroRate());
    	SmartDashboard.putNumber("lMotorPower: ", leftPower);
    	SmartDashboard.putNumber("rMotorPower ", rightPower);
    	
     	rightDrive2.set(-rightPower);
    	leftDrive1.set(leftPower);
    	rightDrive1.set(-rightPower);
    	leftDrive2.set(leftPower);
    }
    
    /**
     * Stops the motors on the drivetrain.
     */
    public void stop () {
    	this.driveLR(0,0);
    }
    
    /**
     * We don't have encoders. Should reset the encoders.
     */
    public void resetEncoders () {
    	leftEncoder.reset();
    }
    
    /**
     * Runs a calibration on the gyro. Takes 5 seconds.
     */
    public void calibrateGyro(){
    	gyro.calibrate();
    }
    
    /**
     * Resets the gyroscope accumulated angle to 0. The accumulator is done in the FPGA to make it drift less.
     */
    private void resetGyro(){
    	gyro.reset();
    }
    
    /**
     * @return Current angle of gyroscope since last reset.
     */
    public double getGyroAngle(){
    	return gyro.getAngle();
    }
    
    /**
     * @return Current rate of turning in degrees.
     */
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

