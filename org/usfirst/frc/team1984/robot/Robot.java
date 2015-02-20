
package org.usfirst.frc.team1984.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * main class for the robot. duh.
 */
public class Robot extends IterativeRobot {
   
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	private Joystick        drive,
						    liftControl;
	private Gyro            gyro;
	private Drivetrain      base;
	private Lift            lift;
	private DigitalInput    limit;
	private SendableChooser autoChooser;
	private CameraServer    server;

    public void robotInit() {
    	drive        =   new Joystick(0);
    	liftControl  =   new Joystick(1);
    	gyro         =   new Gyro(0);
    	lift         =   new Lift(4,5);
    	limit        =   new DigitalInput(0);
    	base         =   new Drivetrain(1,0,3,2, drive, gyro);
    	autoChooser = new SendableChooser();
    	autoChooser.addDefault("Pos1", Integer.valueOf(0));
    	autoChooser.addObject("Pos2", Integer.valueOf(1));
    	autoChooser.addObject("Pos3", Integer.valueOf(2));
    	SmartDashboard.putData("Autonomous", autoChooser);
    	SmartDashboard.putNumber("Auto Delay", 1);
    	server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture("cam1");
    } 

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit() {
    	int automode = ((Integer) autoChooser.getSelected()).intValue();

    	switch (automode)
    	{
    	    case 0:
    	        AutoHandler.runPos1(base, lift, limit);
    	        break;

    	    case 1:
    	        AutoHandler.runPos2(base, lift, limit);
    	        break;

    	    case 2:
    	        AutoHandler.runPos3(base);
    	        break;

    	    default:
    	        AutoHandler.runPos1(base, lift, limit);
    	        break;
    	}
    	DriverStation.reportError("Exiting Autonomous.", false);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	SmartDashboard.putNumber("Gyro: ", gyro.getAngle());
    	SmartDashboard.putBoolean("limit: ", limit.get());
    	base.update();
    	DashboardManager.update(base);
    	lift.setLift(liftControl.getY()); 
    	lift.setBrakes(liftControl.getRawButton(1));
    }
    
    /** 
     * This function is called periodically during test mode
     */
    public void testPeriodic() {

    }
    
    public void disabledPeriodic(){
    	if (liftControl.getRawButton(1)){	
    		DriverStation.reportError("Calibrating Gyro...", false);
    		gyro.initGyro();
    		DriverStation.reportError("Done.", false);
    	}
    }
}


