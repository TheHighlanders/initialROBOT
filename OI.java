package com.nuetron.robottest;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private double deadband(double min, double max, double input){
    	if(input<max && input>min){
    		return 0;
    	}
    	return input;
    	
    }
	private Joystick driverPad = new Joystick(RobotMap.DRIVER_PAD);//number = USB port
	
	public OI() {
		
	}
	public double getLeftJoyStickY() {
		return this.deadband(-.1, .1, driverPad.getRawAxis(1));
	}
	
	public double getRightJoyStickY(){
		return this.deadband(-.1, .1,driverPad.getRawAxis(5));
	}
}

