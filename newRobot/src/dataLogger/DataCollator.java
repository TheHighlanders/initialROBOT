package dataLogger;
/**
 * This class is used to store the DataField objects that are being logged.  It has methods to generate header Strings, and data Strings for CSV files.
 * See DataLoggerPublisher for how these methods get used.
 *
 * To add another DataField to the logger, add the DataField below, and in the dataFieldArray.
 * This will add it to the log files. See DataField for how to update the data that is being logged.
 * 
 * 
 * @author David Matthews
 * @version Dec 31, 2016
 *
 */
public class DataCollator {
	
	public static DataField totalCurrent = new DataField("totalCurrent");
	public static DataField gyro = new DataField("gyro");
	public static DataField motorSpeedLeft =  new DataField("motorSpeedLeft");
	public static DataField motorSpeedRight =  new DataField("motorSpeedRight");
	public static DataField motorRoller =  new DataField("motorRoller");
	public static DataField batteryVoltage =  new DataField("batteryVoltage");
	public static DataField pdpTemp =  new DataField("pdpTemp");
	public static DataField accelX =  new DataField("accelX");
	public static DataField accelY =  new DataField("accelY");
	public static DataField accelZ =  new DataField("accelZ");
	public static DataField current1 =  new DataField("current1");
	public static DataField current2 =  new DataField("current2");
	public static DataField current3 =  new DataField("current3");
	public static DataField current14 =  new DataField("current14");
	public static DataField current15 =  new DataField("current15");
	private static DataField[] dataFieldArray  = {totalCurrent, gyro, motorSpeedLeft, motorSpeedRight, motorRoller, batteryVoltage, pdpTemp, accelX, accelY, accelZ, current1, current2, current3, current14, current15, totalCurrent};

	/**
	 * 
	 * @return A String holding the names of the DataField's listed in the dataFieldArray.
	 */
	public static String getHeader() {
		StringBuilder s = new StringBuilder ();
		for (DataField df :dataFieldArray) {
			if (s.length() > 0) {
				s.append(",");
			}
			s.append(df.getName());
		}
		return s.toString();
	}

	/**
	 * 
	 * @return A String holding the current values of the DataField's listed in the dataFieldArray.
	 */
	public static String getData() {
		StringBuilder s = new StringBuilder ();
		for (DataField df :dataFieldArray) {
			if (s.length() > 0) {
				s.append(",");
			}
			s.append(df.getVal());
		}
		return s.toString();
	}
}
