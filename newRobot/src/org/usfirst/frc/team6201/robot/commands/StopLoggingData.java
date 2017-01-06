package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.subsystems.DataLoggerFetcher;

import dataLogger.DataLoggerPublisherThread;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopLoggingData extends Command {

    public StopLoggingData() {
    }

    // Tells DataLoggerPublisherThread to send a "stop logging message"
    protected void initialize() {
    	Robot.dlf.stopLogging();
    }
    protected void execute() {
    }
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    }
    protected void interrupted() {
    }
}
