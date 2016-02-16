package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.RollInCmd;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Roller extends PIDSubsystem {
	private Victor motor = new Victor (RobotMap.ROLLER);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public static double P = 0;
	public static double I = 0;
	public static double D = 0;
	public Roller(){
		super ("Roller", P, I, D);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new RollInCmd());
    }
    
    public void roll(double power){
    	motor.set(power);
    }
    
    public void stop(){
    	roll(0);
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double arg0) {
		// TODO Auto-generated method stub
		
	}
}

