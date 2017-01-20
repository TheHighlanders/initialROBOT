public class ImageMetadataReceiver {
	public static void main(String[] args) {
		new ImageMetadataReceiverThread().start();
        for (int i = 0; i < 10000000; i ++) {
            for (int j = 0; j < 1000; j ++){
                double k = (double) i / j;
            }
        }
	}
}
