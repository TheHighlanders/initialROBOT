package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
    
	VictorSP leftMotor;
	VictorSP rightMotor;
	
	public DriveTrain() {
		leftMotor = new VictorSP(RobotMap.LEFTMOTOR);
		rightMotor = new VictorSP(RobotMap.RIGHTMOTOR);
	}
	
	public void driveLR(double leftSpeed, double rightSpeed) {
		leftMotor.set(leftSpeed);
		rightMotor.set(-rightSpeed);
	}
	
    public void initDefaultCommand() {
    	
    }
}

