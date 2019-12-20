package robot;



import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class Parametre_p {
	boolean loop_begin = true;
	static int p = 2;
	static boolean second_param = false;
	public Parametre_p(){
	}
	
	public int get_p(){
		if(loop_begin){
			LCD.drawString("Selectionnez P", 0, 2);
			LCD.drawString("P = 0", 2, 3);
			LCD.drawString("P = 1", 2, 4);
			LCD.drawString(">", 1, 3);
			loop_begin = false;			
		}
		
		if(Button.DOWN.isDown() && Button.UP.isUp() && second_param == false){
			LCD.drawString(" ", 1, 3);
			LCD.drawString(">", 1, 4);
			second_param = true;
			//p=2
		}
		if(Button.UP.isDown() && Button.DOWN.isUp() && second_param == true){
			LCD.drawString(">", 1, 3);
			LCD.drawString(" ", 1, 4);
			second_param = false;
			//p=1
		}
		if(second_param == true && Button.RIGHT.isDown()){
			p = 1;
		}
		if(second_param == false && Button.RIGHT.isDown()){
			p = 0;
		}
		return p;
	}
	
	
	public static void main(String[] args){
		while(Button.ESCAPE.isUp()){
			Parametre_p fct = new Parametre_p();
			int prout = 2;
			while(prout == 2){
				prout = fct.get_p();
			}
			LCD.drawString("parametre : " + Integer.toString(prout),1,7);
		//TimeUnit.SECONDS.sleep(3);
		}
	}
	
}
