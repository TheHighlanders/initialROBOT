package org.usfirst.frc.team6201.robot.commands;



import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;



public class TankDriveCmd extends Command {

    public TankDriveCmd() {
    	requires (Robot.dt);
    }

    protected void initialize() {
    }
    protected void execute() {
    	double rightStick = Robot.oi.getRightStick();
    	double leftStick = Robot.oi.getLeftStick();
    	Robot.dt.driveLR(leftStick, rightStick);
    			
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
