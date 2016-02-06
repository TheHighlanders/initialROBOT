package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *@author David Matthews
 */
public class ArcadeDriveCmd extends Command {


	// declaring variables 
	private double rawX;
	private double rawY;
	

	//determines a scaled value from the raw value
	private double scaledVal (double sV) {
		if (sV>=0) {
			return sV*sV;
		} else {
			return -1*sV*sV;
		}
	}

	
	// determines the value of endX depending on direction of travel, and scaledX
	private double endX (double scaledX, double scaledY) {

    	// going foward
    	if (scaledY > 0) {
    		return scaledX;
    	}
    	
    	// going backwards
    	if (scaledY <= 0) {
    		return -1.0*scaledX;
    	}
    	
    	return 0.0;		
	}
	

    public ArcadeDriveCmd() {
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 rawX = Robot.oi.getRotateAxisOfLogitech();
    	 rawY = -Robot.oi.getYAxisOfLogitech();
    	Robot.dt.driveLR(scaledVal(rawY) + endX(scaledVal(rawY), scaledVal(rawX)), scaledVal(rawY) - endX(scaledVal(rawY), scaledVal(rawX)) );
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
