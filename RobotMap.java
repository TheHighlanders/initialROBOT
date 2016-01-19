package com.nuetron.robottest;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // PWM
	public static final int LEFT_DRIVE = 0;
	public static final int RIGHT_DRIVE = 1;
	public static final int ENCODER_LEFT_A = 3;
	public static final int ENCODER_LEFT_B = 5;
	
	//OI
	public static final int DRIVER_PAD = 0;
	
}
