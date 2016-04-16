package org.usfirst.frc.team6201.robot;

import org.usfirst.frc.team6201.robot.commands.AutoCrossDefenseCmdGrp;
import org.usfirst.frc.team6201.robot.commands.BottomPneumaticOnCmd;
import org.usfirst.frc.team6201.robot.commands.FowardCamCmd;
import org.usfirst.frc.team6201.robot.commands.FowardDriveCmd;
import org.usfirst.frc.team6201.robot.commands.RearCamCmd;
import org.usfirst.frc.team6201.robot.commands.RearDriveCmd;
import org.usfirst.frc.team6201.robot.commands.RollInCmd;
import org.usfirst.frc.team6201.robot.commands.RollOutCmd;
import org.usfirst.frc.team6201.robot.commands.SpinClockwiseCmd;
import org.usfirst.frc.team6201.robot.commands.SpinCounterClockwiseCmd;
import org.usfirst.frc.team6201.robot.commands.TopPneumaticOnCmd;
import org.usfirst.frc.team6201.robot.commands.TurnAngleWithoutZeroingCmd;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 *@author David Matthews
 *@author Max Nadeau
 *@author Adriana Massie
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
		//camera reversal
		Button button3 = new JoystickButton(arcade, 3);
		Button button5 = new JoystickButton(arcade, 5);
		
		button5.whenPressed(new FowardDriveCmd());
		button5.whenPressed(new FowardCamCmd());
	
		button3.whenPressed(new RearDriveCmd());
		button3.whenPressed(new RearCamCmd());

		// ball roller
		Button button4 = new JoystickButton(arcade, 4);
		Button button6 = new JoystickButton(arcade, 6);
		
		button4.whenPressed(new RollInCmd());
		button6.whenPressed(new RollOutCmd());
		
		//spin 
		Button button7 = new JoystickButton (arcade, 7); //Counter Clockwise
		Button button8 = new JoystickButton (arcade, 8); //Clockwise
		
		button7.whileHeld(new SpinCounterClockwiseCmd());
		button8.whileHeld(new SpinClockwiseCmd());
		
		//pneumatics 
		Button button9 = new JoystickButton (arcade, 9); //Top pneumatic
		Button button10 = new JoystickButton (arcade, 10); //Bottom pneumatic
				
		button9.whileHeld(new TopPneumaticOnCmd());
		button10.whileHeld(new BottomPneumaticOnCmd()); 
		
		
		
		
		
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

