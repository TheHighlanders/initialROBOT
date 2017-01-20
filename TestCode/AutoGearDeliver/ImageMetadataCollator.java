import java.io.*;


public class ImageMetadataCollator {

	public volatile boolean hasTarget = false;
	public static volatile double x = 0;
	public static volatile double y = 0;
	public static volatile double width = 0;
	public static volatile double height = 0;

    private static File f = null;
    private static FileWriter fw = null;
    private static boolean needNewFile = true;
	
    
    
    private static void newFile() {
        File fNew = null;
        FileWriter fwNew = null;
        try {
            fNew = new File("Hello.txt");
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
    
    public static void unpackString(String s) {
        System.out.println("Got data!");
        if (needNewFile) {
            newFile();
            needNewFile = false;
        }
        
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
        try {
            fw.append(x + "\n");
            fw.append(y + "\n");
            fw.append(width + "\n");
            fw.append(height + "\n");
            fw.append("NewData:..." + "\n");
            fw.flush();

        }
        catch (IOException e){
            System.out.println("an issue occured: ");
        }
	}
	
}
