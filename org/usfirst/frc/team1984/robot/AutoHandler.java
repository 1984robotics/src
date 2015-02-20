package org.usfirst.frc.team1984.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.*;

public class AutoHandler {
	
	public static void runPos1(Drivetrain base, Lift lift, DigitalInput limit){
    	grabTote(lift, limit);
    	rollOut(base,5);
    }
    
	public static void runPos2(Drivetrain base, Lift lift, DigitalInput limit){
    	grabTote(lift,limit);
    	rollOut(base, 0);
    	base.manualUpdate(.5, 0, 0);
    	Timer.delay(3);
    	base.manualUpdate(0, 0, 0);
    	rollOut(base, 5);
    }

    public static void runPos3(Drivetrain base){
    	rollOut(base, 2);
    }
   
    private static void grabTote(Lift lift, DigitalInput limit)
    {
    	while (!limit.get()){
    		lift.setLift(.5);
    		lift.setBrakes(false);
    	}
    	lift.setBrakes(true);
    	lift.setLift(0);
    }
    
    private static void rollOut(Drivetrain base, int time){
       	double begin = Timer.getFPGATimestamp();
    	while(Timer.getFPGATimestamp()-begin < time){
    		base.manualUpdate(.5,90,0);
    	}
       	base.manualUpdate(0,0,0);
    }
}