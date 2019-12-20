package Tests;

import lejos.hardware.Button;
import robot.Motor_mill;

public class Depose_test {
	Motor_mill motor = new Motor_mill();
	
	Depose_test(){}
	
	public void depose(){
		if(Button.UP.isUp()){
			
		}
		if(Button.DOWN.isUp()){
			motor.motor_mill_echange();
		}
	}

}
