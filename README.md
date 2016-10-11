
David Matthews  
03 October 2016
# The Software of FRC team 6201 
## Table of Contents 

{{TOC}}
## Current state + Brief History
In the 2016 FRC Build Season, no-one on our team was familiar with the software of FRC.
Three members of our team (David Matthews, Max Nadaeu, and Adriana Massie) together were able to create software to drive our robot with a joystick and send a camera feed to the driving computer.
Throughout the build season, our robot would occasionally fail to start up, complaining that something was crashing while initializing it.
We were not quite sure of the cause and because it was infrequent we ignored it.

At our first competition, our robot had some issues.
Our robot’s battery would mysteriously drop to very low voltages and the robot would reboot during the matches.
After one of the veteran teams helped us investigate why this was occurring, we discovered that our motors were drawing a ton of current.
We were able to hack together a solution by disabling half of our motors. 
This eliminated our brownouts, but made our robot have a harder time crossing obstacles.

Although we do not know exactly what was causing these brownouts, we believe them to be attributed to an increase in the friction between the wheels and the floor.
At Somerville High School we were test driving out robot on waxed floors, and occasionally a concrete floor.
These floors have much lower friction than the rugs of the competition field. 

At our second competition, we discovered the value of cameras and the value of having a git repository.
The crashes that we experienced when our robot was booting up from the build season came back.
Our robot did not move.
We suspected that some of the new software we had written since the last competition had caused the errors, but the cameras were   also not appearing correctly in the roboRIO’s webpage.
After spending many matches trying to determine what changed that caused our crashes, we were able to revert back to some software we had been using reliably in the past and after disabling the cameras had a moveable robot.
Without our cameras, we really struggled to cross the obstacles, and we struggled to score balls in the tower.

Despite the many issues we had, our software worked very well for it being our first year.
We created a control system that optimized both slow fine movements, and high speed travel.
This control system was very intuitive to drive and was a major asset to our robot.
Although our cameras failed on us, we were able to get a video stream to the driving computer which enabled us to get a better sense of where we were on the field.
We also were able to experiment with pneumatics control, and sensors.

As we begin the FRC 2017 pre-season, we can learn from our mistakes to function more fluidly.

## Briefly on Git

Git was the tool that enabled us to bring our robot back to life, but have not been using git to it’s full potential.
I have since talked with a software engineer who works on robots that creates some of the chips in our computers.
They helped me to outline what a workflow with git looks like, and emphasized to me how git is more than just a tool for keeping track of a history, it is mainly a tool for working together.
Git allows teams to work in parallel on projects related to each other, and merge these sub-projects together into larger projects.

Later I will talk more specifically how we will use git.

## Major Projects of 2016-17 yr
### Data Logger
#### Why we need it
Logging data is probably the single most important software project for our team.
This project will enable us to better analyze our robot and improve all aspects of it: Mechanically, electrically, and with software.

As I briefly mentioned before, at our first competition we were attempting to draw too much power from our robot and caused brown outs (Voltage drops very low).[^In lead acid batteries, the voltage sags as more current is drawn.]
Brownouts are really bad.
When the RoboRIO shuts off due to a brownout, it takes at least 30 seconds for it to power back on.
To this date we do not know what causes these brownouts.
If we could record data points such as:

- Battery voltage
- Current draw of motors,
- Rate of turn (Gyroscope)
- Acceleration

We could help to pinpoint exactly what was causing our brownouts.
This information is invaluable to the electoral, mechanical, and software elements of the robot.
Knowing why things our occurring will enable us to comprehensively assess issues and address as appropriate.

For example, lets take a closer look at our brownouts.
To solve our problem we decided to take an electoral approach; we disconnected 1/2 of our motors -- reducing our torque by a factor of two.
This made it harder to cross the moat, and harder to cross the rough terrain.

Our solution was not very eloquent.
There are many other ways that we can solve this problem but they all require more data.
They all require knowing exactly what is causing the surge in power draw.
If we could pinpoint this we can solve the problem through:
- Changing our driving style to avoid brownouts
- Preventing the robot from drawing too much power in risky situations through software limits
- Mechanically changing the design of the robot.

Once we have our data logger working, and some data collected, I plan for us to reenable the other 2 motors and try to brownout the robot so that we can determine what is causing it.

The value of data logging goes well beyond brownouts.
Data logging will help us better analyze our robot’s design, improving efficiency, and enabling us to more gracefully solve any problems that come up.
Data logging all error messages, and the transitions of state in our robot can help us to better analyze and improve our software.
If we had had data from our robot up until the software tried to initialize the cameras, then we would have known during the build season that we needed to switch to IP cameras.
Our camera’s was the major reason why we preformed much worse at our second competition at Dartmouth.

