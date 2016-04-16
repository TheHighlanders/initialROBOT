package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This command uses the current position of the joysticks to set the power to the left and right motors of the robot.
 * The joystick's position is passed through a tangent curve to allow for precise control near the center region of the joystick and to allow for fast rough control at higher speeds.
 *
 * @author David Matthews
 * @version Apr 16, 2016
 *
 */
public class ArcadeDriveCmd extends Command {
	
	private double rawTurn; // how much turning?
	private double rawPower; // how much speed?
	private double processedTurnOnce; // Turn that has been adjusted via Tan function
	private double processedTurnTwice; // Turn derived from processedOnceTurn and gyro.getRate(). Used to drive the robot at the desired turn rate. This will be fed to the motors
	private double processedPower; // adjusted power
	private final double TANDOMAIN_Y = 1.3; // used for sensitivity of joystick
	private final double TANDOMAIN_X = 1.2; // used for sensitivity of joystick // changed after reading competition
	private final double pTurnGain = 0.05; // This is used for allowing us to drive in a straight line.
	private final double gyroRateGain = 0.05; // This is used to make the desired turn rate as output by the Joystick match the 
	
	
	// uses a tan curve for the sensitivity of the robot vs. joystick position.
	// compresses the values to fit within the desired range of tan, and within the desired output range.
	
	private double scaledValTan (double rawVal, double domain){
		return Math.tan(rawVal*domain) / (Math.tan(domain));
	}
	
    public ArcadeDriveCmd() {
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.resetGyro();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	rawTurn = scaledValTan(Robot.oi.getXAxisOfLogitech(), TANDOMAIN_X);
    	rawPower = scaledValTan(Robot.oi.getYAxisOfLogitech(), TANDOMAIN_Y);
    	
    	processedPower = 0.95*rawPower; // leaves room for turning at full speed ahead.
    	processedTurnOnce = (1-rawPower) * rawTurn; // allows for turning full speed at stop.
    	processedTurnTwice = (processedTurnOnce - gyroRateGain*Robot.dt.getGyroRate())*pTurnGain + processedTurnOnce; // uses the gyro as a feedback loop to drive at the desired turn rate. 
    	
    	SmartDashboard.putNumber("rPower: ", Robot.oi.getYAxisOfLogitech());
    	SmartDashboard.putNumber("rTurn: ", Robot.oi.getXAxisOfLogitech());
    	
    	SmartDashboard.putNumber("pPower: ", processedPower);
    	SmartDashboard.putNumber("p1Turn: ", processedTurnOnce);
    	SmartDashboard.putNumber("p2Turn: ", processedTurnTwice);
    	
    	SmartDashboard.putNumber("GyroAngle: ", Robot.dt.getGyroAngle());
    	SmartDashboard.putNumber("GyroRate: ", Robot.dt.getGyroRate());
    	
    	if (RobotMap.fowardOrReverse == 1){
        	Robot.dt.driveLR(RobotMap.fowardOrReverse*(processedPower + processedTurnTwice), RobotMap.fowardOrReverse*(processedPower - processedTurnTwice));
    	} else {
    		Robot.dt.driveLR(RobotMap.fowardOrReverse*(processedPower - processedTurnTwice), RobotMap.fowardOrReverse*(processedPower + processedTurnTwice));
    	}
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
