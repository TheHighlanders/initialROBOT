package org.usfirst.frc.team6201.robot;

import org.usfirst.frc.team6201.robot.commands.FowardOrReverseCmd;
import org.usfirst.frc.team6201.robot.commands.SwitchCamCmd;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 *@author David Matthews
 *
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
	
	public double getXAxisOfLogitech(){
		return arcade.getRawAxis(RobotMap.LOGITECH_X_AXIS);
	}
	
	public double getYAxisOfLogitech(){
		return arcade.getRawAxis(RobotMap.LOGITECH_Y_AXIS);
	}
	
	public OI () {
		Button button2 = new JoystickButton(arcade, 2);
		
		button2.whenPressed(new FowardOrReverseCmd());
		Timer.delay(0.001);
		button2.whenPressed(new SwitchCamCmd());
			
	}
	
	
	
	
	 //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

}

