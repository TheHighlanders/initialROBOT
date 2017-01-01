/**
 * 
 * This class is used to allow for easy adding of new data fields to track. The values of the DataField should be updated using setVal()
 * in a subsystem. For sensors, create a method in DataLoggerFetcher and add a call to that method in DataLoggerScanner.
 * 
 * @author David Matthews
 * @version Dec 31, 2016
 *
 */
public class DataField {
	public String name;
    public volatile double value = 0;
	
	/**
	 * This constructor sets the name of the DataField. 
	 * 
	 * @param n The name of the DataField as will appear in the log files.
	 */
	public DataField (String n) {
		name = n;
	}
	
	/**
	 * @param v The updated value of the DataField
	 */
	public void setVal (double v) {
		value = v;
	}
	
	/**
	 * 
	 * @return the current value of the DataField.
	 */
	public double getVal (){
		return value;
	}
	
	/**
	 * 
	 * @return the name of the DataField.
	 */
	public String getName() {
		return name;
	}
}
