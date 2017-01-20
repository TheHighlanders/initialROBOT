import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ImageMetadataReceiverThread extends Thread {
	
	private DatagramSocket inputSocket = null;
	private boolean ableToReceive = false;
    private byte[] buffer;
	private DatagramPacket dataPacket;
	private String dataString;
	
	public void run() {
        while (true ){
            System.out.println("Meow");
            if(!ableToReceive) {
                System.out.println("Attempting to open inputSocket");
                try {
                    buffer = new byte[256];
                    inputSocket = new DatagramSocket(5005);
                    ableToReceive = true;
                    
                } catch(IOException e) {
                    ableToReceive = false;
                    System.out.println("Failed to open inputSocket");
                }
            }
            try {
                // Recieve data
                dataPacket = new DatagramPacket(buffer, buffer.length);
                inputSocket.receive(dataPacket);
                
                // Parse to string
                dataString = new String(dataPacket.getData(), 0, dataPacket.getLength());
                System.out.println(dataString);
                
                //Send to filewriter
                ImageMetadataCollator.unpackString(dataString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
	}
}
