package org.usfirst.frc.team6201.robot.subsystems;



import java.io.IOException;

import dataLogger.*;
import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 *@author David Matthews
 */
public class DataLoggerFetcher extends Subsystem {
	private PowerDistributionPanel powerPanel;
	private	ADXRS450_Gyro gyro;
	private ADXL362 accel;
	private DataLoggerPublisherThread loggerPublisherThread;
	public DataLoggerFetcher() {
		powerPanel = new PowerDistributionPanel(0);
		gyro = new ADXRS450_Gyro();
		accel = new ADXL362(Accelerometer.Range.k16G);
		
		try {
			loggerPublisherThread = new DataLoggerPublisherThread();
			loggerPublisherThread.start();
		}
		catch (IOException e) {
			System.out.println("DataLoggerPublisherThread().start(); crashed" + e.getStackTrace());
		}
		

	}
	
	

	public void stopLoggingRecorder() {
		loggerPublisherThread.stopLoggingRecorder();
	}

	
	public void initDefaultCommand() {
        setDefaultCommand(new DataLoggerScannerCmd());
    }

	public void setTotalCurrent() {
		dataLogger.DataCollator.totalCurrent.setVal(powerPanel.getTotalCurrent());		
	}
	public void setCurrent1() {
		dataLogger.DataCollator.current1.setVal(powerPanel.getCurrent(1));
	}
	public void setCurrent2() {
		dataLogger.DataCollator.current2.setVal(powerPanel.getCurrent(2));
	}
	public void setCurrent3() {
		dataLogger.DataCollator.current3.setVal(powerPanel.getCurrent(3));
	}
	public void setCurrent14() {
		dataLogger.DataCollator.current14.setVal(powerPanel.getCurrent(14));
	}
	public void setCurrent15() {
		dataLogger.DataCollator.current15.setVal(powerPanel.getCurrent(15));
	}
	
	public void setVoltage() {
		dataLogger.DataCollator.batteryVoltage.setVal(powerPanel.getVoltage());
	}
	public void setTemp() {
		dataLogger.DataCollator.pdpTemp.setVal(powerPanel.getTemperature());
	}
	public void setRate() {
		dataLogger.DataCollator.gyro.setVal(gyro.getRate());
	}
	public void setAccelX() {
		dataLogger.DataCollator.accelX.setVal(accel.getX());
	}
	public void setAccelY() {
		dataLogger.DataCollator.accelY.setVal(accel.getY());
	}
	public void setAccelZ() {
		dataLogger.DataCollator.accelZ.setVal(accel.getZ());
	}
}

