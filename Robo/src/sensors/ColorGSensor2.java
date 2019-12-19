package sensors;

import controler.Sensors;   

//import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import systemMachine.Commands;
import systemMachine.SMMachine;
import controler.Launcher;

@SuppressWarnings("unused")
public class ColorGSensor2 implements Runnable{
	
	EV3ColorSensor colorGround;
	SampleProvider colorGProvider;
	static float[] colorGSamples;
	
	static boolean black_line;
	static boolean green_sticker;
	static boolean red_sticker;
	public static boolean out_of_line;
	boolean LCD_display = false;
	
	public ColorGSensor2(){
		//getting the port written in Sensors.java
		Port p = LocalEV3.get().getPort(Sensors.portas[2]);
		colorGround = new EV3ColorSensor(p);
		colorGProvider = colorGround.getRGBMode();
		colorGSamples = new float[colorGProvider.sampleSize()];
	}
	
					//Calibration
	
	public static void brightness(){		
		//value of R
		float rouge = colorGSamples[0];
		if(rouge <= 0.02){
			rouge = 0;
		}
		String red_1 = Float.valueOf(rouge).toString();
		int end_rouge_string = red_1.length() - 2 ;
		if(end_rouge_string >= 6){
			end_rouge_string = 6;}
		String red_2 = red_1.substring(0, end_rouge_string);
		rouge = Float.parseFloat(red_2);	

		//value of G
		float vert = colorGSamples[1];
		if(vert <= 0.008){
			vert = 0;
		}
		String green_1 = Float.valueOf(vert).toString();
		int end_green_string = green_1.length() - 2 ;
		if(end_green_string >= 6){
			end_green_string = 6;}
		String green_2 = green_1.substring(0, end_green_string);
		vert = Float.parseFloat(green_2);

		//value of B
		float bleu = colorGSamples[2];
		if(bleu <= 0.01){
			bleu = 0;
		}
		String blue_1 = Float.valueOf(bleu).toString();
		int end_blue_string = blue_1.length() - 2 ;
		if(end_blue_string >= 6){
			end_blue_string = 6;}
		String blue_2 = blue_1.substring(0, end_blue_string);
		bleu = Float.parseFloat(blue_2);

//		//Displaying the values on the screen
//		LCD.drawString("R : " + rouge, 0, 6);
//		LCD.drawString("G : " + vert, 0, 4);
//		LCD.drawString("B : " + bleu, 0, 5);
//		Delay.msDelay(200); //toutes les 250, on affiche les RGB du capteur
//		LCD.drawString("               ", 0, 7);

		//Configuration of the cases
		if(rouge >= 0.035 && vert <= 0.02 && bleu <= 0.02){
			black_line = false;
			green_sticker = false;
			red_sticker = true;
			out_of_line = false;
		}
		else if(vert >= 0.04 && bleu <= 0.04 && rouge <= 0.042){
			black_line = false;
			green_sticker = true;
			red_sticker = false;
			out_of_line = false;
		}
		else if(bleu <= 0.039 && vert <= 0.03 && rouge <= 0.0349){
			black_line = true;
			green_sticker = false;
			red_sticker = false;
			out_of_line = false;
		}
		else if((bleu >= 0.15 && vert>= 0.1) ||
				(bleu >= 0.15 && rouge >= 0.1) ||
				(vert>= 0.1 && rouge >= 0.1)) {
			black_line = false;
			green_sticker = false;
			red_sticker = false;
			out_of_line = true;
			
		}
	}
	
	
	
					// Recovery function
	
	public void recLigne() {
		brightness();
		double n = 300;
		System.out.println("p1");
		Launcher.getmL().setSpeed(80);
		Launcher.getmR().setSpeed(80);
		
		while(out_of_line == true){ 

			long cur_time = System.currentTimeMillis();			
			while(System.currentTimeMillis() - cur_time < n && ColorGSensor2.out_of_line == true){

				System.out.println("p2" + cur_time + "  " + System.currentTimeMillis());
				Launcher.getmL().forward();
				Launcher.getmR().backward();
				ColorGSensor2.brightness();
				Delay.msDelay(200);
				
				if(black_line || red_sticker || green_sticker) {break;}
			}

			ColorGSensor2.brightness();
			System.out.println("p3 " + out_of_line);
			cur_time = System.currentTimeMillis();
			
			while(System.currentTimeMillis() - cur_time < n && out_of_line == true ){
				System.out.println("p4");
				Launcher.getmL().backward();
				Launcher.getmR().forward();
				ColorGSensor2.brightness();
				Delay.msDelay(200);
				
				if(black_line || red_sticker || green_sticker) {break;}

			}
			
			ColorGSensor2.brightness();
			System.out.println("p5 " + out_of_line);
			cur_time = System.currentTimeMillis();
			
			while(System.currentTimeMillis() - cur_time < n && out_of_line == true){
				//sens = "droite";
				System.out.println("p6");
				Launcher.getmR().forward();
				Launcher.getmL().backward();
				ColorGSensor2.brightness();
				Delay.msDelay(200);
				
				if(black_line || red_sticker || green_sticker) {break;}

			}
		
			ColorGSensor2.brightness();
			System.out.println("p7 " + out_of_line);
			cur_time = System.currentTimeMillis();
			
			while(System.currentTimeMillis() - cur_time < n && out_of_line == true){
				//sens = "gauche";
				System.out.println("p8");
				Launcher.getmR().backward();
				Launcher.getmL().forward();
				ColorGSensor2.brightness();
				Delay.msDelay(200);

				if(black_line || red_sticker || green_sticker) {break;}
			}
			
			System.out.println("p9");
			ColorGSensor2.brightness();
			
			n=n*2;

			//si jamais n devient trop grand, le robot va simpler tourner sur lui
			//dans le même sens jusqu'à détecter la ligne noire
			if(n>=2000){
				System.out.println("p0");				
				//Delay.msDelay(5000);
				while(out_of_line == true){
					Launcher.getmL().forward();
					Launcher.getmR().backward();
					brightness();
				}
				//sens="";
				Commands.motorStop();
			}
		}
		Commands.motorStop();
		Commands.CmForward();
		System.out.println("pfinal");

	}
	
	

	void get() {
		while(true) {
			if (Launcher.getGettingGround()) {
	//			Delay.msDelay(10);
	//			System.out.println("color ground");			
	//			System.out.println(colorGSamples[0]);
	//			if (Launcher.getPauseThreadColorGround() == true) {
	//				while(Launcher.getPauseThreadColorGround() == true) {}
	//			}
				colorGProvider.fetchSample(colorGSamples, 0);
				brightness();
				if (black_line == true) {Launcher.setColorGround('b');}//System.out.println('b');}
				else if (green_sticker == true) {Launcher.setColorGround('g');}//; System.out.println('g');}
				else if (red_sticker == true) {Launcher.setColorGround('r');}//; System.out.println('r');}
				else if (out_of_line == true && Launcher.toTurn) {Launcher.setColorGround('w');}//System.out.println('w');}	
				else if (out_of_line == true && Launcher.toTurn == false) {recLigne();}
			}
			else {
				System.out.println("Locked Thread CG");
				}		
		}
	}
	
	@Override
	public void run() {
		this.get();
	}

}
