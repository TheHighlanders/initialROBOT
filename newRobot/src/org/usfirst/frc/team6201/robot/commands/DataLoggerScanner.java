package org.usfirst.frc.team6201.robot.commands;


import org.usfirst.frc.team6201.robot.Robot;


import dataLogger.DataCollator;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *@author David Matthews
 */
public class DataLoggerScanner extends Command {

    public DataLoggerScanner() {

    	requires(Robot.dlf);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dlf.setAccelX();
    	Robot.dlf.setAccelY();
    	Robot.dlf.setAccelZ();
    	Robot.dlf.setCurrent1();
    	Robot.dlf.setCurrent14();
    	Robot.dlf.setCurrent2();
    	Robot.dlf.setCurrent3();
    	Robot.dlf.setCurrent15();
    	Robot.dlf.setRate();
    	Robot.dlf.setTemp();
    	Robot.dlf.setTemp();
    	Robot.dlf.setTotalCurrent();
    	Robot.dlf.setVoltage();
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
