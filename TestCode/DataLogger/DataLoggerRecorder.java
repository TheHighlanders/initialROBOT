
/**
 *
 * This is a Data Logger Recorder. It receives data over UDP and records it to a file.
 * TODO: implement timeout
 * @author David Matthews
 * @version Dec 31, 2016
 *
 */

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataLoggerRecorder {

	private static boolean moreDataExists = true;
	private static boolean needNewFile = true;
	private static boolean needHeader = true;
	private static File f = null;
	private static FileWriter fw = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		// init
		DatagramSocket inputSocket = new DatagramSocket(4445);
		byte[] buffer = new byte[256];

		DatagramPacket dataPacket;
		String dataString;
        newFile();
        
		try {

			// receive data and save
			while (moreDataExists) {

				// receive data via UDP packet
				dataPacket = new DatagramPacket(buffer, buffer.length);
				inputSocket.receive(dataPacket);
				
				//parse to string 
				dataString = new String(dataPacket.getData(), 0, dataPacket.getLength());
				
				//process string appropriately.
				saveData(fw, dataString);
			}
			fw.close();
			inputSocket.close();
		} catch (IOException e) {
			System.out.println("Data Logger failed to open log file." + e.getStackTrace());
		}

	}

	private static void newFile() {
		File fNew = null;
		FileWriter fwNew = null;
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			fNew = new File(dateFormat.format(date) + ".csv");
			fwNew = new FileWriter(fNew, true);	
		}
		catch (IOException e) {
			System.out.println("File Failed to be created.");
		}
		
		f = fNew;
		fw = fwNew;
        System.out.println("f and fw set to fNew and fwNew");
        System.out.println(fw);
        System.out.println(f);
        needNewFile = false;
	}

	private static void saveData(FileWriter fw, String data) {
		try {
			// is this the exit message?
			if (data.charAt(0) == 'e') {
				moreDataExists = false;
                System.out.println("No More Data");
			}

			// is this a header string?
			if (data.charAt(0) == 'h') {
				if (needNewFile) {
					newFile();
					needHeader = true;
					needNewFile = false;
				}
				if (needHeader) {
					fw.append(data);
					needHeader = false;
				}
				return;
			}

			// is this a data message?
			if (data.charAt(0) == 'd') {
				fw.append(data);
			}
		} catch (IOException e) {
			System.out.println("Data Logger failed to open log file." + e.getStackTrace());
		}
	}
}
