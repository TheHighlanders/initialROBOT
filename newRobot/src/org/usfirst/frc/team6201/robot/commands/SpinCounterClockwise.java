package org.usfirst.frc.team6201.robot.commands;

	import org.usfirst.frc.team6201.robot.Robot;
	import org.usfirst.frc.team6201.robot.RobotMap;
	import edu.wpi.first.wpilibj.command.Command;

	/**
	 *@author Adriana Massie
	 *
	 *
	 *Tells the robot to spin clockwise 
	 */
	public class SpinCounterClockwiseCmd() extends Command {

	    public SpinCounterClockwiseCmd() {
	        // Use requires() here to declare subsystem dependencies
	         requires(Robot.dt);
	    }

	    
	   //Called just before this Command runs the first time
	    protected void initialize() {
	    }

	    protected void execute() {	
	    	Robot.dt.driveLR(-0.95,0.95 );
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