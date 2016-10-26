package org.usfirst.frc.team6201.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick xbox = new Joystick(0);
    
    public double getLeftStick() {
    	return xbox.getRawAxis(1);
    }
    
    public double getRightStick() {
    	return xbox.getRawAxis(0);
    }
}

