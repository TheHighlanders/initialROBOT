package org.usfirst.frc.team6201.robot.subsystems;



import org.usfirst.frc.team6201.robot.DataCollection;
import org.usfirst.frc.team6201.robot.commands.PDPRecorder;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 *
 */
public class Power extends Subsystem {
	private PowerDistributionPanel powerPanel;
	private	ADXRS450_Gyro gyro;
	private ADXL362 accel;
	public Power() {
		powerPanel = new PowerDistributionPanel(0);
		gyro = new ADXRS450_Gyro();
		accel = new ADXL362(Accelerometer.Range.k16G);
	}
	
	public void initDefaultCommand() {
        setDefaultCommand(new PDPRecorder());
    }

	public double getTotalCurrent() {
		return powerPanel.getTotalCurrent();		
	}
	public double getCurrentChannel(int channel) {
		return powerPanel.getCurrent(channel);
	}
	public double getVoltage() {
		return powerPanel.getVoltage();
	}
	public double getTemp() {
		return powerPanel.getTemperature();
	}
	public double getRate() {
		return gyro.getRate();
	}
	public double getAccelX() {
		return accel.getX();
	}
	public double getAccelY() {
		return accel.getY();
	}
	public double getAccelZ() {
		return accel.getZ();
	}
}

