
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
  private static DatagramSocket inputSocket;
  private static byte[] buffer;
  

  
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		init();
            
		try {
      
			// receive data and save
			while (moreDataExists) {
        receiveAndProcessData();
			}
			fw.close();
			inputSocket.close();
		}
    catch (IOException e) {
        System.out.println("Starting new file");
        fw = null;
        f = null;
        needNewFile = true;
        needHeader = true;
    }
	}

  
  private static void saveData(FileWriter fw, String data) {
  	try {        
        // is this the exit message?
        if (data.charAt(0) == 'e') {
            if (fw == null ) {
                System.out.println("No FileWriter Open");
            }
            else {
                System.out.println("Starting new file");
                needNewFile = true;
            }
        }

        // is this a header string?
        if (data.charAt(0) == 'h' && needHeader) {
              fw.append(data);
              needHeader = false;
        }
        // is this a data message?
        if (data.charAt(0) == 'd') {
            fw.append(data);
        }
        fw.flush();
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
        needHeader = true;
	}

  private static void init(){
    try {
      inputSocket = new DatagramSocket(4445);
    }
    catch (SocketException e) {
      System.out.println("DatagramSocket Failed to open");
    }
    
    buffer = new byte[256];
    newFile();
  }
  
  /**
   * recieves data and stores it in buffer
   */
  private static void receiveAndProcessData() {
    try {
        DatagramPacket dataPacket = new DatagramPacket(buffer, buffer.length);
        inputSocket.setSoTimeout(5000);
        inputSocket.receive(dataPacket);
        
        //parse to string 
        String dataString = new String(dataPacket.getData(), 0, dataPacket.getLength());
        
        // if need new file, create one here.
        if (needNewFile) {
          newFile();
        }
        saveData(fw, dataString);
    }
    catch (IOException e) {
      System.out.println("Timeout Occured. " + e.getMessage());
      needNewFile = true;
    }
  }
}
