package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnAngleCmd extends Command {

	public static double goal;
    public TurnAngleCmd(double rotation) {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.dt);
         this.goal = Robot.dt.getGyroAngle() + rotation;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(this.goal > Robot.dt.getGyroAngle())
    		Robot.dt.driveLR(RobotMap.ROTATION_SPEED, -RobotMap.ROTATION_SPEED);
    	else
    		Robot.dt.driveLR(-RobotMap.ROTATION_SPEED, RobotMap.ROTATION_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(Robot.dt.getGyroAngle() - this.goal) < RobotMap.ROTATION_LEEWAY);
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

