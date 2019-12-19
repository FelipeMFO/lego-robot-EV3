package color;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ColorExClaire {
	//variable globale
	EV3ColorSensor color_sensor;
	SampleProvider color_provider; //mode RGB
	float[] color_sample;
	
	
	
	public ColorExClaire(){
		String color_detected = new String("");
		Port port_ball_color = LocalEV3.get().getPort("S1"); //get the port of the sensor
		color_sensor = new EV3ColorSensor(port_ball_color);
		color_provider = color_sensor.getRGBMode();
		color_sample = new float[color_provider.sampleSize()]; //on attribue la taille du sample à la taille de l'array
		
		while(Button.ESCAPE.isUp()){
			color_provider.fetchSample(color_sample, 0);
			//value of R
			float rouge = color_sample[0];
			String red_1 = Float.valueOf(rouge).toString();
			int end_rouge_string = red_1.length() - 2 ;
			if(end_rouge_string >= 6){
				end_rouge_string = 6;}
			String red_2 = red_1.substring(0, end_rouge_string);
			rouge = Float.parseFloat(red_2);	
			
			//value of G
			float vert = color_sample[1];
			String green_1 = Float.valueOf(vert).toString();
			int end_green_string = green_1.length() - 2 ;
			if(end_green_string >= 6){
				end_green_string = 6;}
			String green_2 = green_1.substring(0, end_green_string);
			vert = Float.parseFloat(green_2);
			
			//value of B
			float bleu = color_sample[2];
			String blue_1 = Float.valueOf(bleu).toString();
			int end_blue_string = blue_1.length() - 2 ;
			if(end_blue_string >= 6){
				end_blue_string = 6;}
			String blue_2 = blue_1.substring(0, end_blue_string);
			bleu = Float.parseFloat(blue_2);
			
			//affiche les valeurs RGB sur la brique
			LCD.drawString("R : " + rouge, 0, 1);
		    LCD.drawString("G : " + vert, 0, 3);
		    LCD.drawString("B : " + bleu, 0, 5);
			Delay.msDelay(250); //toutes les 250, on affiche les RGB du capteur

			color_detected = "       ";
		    LCD.drawString("Couleur : " + color_detected, 0, 7);
			if(rouge >= 2*vert && rouge >= 2*bleu){
				color_detected = "Rouge !";
			}
			else if(bleu >= 2*vert && bleu >= 2*rouge){
				color_detected = "Bleu !";
			}
			else{
				color_detected = "...";
			}
		    LCD.drawString("Couleur : " + color_detected, 0, 7);
		    
			
		}
		//pour fermer le sample
		color_sensor.close();
	}



	
	/*
	public static void main(String[] args){
		new ColorExClaire();
	}
	*/
}