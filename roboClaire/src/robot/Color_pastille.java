package robot;

import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Color_pastille{
	//variable globale
	EV3ColorSensor color_sensor_pastille;
	SampleProvider color_provider_pastille; //mode RGB
	float[] color_sample_pastille;
	boolean black_line;
	boolean green_sticker;
	boolean red_sticker;
	boolean LCD_display = true;
	Wheels mouvement = new Wheels();
	


	public Color_pastille(){
		//close_pastille_sensor();
			//color_provider_pastille.fetchSample(color_sample_pastille, 0);
		}
	
	public void init_pastille_sensor(){
		Port port_pastille_color = LocalEV3.get().getPort("S2"); //get the port of the sensor
		color_sensor_pastille = new EV3ColorSensor(port_pastille_color);
		//color_provider_pastille = color_sensor_pastille.getRGBMode();
		//color_sample_pastille = new float[color_provider_pastille.sampleSize()]; //on attribue la taille du sample à la taille de l'array
		
		}
	public void luminosite(String lum){
		if(lum == "faible"){
			//value of R
			float rouge = color_sample_pastille[0];
			if(rouge <= 0.01){
				rouge = 0;
			}
			String red_1 = Float.valueOf(rouge).toString();
			int end_rouge_string = red_1.length() - 2 ; 
			if(end_rouge_string >= 6){
				end_rouge_string = 6;}
			String red_2 = red_1.substring(0, end_rouge_string); // ta reduzindo o float para apenas no maximo 6 casas decimais
			rouge = Float.parseFloat(red_2);	

			//value of G
			float vert = color_sample_pastille[1];
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
			float bleu = color_sample_pastille[2];
			if(bleu <= 0.01){
				bleu = 0;
			}
			String blue_1 = Float.valueOf(bleu).toString();
			int end_blue_string = blue_1.length() - 2 ;
			if(end_blue_string >= 6){
				end_blue_string = 6;}
			String blue_2 = blue_1.substring(0, end_blue_string);
			bleu = Float.parseFloat(blue_2);

			//affiche les valeurs RGB sur la brique
			//LCD.drawString("R : " + rouge, 0, 6);
			//LCD.drawString("G : " + vert, 0, 4);
			//LCD.drawString("B : " + bleu, 0, 5);
			//Delay.msDelay(200); //toutes les 250, on affiche les RGB du capteur
			//LCD.drawString("               ", 0, 7);

		    LCD.drawString("pasti:", 0, 3);
			if(rouge >= 0.012 && rouge <= 0.032 && vert <= 0.01 && bleu <= 0.013){
				if(LCD_display == true){
				    LCD.drawString("rouge", 6, 3);}
				black_line = false;
				green_sticker = false;
				red_sticker = true;
			}
			else if(vert >= 0.0087 && vert <= 0.02 && bleu == 0.0 && rouge == 0.0){
				if(LCD_display == true){
				    LCD.drawString("verte", 6, 3);}
				black_line = false;
				green_sticker = true;
				red_sticker = false;
			}
			else if(bleu <= 0.05 && vert <= 0.04 && rouge <= 0.04){
				if(LCD_display == true){
				    LCD.drawString("ligne", 6, 3);}
				black_line = true;
				green_sticker = false;
				red_sticker = false;
			}
			else{
				if(LCD_display == true){
				    LCD.drawString("?????", 6, 3);}
				black_line = false;
				green_sticker = false;
				red_sticker = false;
			}
			
		}
		if(lum == "moyenne"){
			//value of R
			float rouge = color_sample_pastille[0];
			if(rouge <= 0.01){
				rouge = 0;
			}
			String red_1 = Float.valueOf(rouge).toString();
			int end_rouge_string = red_1.length() - 2 ;
			if(end_rouge_string >= 6){
				end_rouge_string = 6;}
			String red_2 = red_1.substring(0, end_rouge_string);
			rouge = Float.parseFloat(red_2);	

			//value of G
			float vert = color_sample_pastille[1];
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
			float bleu = color_sample_pastille[2];
			if(bleu <= 0.01){
				bleu = 0;
			}
			String blue_1 = Float.valueOf(bleu).toString();
			int end_blue_string = blue_1.length() - 2 ;
			if(end_blue_string >= 6){
				end_blue_string = 6;}
			String blue_2 = blue_1.substring(0, end_blue_string);
			bleu = Float.parseFloat(blue_2);

			//affiche les valeurs RGB sur la brique
			//LCD.drawString("R : " + rouge, 0, 6);
			//LCD.drawString("G : " + vert, 0, 4);
			//LCD.drawString("B : " + bleu, 0, 5);
			//Delay.msDelay(200); //toutes les 250, on affiche les RGB du capteur
			//LCD.drawString("               ", 0, 7);

		    LCD.drawString("pasti:", 0, 3);
			if(rouge >= 0.015 && rouge <= 0.032 && vert <= 0.01 && bleu <= 0.012){
				if(LCD_display == true){
				    LCD.drawString("rouge", 6, 3);}
				black_line = false;
				green_sticker = false;
				red_sticker = true;
			}
			else if(vert >= 0.0097 && vert <= 0.012 && bleu == 0.0 && rouge == 0.0){
				if(LCD_display == true){
				    LCD.drawString("verte", 6, 3);}
				black_line = false;
				green_sticker = true;
				red_sticker = false;
			}
			else if(bleu <= 0.05 && vert <= 0.04 && rouge <= 0.04){
				if(LCD_display == true){
				    LCD.drawString("ligne", 6, 3);}
				black_line = true;
				green_sticker = false;
				red_sticker = false;
			}
			else{
				if(LCD_display == true){
				    LCD.drawString("?????", 6, 3);}
				black_line = false;
				green_sticker = false;
				red_sticker = false;
			}
			
		}if(lum == "b209"){
			//value of R
			float rouge = color_sample_pastille[0];
			if(rouge <= 0.01){
				rouge = 0;
			}
			String red_1 = Float.valueOf(rouge).toString();
			int end_rouge_string = red_1.length() - 2 ;
			if(end_rouge_string >= 6){
				end_rouge_string = 6;}
			String red_2 = red_1.substring(0, end_rouge_string);
			rouge = Float.parseFloat(red_2);	

			//value of G
			float vert = color_sample_pastille[1];
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
			float bleu = color_sample_pastille[2];
			if(bleu <= 0.01){
				bleu = 0;
			}
			String blue_1 = Float.valueOf(bleu).toString();
			int end_blue_string = blue_1.length() - 2 ;
			if(end_blue_string >= 6){
				end_blue_string = 6;}
			String blue_2 = blue_1.substring(0, end_blue_string);
			bleu = Float.parseFloat(blue_2);

			//affiche les valeurs RGB sur la brique
			//LCD.drawString("R : " + rouge, 0, 6);
			//LCD.drawString("G : " + vert, 0, 4);
			//LCD.drawString("B : " + bleu, 0, 5);
			//Delay.msDelay(200); //toutes les 250, on affiche les RGB du capteur
			//LCD.drawString("               ", 0, 7);

		    LCD.drawString("pasti:", 0, 3);
			if(rouge >= 0.015 && rouge <= 0.032 && vert <= 0.01 && bleu <= 0.012){
				if(LCD_display == true){
				    LCD.drawString("rouge", 6, 3);}
				black_line = false;
				green_sticker = false;
				red_sticker = true;
			}
			else if(vert >= 0.0087 && vert <= 0.0119 && bleu == 0.0 && rouge == 0.0){
				if(LCD_display == true){
				    LCD.drawString("verte", 6, 3);}
				black_line = false;
				green_sticker = true;
				red_sticker = false;
			}
			else if(bleu <= 0.05 && vert <= 0.04 && rouge <= 0.04){
				if(LCD_display == true){
				    LCD.drawString("ligne", 6, 3);}
				black_line = true;
				green_sticker = false;
				red_sticker = false;
			}
			else{
				if(LCD_display == true){
				    LCD.drawString("?????", 6, 3);}
				black_line = false;
				green_sticker = false;
				red_sticker = false;
			}
			
		}
		if(lum == "forte"){
			//value of R
			float rouge = color_sample_pastille[0];
			if(rouge <= 0.01){
				rouge = 0;
			}
			String red_1 = Float.valueOf(rouge).toString();
			int end_rouge_string = red_1.length() - 2 ;
			if(end_rouge_string >= 6){
				end_rouge_string = 6;}
			String red_2 = red_1.substring(0, end_rouge_string);
			rouge = Float.parseFloat(red_2);	

			//value of G
			float vert = color_sample_pastille[1];
			if(vert <= 0.01){
				vert = 0;
			}
			String green_1 = Float.valueOf(vert).toString();
			int end_green_string = green_1.length() - 2 ;
			if(end_green_string >= 6){
				end_green_string = 6;}
			String green_2 = green_1.substring(0, end_green_string);
			vert = Float.parseFloat(green_2);

			//value of B
			float bleu = color_sample_pastille[2];
			if(bleu <= 0.01){
				bleu = 0;
			}
			String blue_1 = Float.valueOf(bleu).toString();
			int end_blue_string = blue_1.length() - 2 ;
			if(end_blue_string >= 6){
				end_blue_string = 6;}
			String blue_2 = blue_1.substring(0, end_blue_string);
			bleu = Float.parseFloat(blue_2);

			//affiche les valeurs RGB sur la brique
			//LCD.drawString("R : " + rouge, 0, 6);
			//LCD.drawString("G : " + vert, 0, 4);
			//LCD.drawString("B : " + bleu, 0, 5);
			//Delay.msDelay(50); //toutes les 250, on affiche les RGB du capteur
			//LCD.drawString("               ", 0, 7);

		    LCD.drawString("pasti:", 0, 3);
			if(rouge >= 0.012 && rouge <= 0.02 && vert == 0.0 && bleu == 0.0){
				if(LCD_display == true){
				    LCD.drawString("rouge", 6, 3);}
				black_line = false;
				green_sticker = false;
				red_sticker = true;
			}
			else if(vert >= 0.01 && vert <= 0.02 && bleu == 0.0 && rouge == 0.0){
				if(LCD_display == true){
				    LCD.drawString("verte", 6, 3);}
				black_line = false;
				green_sticker = true;
				red_sticker = false;
			}
			else if(bleu <= 0.05 && vert <= 0.04 && rouge <= 0.04){
				if(LCD_display == true){
				    LCD.drawString("ligne", 6, 3);}
				black_line = true;
				green_sticker = false;
				red_sticker = false;
			}
			else{
				if(LCD_display == true){
				    LCD.drawString("?????", 6, 3);}
				black_line = false;
				green_sticker = false;
				red_sticker = false;
			}
			
		}
	}
	
	public void calcul_pastille(){		
		//Delay.msDelay(100);
		color_provider_pastille = color_sensor_pastille.getRGBMode();
		color_sample_pastille = new float[color_provider_pastille.sampleSize()]; //on attribue la taille du sample à la taille de l'array
		color_provider_pastille.fetchSample(color_sample_pastille, 0);
		//luminosite("faible");
		//luminosite("moyenne");
		luminosite("forte");
		//luminosite("b209");
	}

	public boolean isBlack_line() {
		return black_line;}

	public boolean isGreen_sticker() {
		return green_sticker;}

	public boolean isRed_sticker() {
		return red_sticker;}
	
	public void close_pastille_sensor(){//pour fermer le sample
		
		color_sensor_pastille.close();
		}

	
	public int getNombre_pastilles() {
		LCD.drawString(previous_pastille, 1, 1);
		return nombre_pastilles;
	}

	int nombre_pastilles = 0;
	String previous_pastille = "";
	
	public int place_detection(){
		int place = 0;
		//couleur_pastille.close_pastille_sensor();
		//couleur_pastille.init_pastille_sensor();
		calcul_pastille();
		if(isBlack_line() == true){
			place = 1;
			previous_pastille = "ligne";
		}
		else if(isGreen_sticker() == true){
			place = 2;
			if(previous_pastille != "green"){
				nombre_pastilles++;
			}
			previous_pastille = "green";
		}
		else if(isRed_sticker() == true){
			place = 3;
			if(previous_pastille != "red"){
				nombre_pastilles++;
			}
			previous_pastille = "red";
		}		
		LCD.drawString("nb_p:" + String.valueOf(nombre_pastilles), 12, 3);
		return place;
	}
	
	public void retrouve_ligne(String direction){
		if(direction == "gauche"){
			while(place_detection() != 1){
				mouvement.turn_left();	
			}
			mouvement.turn_left_milsec(100);
			mouvement.stop();
		}
		if(direction == "droite"){
			while(place_detection() != 1){
				mouvement.turn_right();				
			}
			mouvement.turn_right_milsec(100);
			mouvement.stop();
		}
	}
	/*
	public static void main(String[] args){
		Color_pastille fct = new Color_pastille();
		fct.sensor_pastille_init();
		while(Button.ESCAPE.isUp()){
			fct.calcul_pastille();
		}
	}
	*/
/*
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init_pastille_sensor();
		while(true){
			calcul_pastille();
		}
	}
	*/
	
	/*
	@Override
	public void run() {
		this.calcul_pastille();
	}*/

	
}
