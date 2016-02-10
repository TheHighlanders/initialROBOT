package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author David Matthews
 */
public class ArcadeDriveCmd extends Command {


	// declaring variables 
	private double rawX; // X axis from the Joystick
	private double rawY; // Y axis from the Joystick
	private double scaledY; // Y axis after tan curve
	private double scaledX; // X axis after tan curve
	private double turn; // how much turning?
	private double power; // how much speed?
	private double nTurn; // adjusted turn
	private double nPower; // adjusted power
	private final double TANDOMAIN_Y = 1.3;
	private final double TANDOMAIN_X = 1.3;
	
	
	// uses a tan curve for the sensitivity of the robot vs. joystick position.
	// compresses the values to fit within the desired range of tan, and within the desired output range.
	
	private double scaledVal (double rawVal, double domain){
		return Math.tan(rawVal*domain) / (Math.tan(domain));
	}
	
	
    public ArcadeDriveCmd() {
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	turn = scaledVal(Robot.oi.getXAxisOfLogitech(), TANDOMAIN_X);
    	power = scaledVal(Robot.oi.getLeftYOfXbox(), TANDOMAIN_Y);
    	
    	nPower = 0.95*power;
    	nTurn = (1-power) * turn;
    
    	Robot.dt.driveLR(nPower + nTurn, nPower - nTurn);
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
