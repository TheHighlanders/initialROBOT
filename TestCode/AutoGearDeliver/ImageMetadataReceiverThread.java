import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ImageMetadataReceiverThread extends Thread {
	
	private DatagramSocket inputSocket = null;
	private boolean ableToReceive = false;
	private byte[] buffer = new byte[256];
	private DatagramPacket dataPacket;
	private String dataString;
	
	public void run() {
		if(!ableToReceive) {
			try {
				inputSocket = new DatagramSocket(5005);
				ableToReceive = true;
			} catch(IOException e) {
				ableToReceive = false;
			}
		} else if(ableToReceive) {
			try {
				dataPacket = new DatagramPacket(buffer, buffer.length);
				inputSocket.receive(dataPacket);
				dataString = new String(dataPacket.getData(), 0, dataPacket.getLength());
				ImageMetadataCollator.unpackString(dataString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}