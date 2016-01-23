package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistanceCmd extends Command {

	private double distance;
	
    public DriveDistanceCmd(double distance) {
    	requires (Robot.dt);
    	this.distance = distance;
    	
    	Robot.dt.setInputRange(-this.distance,this.distance);
    	Robot.dt.setOutputRange(-1.0, 1.0);
    	Robot.dt.setAbsoluteTolerance(1);
    }
    
    protected void initialize() {
    	
    	Robot.dt.setSetpoint (this.distance);
    	Robot.dt.resetEncoders();
    	Robot.dt.enable();
    }

   
    protected void execute() {
    	
    }

  
    protected boolean isFinished() {
        return Robot.dt.onTarget();
    }


    protected void end() {
    	Robot.dt.disable();
    	Robot.dt.stop();
    }

    protected void interrupted() {
    }
}
