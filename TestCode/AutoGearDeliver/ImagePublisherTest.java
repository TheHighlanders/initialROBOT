
import java.io.*;
import java.net.*;
import java.util.*;

public class ImagePublisherTest {
    private static DatagramSocket outSocket = null;
    /**
     * This sets how often a row of data is sent over UDP, in ms. Lower values = more data resolution.
     */
    private static final long SLEEP_TIME = 10;
    /**
     * Set to false to end this thread.
     */
    private static boolean toLog = true;
    /**
     * UDP packets may not arrive in the same order at their destination.
     * This will ensure that we can sort our data to ensure that we have an accurate picture of what happened.
     */
    private static int sequenceNumb = 0;
    private static byte[] buffer = new byte[512];
    private static InetAddress destaddress;

    
	public static void main (String [] args){
        try {
            destaddress = InetAddress.getByName("255.255.255.255");
            outSocket = new DatagramSocket();
            outSocket.setBroadcast(true);
        }
        catch (Exception e){
            System.out.println("Failed to open socket");
        }
        while (toLog) {
            if (sequenceNumb%100000000 < 10 && sequenceNumb%10000000 > -10 && sequenceNumb> 100) {
                System.exit(0);
            }
            sendData("01284,12.124185,10257.0807,0.912580");
            sequenceNumb++;
        }
    }
    
    private static void sendData(String s) {
        try {
            buffer = s.getBytes();
            if (buffer == null) {
                System.out.println("buffer null");
            }
            if (destaddress == null) {
                System.out.println("destaddr null");
            }
            DatagramPacket outPacket = new DatagramPacket(buffer, buffer.length, destaddress, 5005);
            outSocket.send(outPacket);
            
        } catch (IOException e) {
            e.printStackTrace();
            toLog = false;
        }
    }
}
