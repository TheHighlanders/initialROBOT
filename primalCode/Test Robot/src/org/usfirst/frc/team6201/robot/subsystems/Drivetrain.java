
package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.TankDriveCmd;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem; 


public class Drivetrain extends PIDSubsystem {

	//  Motors
	private Talon leftDrive = new Talon (RobotMap.LEFT_DRIVE);
	private Talon rightDrive = new Talon (RobotMap.RIGHT_DRIVE);
	
	
	// Sensors
	private Encoder leftEncoder = new Encoder (RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B);

	// PID values
	public static double P = 0;
	public static double I = 0;
	public static double D = 0;

	public Drivetrain () {
		super ("Drvetrain", P, I, D);
		
		leftEncoder.setDistancePerPulse(1);
	}
	
	public void initDefaultCommand() {
    	setDefaultCommand (new TankDriveCmd());
    }
    
    //sets the power of the left and right motors.
    // Power can be between -1 and 1.
    // 0 is stop, extremum  are full speed.
    public void driveLR (double leftPower, double rightPower) {
    	leftDrive.set(leftPower);
    	rightDrive.set(-rightPower);
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

