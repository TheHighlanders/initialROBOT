package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveBFWithUInput extends Command {
	/**
	 * if true driving forward if false driving backward
	 */
	boolean drivingForward = true;
	double leftStick;
	double rightStick;
	/**
	 * number of times it travels back and forth
	 */
	int cycles;
	
	public DriveBFWithUInput(int cycles) {
		this.cycles = cycles;
		requires(Robot.dt);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if(drivingForward) {
			Robot.dt.driveLR(0.5, 0.5);
		} else {
			Robot.dt.driveLR(-0.5, -0.5);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