#### What it should look like 
Our robot’s data logger will have two parts; a part on the roboRIO[^The computer that FIRST requires us to use for safety reasons], and an external part.

#### Why we should start with this project
Besides being a very important project, this project also has aspects which everyone can contribute to.

##### What we will record
The following is the beginnings of a list of fields of data that we will want to track.
We will very likely be adding to this in the future.

- Software output power to each motor
- Rate of turning (Gyroscope)
- Acceleration (Accelerometer)
- Battery voltage
- Total current draw on the PDP
- Current draw on each motor (PDP Object will have this data.)
- Switches between each stage of the competition
- Please email me any other ideas you have -- dmatthewsshs@gmail.com
##### roboRIO
The roboRIO is not a good place to save our data.
In the event of a power failure it may not write the data to disk and thus we may be unable to capture crucial data to assess a problem.
The data is only useful to us if we can access it easily.
Broadcasting the data will provide us flexibility to both store long histories of data, record it to a computer and/or view it live when addressing a problem.

The roboRIO software will have two parts; collecting the data in static fields of a public class [^This would be similar to the RobotMap.java except with non-Final variables], and broadcasting the data as a string via UDP to our local network [^To repeatedly send data, we could create a subsystem and set the default command to send the data. This would provide flexibility to also receive data from a raspberry Pi via UDP if desired.].

##### External // raspberry Pi
The raspberry pi is a good place to store our data.
They are a fairly cheap credit card sized fully functional computer.
This makes them very easy to learn how to program on.
All of their information is stored on a micro SD card which means that they are very easy to revert back to previous copies of the code through git.
They are also powered via 5v, which will not cut off even if the RoboRIO has a brownout.

#### Breakdown of roles
A few teams:

- Publishing data fields to static fields in a public class.
- UDP Broadcasting strings to a local network [^Here is a link that might be a good starting place: [https://docs.oracle.com/javase/tutorial/networking/datagrams/index.html](https://docs.oracle.com/javase/tutorial/networking/datagrams/index.html)]
- Receiving a UDP packet on the raspberryPi and writing it to file as a string.
- Saving files in a directory in a way that makes logical sense.
	- Should create a new file when robot powers on, and at other key points so each file does not contain too much data.
### Vision for gameplay // Email dmatthewsshs@gmail.com if you want to work on this on the side.

For the past 4+ yrs, FRC has had retro-reflective vision targets[^Retro-reflective means that light rays go back towards their source mostly. This is useful because sticking a light near a camera means that the retroreflective elements will light up like a christmas tree.][^found this which might be a good resource for introduction to some if these things [https://www.intorobotics.com/opencv-tutorials-best-of/](https://www.intorobotics.com/opencv-tutorials-best-of/)]

There are two abstract elements of this project; reliably finding the target from a video feed, and determining how the robot should react to this information.
#### Possible objectives for finding the target. // listed in no particular order.
##### Local computation during Auto part
- We want this piece of software to be as efficient as possible because during autonomous communications are limited.
Likely this software will run on a raspberry Pi, but other small computation machines do exist (Ex. Intel NUC).
##### Please refer to picking up game pieces:

- If we can identify the game pieces then we can autonomously pick them up.
- This could greatly improve our efficiency.
In FRC 2016, we often struggled to pick up balls; missing aligning them which caused us to waste valuable time during the matches.

#### Possible objectives for reacting to this information // listed from easiest to hardest.
##### Picking up game pieces
- FRC 2016 had balls on the field. If we could pick them up reliably and efficiently then we would have more time to traverse obstacles and score.
- Keep in mind that because FRC 2016 had balls it is not very likely that FRC 2017 will have them too, but this would still be a cool exercise that is easier than driving to a low goal and scoring
##### Throwing a projectile
Two main ideas of how to use the data from the camera;

- Aim a shooter and fire the projectile at the target.
- Autonomously drive the robot to a better place to fire the projectile to the target.
- Autonomously drive the robot to a fixed place to shoot the projectile in a constant manner. 

##### Autonomously driving the robot during auto mode.

- During FRC 2016, one could score balls in the low or high goals. The high goals were the only ones with Retro-reflective tape, but our robot was unable to shoot for the high goals. Once we have some information about the high goal, how can we drive the robot to score in the low goal[^Information available will include the position of the center of the target, it’s height and width, and possible other pieces of information.]?


### Other: Tell me Your ideas! -- dmatthewsshs@gmail.com