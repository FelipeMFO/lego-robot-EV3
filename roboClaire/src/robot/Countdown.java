package robot;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Countdown {
	long previous_time;
	boolean go = false;
	boolean debut = true;
	boolean bool3 = false;
	boolean bool2 = false;
	boolean bool1 = false;
	//Decision set_position_depart = new Decision();


	public Countdown(){
		previous_time = System.currentTimeMillis();
		LCD.drawString("Wait...", 5, 2);
	}
	
	public void compte_a_rebourd(){
		//set_position_depart.setPosition(0);
		long current_time = System.currentTimeMillis();
		if(debut == true){
			debut_countdown();
			previous_time = current_time;
			debut = false;
		}
		
		if(current_time - previous_time >= 1 && bool3 == false){
			LCD.drawString("3...", 5, 4);
			previous_time = current_time;
			bool3=true;
		}
		if(current_time - previous_time >= 1 && bool2 == false){
			LCD.drawString("2...", 5, 4);
			previous_time = current_time;
			bool2=true;
		}
		if(current_time - previous_time >= 1 && bool1 == false){
			LCD.drawString("1...", 5, 4);
			previous_time = current_time;
			bool1=true;
		}
		if(current_time - previous_time >= 1 && bool1 == true){
			LCD.clear();
			LCD.drawString("GO !", 5, 4);
			Delay.msDelay(500);
			LCD.clear();
			go = true;
		}		
	}
	
	public void debut_countdown(){
		LCD.drawString("Wait...", 5, 2);		
		go = false;
	}
	public void setDebut(boolean debut) {
		this.debut = debut;
	}

	public void setGo(boolean go) {
		this.go = go;
	}

	public boolean isGo() {
		return go;
	}

}
