public class ImageMetadataReceiver {
	public static void main(String[] args) {
		new ImageMetadataReceiverThread().start();
		System.out.println("Receiving Data");
	}
}
