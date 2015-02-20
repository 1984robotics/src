package org.usfirst.frc.team1984.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * 
 * @author 3044936
 * This class manages the lifter mechanism thingie. It's not as
 * exciting, nor as British as it may sound.
 */

public class Lift {
	private Jaguar           lift1;
	private Jaguar           lift2;
	private DoubleSolenoid   brakes;
	
	/**
	 * 
	 * @param one Port in which the first Jag is plugged in.
	 * @param two Port in which the second Jag is plugged in.
	 */
	public Lift(int one, int two){
		lift1    =   new Jaguar(one);
    	lift2    =   new Jaguar(two);
    	brakes   =   new DoubleSolenoid(0,1);
	}
	
	/**
	 * 
	 * @param magnitude Strength/speed of lifter. Can be negative.
	 */
	public void setLift(double magnitude){
		lift1.set(magnitude);
		lift2.set(-magnitude);
	}
	
	public void setBrakes(boolean engaged){
		if (engaged){
    		brakes.set(Value.kReverse);
    	} else{
    		brakes.set(Value.kForward);
    	}
	}
}

