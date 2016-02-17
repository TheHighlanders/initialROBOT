package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class TurnAngleCmd extends PIDCommand {

	public static boolean finished = false;
	public static double startPos = Robot.dt.getGyroAngle();
	
    public TurnAngleCmd(double rotation) {
        // Use requires() here to declare subsystem dependencies
    	super(1, 0, 0);
    	this.setSetpoint(this.startPos + rotation);
        requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.resetGyro();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }
    
	protected boolean isFinished() {
		return this.finished;
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

	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.dt.getGyroAngle();
	}

	protected void usePIDOutput(double arg0) {
		// TODO Auto-generated method stub
		Robot.dt.driveLR(arg0, -arg0);
		if(arg0 == 0){
			this.finished = true;
		}
	}
}

