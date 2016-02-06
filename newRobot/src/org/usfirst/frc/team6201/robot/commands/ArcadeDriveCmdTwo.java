package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *@author David Matthews
 */
public class ArcadeDriveCmdTwo extends Command {


	// declaring variables 
	private double rawX; // X axis from the Joystick
	private double rawY; // Y axis from the Joystick
	private double scaledY; // Y axis after tan curve
	private double scaledX; // X axis after tan curve.
	private final double TANDOMAIN_Y = 1.3;
	private final double TANDOMAIN_X = 1.3;
	
	
	
	// uses a tan curve for the sensitivity of the robot vs. joystick position.
	// compresses the values to fit within the desired range of tan, and within the desired output range.
	
	private double scaledVal (double rawVal, double domain){
		return Math.tan(rawVal*domain) / (Math.tan(domain));
	}
	
	// adjusts the output of the scaledX inorder to reduce sensitivy at higher speeds.
	
	
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
	

    public ArcadeDriveCmdTwo() {
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 rawX = Robot.oi.getRotateAxisOfLogitech();
    	 rawY = -Robot.oi.getYAxisOfLogitech();
    //	Robot.dt.driveLR(scaledVal(rawY) + endX(scaledVal(rawY), scaledVal(rawX)), scaledVal(rawY) - endX(scaledVal(rawY), scaledVal(rawX)) );
    
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
