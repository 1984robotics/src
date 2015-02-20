package org.usfirst.frc.team1984.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

/**
 * 
 * @author 3044936
 *This class manages the drivetrain, currently configured for
 *Cartesian style mecanum drive. Calls the WPILIB RobotDrive
 *constructor.
	 */
	public class Drivetrain {
	
	private Talon frontRight,
				  frontLeft,
				  rearLeft,
				  rearRight;
	private Joystick stick;	
	private RobotDrive base;
	
	/**
	 * 
	 * @param fl Front left speed controller.
	 * @param rl Rear left speed controller.
	 * @param fr Front right speed controller.
	 * @param rr Rear right speed controller.
	 * @param stick Joystick for getting values.
	 * @param gyro Gyro for field-centric operation.
	 */
	public Drivetrain(int fl, int rl, int fr, int rr, Joystick stick, Gyro gyro){
    	frontLeft = new Talon(fl);
    	rearLeft = new Talon(rl);
    	frontRight = new Talon(fr);
    	rearRight = new Talon(rr);
		base = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		base.setInvertedMotor(MotorType.kFrontLeft, true);
		this.stick = stick;
	}
	
	/**
	 * Nothing fancy, just calls the drive method from the RobotDrive class.
	 */
	public void update(){
		base.mecanumDrive_Polar(stick.getMagnitude(), stick.getDirectionDegrees(), stick.getTwist());
	}
	
	public void manualUpdate(double magnitude, double degrees, double twist){
		base.mecanumDrive_Polar(magnitude, degrees, twist);
	}
	
	public double getSpeed(int channel){
		switch (channel){
			case 0:
					return rearLeft.get();
			case 1:
					return frontLeft.get(); 		
			case 2:
					return rearRight.get();
			case 3:
					return frontRight.get();
			default:
					return 0.0;
				
		}	
	}
}
