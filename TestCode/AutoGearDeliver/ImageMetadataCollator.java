public class ImageMetadataCollator {

	public volatile boolean hasTarget = false;
	public static volatile double x = 0;
	public static volatile double y = 0;
	public static volatile double width = 0;
	public static volatile double height = 0;
	
	public static void unpackString(String s) {
		String xString = s.substring(0, s.indexOf(','));
		s = s.substring(s.indexOf(',') + 1);
		String yString = s.substring(0, s.indexOf(','));
		s = s.substring(s.indexOf(',') + 1);
		String wString = s.substring(0, s.indexOf(','));
		s = s.substring(s.indexOf(',') + 1);
		String hString = s;
		x = Double.parseDouble(xString);
		y = Double.parseDouble(yString);
		width = Double.parseDouble(wString);
		height = Double.parseDouble(hString);
		System.out.println("Unpacking new string\n");
		System.out.println(x);
		System.out.println(y);
		System.out.println(width);
		System.out.println(height);
	}
	
}
