package org.usfirst.frc.team6201.robot;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.buttons.Button;
//import org.usfirst.frc.team6201.robot.commands.TankDriveCmd;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	
	// Tank Drive
	private Joystick xbox = new Joystick(RobotMap.XBOX);
	
	public double getLeftYOfXbox(){
		return xbox.getRawAxis(RobotMap.XBOX_LEFT);
	}
	public double getRightYOfXbox (){
		return xbox.getRawAxis (RobotMap.XBOX_RIGHT);
	}
	
	// Arcade Drive
	private Joystick arcade = new Joystick (RobotMap.ARCADE);
	
	public double getRotateAxisOfLogitech(){
		return arcade.getRawAxis(RobotMap.LOGITECH_ROTATE_AXIS);
	}
	
	public double getYAxisOfLogitech(){
		return arcade.getRawAxis(RobotMap.LOGITECH_Y_AXIS);
	}
	
	
	
	

	
}

