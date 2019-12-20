package robot;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.utility.Delay;

public class Motor_mill {
	//variable globale		
	int mill_speed_echange = 300; //en degré par seconde
	int mill_speed_depose = 120; //en degré par seconde
	int mill_speed_prehension = 200; //en degré par seconde

	NXTRegulatedMotor mill_motor = Motor.D;

	public Motor_mill(){}

	public void motor_mill_echange(){
		//pour ramener la balle en haut
		mill_motor.setSpeed(mill_speed_echange);
		float current_position = mill_motor.getPosition();
		mill_motor.rotateTo(Math.round(current_position) + 90);	
		//mill_motor.forward();	
		mill_motor.stop();
	}
	public void motor_mill_correction(){
		mill_motor.setSpeed(mill_speed_prehension);
		float current_position = mill_motor.getPosition();
		mill_motor.rotateTo(Math.round(current_position) + 5);	
		mill_motor.setSpeed(-mill_speed_depose);
		current_position = mill_motor.getPosition();
		mill_motor.rotateTo(Math.round(current_position) - 95);	
		
	}
	public void motor_mill_prehension(){
		//pour ramener la balle en haut
		mill_motor.setSpeed(mill_speed_prehension);
		float current_position = mill_motor.getPosition();
		mill_motor.rotateTo(Math.round(current_position) + 90);	
		//mill_motor.forward();	
		mill_motor.stop();
	}

	public void motor_mill_depose(){
		mill_motor.setSpeed(-mill_speed_depose);
		float current_position = mill_motor.getPosition();
		mill_motor.rotateTo(Math.round(current_position) - 90);		
		//mill_motor.backward();
		mill_motor.stop();
	}

	public boolean blocked_mill(){
		float current_position = mill_motor.getPosition();
		//LCD.drawString(String.valueOf(current_position), 5, 1);
		//mill_motor.rotate(80);
		//mill_motor.rotateTo(0);
		boolean moulin_decalé = true;
		for(int k=0 ; k==10 ; k++){
			if(Math.abs(current_position) == 90*k){
				moulin_decalé = false;
			}
		}
		Delay.msDelay(500);
		return moulin_decalé;
	}

	public void init_mill(){
		float current_position = mill_motor.getPosition();
		LCD.clear();

		//alors le moulin est décalé
		while(Math.abs(current_position) != 0
				|| Math.abs(current_position) != 90
				|| Math.abs(current_position) != 90*2
				|| Math.abs(current_position) != 90*3
				|| Math.abs(current_position) != 90*4
				|| Math.abs(current_position) != 90*5
				|| Math.abs(current_position) != 90*6
				|| Math.abs(current_position) != 90*7
				|| Math.abs(current_position) != 90*8
				|| Math.abs(current_position) != 90*9
				|| Math.abs(current_position) != 90*10
				){
			mill_motor.rotate(1);
			current_position = mill_motor.getPosition();
			LCD.drawString(String.valueOf(Math.abs(current_position)), 5, 1);
		}

	}


	public static void main(String[] args){
		new Motor_mill();
	}


}
