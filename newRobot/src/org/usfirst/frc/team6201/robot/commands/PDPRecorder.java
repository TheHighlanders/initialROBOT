package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.DataCollection;
import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PDPRecorder extends Command {

    public PDPRecorder() {

    	requires(Robot.pdp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DataCollection.totalCurrent = Robot.pdp.getTotalCurrent();
    	DataCollection.batteryVoltage = Robot.pdp.getVoltage();
    	DataCollection.pdpTemp = Robot.pdp.getTemp();
    	DataCollection.gyro = Robot.pdp.getRate();
    	DataCollection.accelX = Robot.pdp.getAccelX();
    	DataCollection.accelY = Robot.pdp.getAccelY();
    	DataCollection.accelZ = Robot.pdp.getAccelZ();
    	// Rollers
    	DataCollection.current1 = Robot.pdp.getCurrentChannel(1);
    	// left side if wood is front.
    	DataCollection.current2 = Robot.pdp.getCurrentChannel(2);
    	// not used.
    	DataCollection.current3 = Robot.pdp.getCurrentChannel(3);
    	// right side if wood is front.
    	DataCollection.current14 = Robot.pdp.getCurrentChannel(14);
    	//not used
    	DataCollection.current15 = Robot.pdp.getCurrentChannel(15);
    	
    	SmartDashboard.putNumber("Total Current: ", DataCollection.totalCurrent);
    	SmartDashboard.putNumber("Gyro: ", DataCollection.gyro);
    	SmartDashboard.putNumber("AccelX: ", DataCollection.accelX);
    	SmartDashboard.putNumber("AccelY: ", DataCollection.accelY);
    	SmartDashboard.putNumber("AccelZ: ", DataCollection.accelZ);
    	SmartDashboard.putNumber("current1: ", DataCollection.current1);
    	SmartDashboard.putNumber("current2: ", DataCollection.current2);
    	SmartDashboard.putNumber("current3: ", DataCollection.current3);
    	SmartDashboard.putNumber("current14: ", DataCollection.current14);
    	SmartDashboard.putNumber("current15: ", DataCollection.current15);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
