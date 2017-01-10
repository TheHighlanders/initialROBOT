## Testing Process

Open terminal, and run the following;
You will need to javac all the classes first. 

* DataLoggerRecorder
* DataLoggerPublisher


## What this tests

The following are unchanged from the robot except for removing a package name
* DataLoggerRecorder
* DataField
* DataCollator
* DataLoggerPublisherThread.

DataPublisher will not exist on the robot.
This class currently serves to start the DataLoggerPublisherThread,
and to update the values of the DataFields, which are objects within the DataCollator Class.

On the robot, the Subsystems will be updating the values of the DataFields which are stored in the DataCollator Class.

The DataLogger Subsystem (I forget it's exact name) will start the DataLoggerPublisherThread.


Test results appear in .CSV files, titled with the approximate date and time that they were created.

## Notes:

The Data Logger as a whole does not care if you start the publisher or the recorder first. If you start the recorder first then it will wait for the first packet and get the initial header. If this packet is missed either because of a packet loss or because the recorder is started after the publisher then the recorder will insert a header when it is next sent.
