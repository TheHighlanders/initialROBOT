
package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.ArcadeDriveCmd;
/// not used
// import org.usfirst.frc.team6201.robot.commands.TankDriveCmd;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;



public class Drivetrain extends PIDSubsystem {

	//  Motors
	private Talon leftDrive1 = new Talon (RobotMap.LEFT_DRIVE1);
	private Talon rightDrive1 = new Talon (RobotMap.RIGHT_DRIVE1);
	private Spark leftDrive2 = new Spark (RobotMap.LEFT_DRIVE2);
	private Spark rightDrive2 = new Spark (RobotMap.RIGHT_DRIVE2);
	
	// Sensors
	private Encoder leftEncoder = new Encoder (RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B);

	// PID values
	public static double P = 0;
	public static double I = 0;
	public static double D = 0;

	public Drivetrain () {
		super ("Drvetrain", P, I, D);
		
		leftEncoder.setDistancePerPulse(RobotMap.SET_DISTANCE_PER_PULSE);
	}
	
	public void initDefaultCommand() {
    	setDefaultCommand (new ArcadeDriveCmd());
    }
    
    //sets the power of the left and right motors.
    // Power can be between -1 and 1.
    // 0 is stop, extremum  are full speed.
    public void driveLR (double leftPower, double rightPower) {
    	leftDrive1.set(leftPower);
    	rightDrive1.set(-rightPower);
    	leftDrive2.set(leftPower);
    	rightDrive2.set(-rightPower);
    }
    
    //stops the drivetrain
    public void stop () {
    	this.driveLR(0,0);
    }
    
    public void resetEncoders () {
    leftEncoder.reset();
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

