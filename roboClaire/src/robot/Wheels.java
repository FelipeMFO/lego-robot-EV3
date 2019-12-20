package robot;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;


public class Wheels {
	
	static NXTRegulatedMotor left_motor = Motor.B;
	static NXTRegulatedMotor right_motor = Motor.C;
	int wheel_speed = 80; //en degré par seconde
	
	
	public Wheels(){
		left_motor.setSpeed(wheel_speed);
		right_motor.setSpeed(wheel_speed);		
	}

	
	public void move_backward(){
		left_motor.forward(); //until stop is called
		right_motor.forward();
	}
	
	public void move_forward(){
		left_motor.backward();
		right_motor.backward();
	}
	
	public void move_forward_milsec(int temps){
		stop();
		long cur_time = System.currentTimeMillis();
		move_forward();
		while(System.currentTimeMillis() - cur_time < temps){
		}
		stop();
	}
	public void move_backward_milsec(int temps){
		stop();
		long cur_time = System.currentTimeMillis();
		move_backward();
		while(System.currentTimeMillis() - cur_time < temps){
		}
		stop();
	}
	
	public void turn_left(){
		left_motor.forward(); //until stop is called
		right_motor.backward();		
	}
	
	public void turn_left_milsec(int temps){
		long cur_time = System.currentTimeMillis();
		while(System.currentTimeMillis() - cur_time < temps){
			right_motor.backward();
			left_motor.forward();
		}
		stop();
	}
	
	public void turn_right(){
		right_motor.forward();
		left_motor.backward();	
	}
	
	public void turn_right_milsec(int temps){
		long cur_time = System.currentTimeMillis();
		while(System.currentTimeMillis() - cur_time < temps){
			left_motor.backward();
			right_motor.forward();
		}
		stop();
	}
	
	public void back_right_milsec(int temps){
		long cur_time = System.currentTimeMillis();
		while(System.currentTimeMillis() - cur_time < temps){
			right_motor.forward();
		}
		stop();
	}
	
	public void stop(){
		right_motor.stop(true);	
		left_motor.stop(true);	
	}
	
}
