

/**
 * This class is used for the final step of the data logger; the logging. Currently it is configured to do a UDP broadcast so that if a stage 3 brownout occurs, we will have the crucial data leading up until the crash.
 * for more information about stage 3 brownouts, please visit our wiki at https://github.com/TheHighlanders/Wiki/wiki/Brownouts
 *
 * @author David Matthews
 * @version Dec 31, 2016
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class DataLoggerPublisherThread extends Thread {

	private DatagramSocket outSocket = null;
	/**
	 * This sets how often a row of data is sent over UDP, in ms. Lower values = more data resolution.
	 */
	private final long SLEEP_TIME = 10;
	/**
	 * Set to false to end this thread.
	 */
	private boolean toLog = true;
	/**
	 * UDP packets may not arrive in the same order at their destination.
	 * This will ensure that we can sort our data to ensure that we have an accurate picture of what happened.
	 */
	private int sequenceNumb = 0;
	private byte[] buffer = new byte[256];
	private InetAddress destaddress = InetAddress.getByName("255.255.255.255");

	/**
	 * creates a DataLoggerPublisherThread called "DataLoggerPublisherThread"
	 * @throws IOException
	 */
	public DataLoggerPublisherThread() throws IOException {
		this("DataLoggerPublisherThread");
	}
	
	/**
	 * creates a DataLoggerPublisherThread.
	 * @param name the name of the thread
	 * @throws IOException
	 */
	public DataLoggerPublisherThread(String name) throws IOException {
		super(name);
		outSocket = new DatagramSocket();
		outSocket.setBroadcast(true);
	}

	/**
	 * Gracefully shuts down logger, sending a message to the UDP receivers that logging is ending.
	 */
	public void stopLogging() {
		toLog = false;
		sendData(getStopMessage());
	}

	/**
	 * Calls on helper methods to ship logging data over UDP until toLog becomes false.
	 * This method occasionally sends a header row so that a receiver can make a new file if desired.
	 */
	public void run() {
		while (toLog) {
			if (sequenceNumb % 100 == 0) {
				sendData(getHeader());
				sequenceNumb++;
			}
            if (sequenceNumb%1000 < 10 && sequenceNumb%1000 > -10 && sequenceNumb> 100) {
        stopLogging();
      }
			sendData(getData());
			sequenceNumb++;
		}
	}

	/**
	 * 
	 * @param s The string to be sent over UDP.
	 */
	private void sendData(String s) {
		try {
			buffer = s.getBytes();
			DatagramPacket outPacket = new DatagramPacket(buffer, buffer.length, destaddress, 4445);
			outSocket.send(outPacket);
			try {
				sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
			}
		} catch (IOException e) {
			e.printStackTrace();
			toLog = false;
		}
	}

	/**
	 * @return A String flagged as a header row for the CSV file.
	 */
	private String getHeader() {
		return ("h," + Integer.toString(sequenceNumb) + "," + DataCollator.getHeader() + "\n");
	}

	/**
	 * 
	 * @return A String flagged as a row of data for a CSV file.
	 */
	private String getData() {
		return ("d," + Integer.toString(sequenceNumb) + "," + DataCollator.getData() + "\n");
	}

	/**
	 * 
	 * @return A string flagged to instruct the CSV FileWriter to gracefully shutdown.
	 */
	private String getStopMessage() {
		return ("e");
	}
}
