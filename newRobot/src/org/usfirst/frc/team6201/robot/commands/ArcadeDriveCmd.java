package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *@author David Matthews
 */
public class ArcadeDriveCmd extends Command {


	// declaring variables 

	private double rawTurn; // how much turning?
	private double rawPower; // how much speed?
	
	private double processedOnceTurn; // Turn that has been sent adjusted via Tan function
	private double processedTwiceTurn; // Turn derived from processedOnceTurn and gyro.getRate(). Used to drive the robot at the desired turn rate. This will be fed to the motors
	private double processedPower; // adjusted power
	
	private final double TANDOMAIN_Y = 1.3; // used for sensitivity of joystick
	private final double TANDOMAIN_X = 1.3; // used for sensitivity of joystick
	private final double XTHREEDOMAIN = 0.5; // used for sensitivity of joystick
	
	
	private final double pTurnGain = 1; // This is used for allowing us to drive in a straight line.
											//We MUST test to find the appropriate  value for this.
	private final double gyroRateGain = 1; // This is used to make the desired turn rate as output by the Joystick match the 
	
	
	
	
	
	
	
	// uses a tan curve for the sensitivity of the robot vs. joystick position.
	// compresses the values to fit within the desired range of tan, and within the desired output range.
	
	private double scaledValTan (double rawVal, double domain){
		return Math.tan(rawVal*domain) / (Math.tan(domain));
	}
	
	private double scaledValXThree (double rawVal, double domain){
		return ((Math.pow(rawVal, 3))/(Math.pow(domain,3)));
	}
	
    public ArcadeDriveCmd() {
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.calibrateGyro();
    	Robot.dt.resetGyro();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	rawTurn = scaledValTan(Robot.oi.getXAxisOfLogitech(), TANDOMAIN_X);
    	rawPower = scaledValTan(Robot.oi.getYAxisOfLogitech(), TANDOMAIN_Y);
    	
    	//turn = scaledValXThree (Robot.oi.getXAxisOfLogitech(), XTHREEDOMAIN);
    	//power = scaledValXThree (Robot.oi.getYAxisOfLogitech(), XTHREEDOMAIN);
    	
    	processedPower = 0.95*rawPower; // leaves room for turning at full speed ahead.
    	processedOnceTurn = (1-rawPower) * rawTurn; // allows for turning full speed at stop.
    	processedTwiceTurn = (processedOnceTurn - gyroRateGain*Robot.dt.getGyroRate())*pTurnGain + processedOnceTurn; // uses the gyro as a feedback loop to drive at the desired turn rate. 
    	
    	SmartDashboard.putNumber("rPower: ", Robot.oi.getYAxisOfLogitech());
    	SmartDashboard.putNumber("rTurn: ", Robot.oi.getXAxisOfLogitech());
    	
    	SmartDashboard.putNumber("pPower: ", processedPower);
    	SmartDashboard.putNumber("p1Turn: ", processedOnceTurn);
    	SmartDashboard.putNumber("p2Turn: ", processedTwiceTurn);
    	
    	SmartDashboard.putNumber("GyroAngle: ", Robot.dt.getGyroAngle());
    	SmartDashboard.putNumber("GyroRate: ", Robot.dt.getGyroRate());
    	
    	Robot.dt.driveLR(RobotMap.fowardOrReverse*(processedPower + processedTwiceTurn), RobotMap.fowardOrReverse*(processedPower - processedTwiceTurn));
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
