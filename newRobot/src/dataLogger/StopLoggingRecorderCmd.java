package dataLogger;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.subsystems.DataLoggerFetcher;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopLoggingRecorderCmd extends Command {

	public StopLoggingRecorderCmd() {
	}

	// Tells DataLoggerPublisherThread to send a "stop logging message"
	protected void initialize() {
		Robot.dlf.stopLoggingRecorder();
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
