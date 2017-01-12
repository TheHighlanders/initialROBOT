
import java.io.*;

public class DataLoggerPublisher {
    public static void main(String[] args) throws IOException {
        new DataLoggerPublisherThread().start();
        for (double i = 0; i <4000000; i++) {
            
            DataCollator.totalCurrent.setVal( i);
            DataCollator.gyro.setVal(i);
            DataCollator.motorSpeedLeft.setVal(i);
            DataCollator.motorSpeedRight.setVal(i);
            DataCollator.motorRoller.setVal(i);
            DataCollator.batteryVoltage.setVal(i);
            DataCollator.pdpTemp.setVal(i);
            DataCollator.accelX.setVal(i);
            DataCollator.accelY.setVal(i);
            DataCollator.accelZ.setVal(i);
            DataCollator.current1.setVal(i);
            DataCollator.current2.setVal(i);
            DataCollator.current3.setVal(i);
            DataCollator.current14.setVal(i);
            DataCollator.current15.setVal(i);
            DataCollator.state.setVal("Hello World!" + i);
            System.out.println("Hello World!" + i);

        }
        System.out.println("Goodbye World!");
    }
}
