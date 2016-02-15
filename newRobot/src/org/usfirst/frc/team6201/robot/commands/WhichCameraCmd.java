package org.usfirst.frc.team6201.robot.commands;


import org.usfirst.frc.team6201.robot.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author David Matthews
 * 
 * provides a feed to the dashboard when called.
 * Has the ability to toggle cameras being fed to the dashboard.
 */

public class WhichCameraCmd extends Command {

	private final int camFront;
	private final int camRear;
	private int curCam;
	private Image frame;
	private CameraServer server;
	
    public WhichCameraCmd() {
        // Get camera ids by supplying camera name ex 'cam0', found on roborio web interface
        camFront = NIVision.IMAQdxOpenCamera(RobotMap.FRONT_CAM, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        camRear = NIVision.IMAQdxOpenCamera(RobotMap.REAR_CAM, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        curCam = camFront;
        
        // Img that will contain camera img
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // Server that we'll give the img to
        server = CameraServer.getInstance();
        server.setQuality(RobotMap.IMG_QUALITY);
    }

    public int getFrontCam(){
    	return camFront;
    }
    
    public int getRearCam(){
    	return camRear;
    }
    public void initialize() {
    	changeCam(camFront);
    	
    }

    public void execute() {
    	
		updateCam();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

		NIVision.IMAQdxStopAcquisition(curCam);
    }

    protected void interrupted() {

		NIVision.IMAQdxStopAcquisition(curCam);
    }
    
    /**
	 * Change the camera to get imgs from to a different one
	 * @param newId for camera
	 */
	public void changeCam(int newId)
    {
		NIVision.IMAQdxStopAcquisition(curCam);
    	NIVision.IMAQdxConfigureGrab(newId);
    	NIVision.IMAQdxStartAcquisition(newId);
    	curCam = newId;
    }
    
	/**
	 * Get the img from current camera and give it to the server
	 */
    public void updateCam()
    {
    	NIVision.IMAQdxGrab(curCam, frame, 1);
        server.setImage(frame);
    }
}
