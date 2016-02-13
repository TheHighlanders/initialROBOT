package org.usfirst.frc.team6201.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// PWM
	public static final int LEFT_DRIVE1 = 2;
	public static final int RIGHT_DRIVE1 = 0;
	public static final int RIGHT_DRIVE2 =1;
	public static final int LEFT_DRIVE2 = 3;
	
	public static final int ENCODER_LEFT_A = 3;
	public static final int ENCODER_LEFT_B = 0;
	
	public static final double SET_DISTANCE_PER_PULSE = 1;
	
	/// OI
	
	// TankdriveCmd
	public static final int XBOX = 0;
	public static final int ARCADE = 1;
	public static final int UP_AXIS = 0;
	public static final int XBOX_RIGHT = 0;
	public static final int XBOX_LEFT = 0;
	
	
	// ArcadeDriveCmd
	public static final int LOGITECH = 1;
	public static final int LOGITECH_X_AXIS = 0;
	public static final int LOGITECH_Y_AXIS = 1;
	public static final int LOGITECH_ROTATE_AXIS = 2;

	// Analog
	public static final int IR_COLLIDER_DECTOR = 0;
	public static final int LED = 1;
	
	
}