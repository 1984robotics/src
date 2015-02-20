package org.usfirst.frc.team1984.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class DashboardManager {
	
	public static void update(Drivetrain base){
		SmartDashboard.putNumber("Rear Left:", base.getSpeed(0));
		SmartDashboard.putNumber("Front Left:", base.getSpeed(1));
		SmartDashboard.putNumber("Rear Right:", base.getSpeed(2));
		SmartDashboard.putNumber("Front Right:", base.getSpeed(3));
		SmartDashboard.putNumber("Voltage:", DriverStation.getInstance().getBatteryVoltage());
	}
}