package robot;



import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class Color_balle{
	//variable globale
	EV3ColorSensor color_sensor_balle;
	SampleProvider color_provider_balle; //mode RGB
	float[] color_sample_balle;
	String color_detected = new String("");



	public String getColor_detected() {
		return color_detected;
	}



	public Color_balle(){}

	public void init_balle_sensor(){
		Port port_balle_color = LocalEV3.get().getPort("S4"); //get the port of the sensor
		color_sensor_balle = new EV3ColorSensor(port_balle_color);
		color_provider_balle = color_sensor_balle.getRGBMode();
		color_sample_balle = new float[color_provider_balle.sampleSize()]; //on attribue la taille du sample à la taille de l'array
		color_provider_balle.fetchSample(color_sample_balle, 0);
	}

	public String calcul_balle(){
		long current_time = System.currentTimeMillis();
		while(System.currentTimeMillis() - current_time < 500){
			color_sample_balle = new float[color_provider_balle.sampleSize()]; //on attribue la taille du sample à la taille de l'array
			color_provider_balle.fetchSample(color_sample_balle, 0);
			//value of R
			float rouge = color_sample_balle[0];
			if(rouge <= 0.01){
				rouge = 0;
			}
			String red_1 = Float.valueOf(rouge).toString();
			//LCD.clear(2);
			//LCD.drawString(red_1, 0, 2);
			int end_rouge_string = red_1.length() - 2 ;
			if(end_rouge_string >= 6){
				end_rouge_string = 6;}
			String red_2 = red_1.substring(0, end_rouge_string);
			rouge = Float.parseFloat(red_2);	

			//value of G
			/*
			float vert = color_sample_balle[1];
			String green_1 = Float.valueOf(vert).toString();
			//LCD.drawString(green_1, 0, 4);
			int end_green_string = green_1.length() - 2 ;
			if(end_green_string >= 6){
				end_green_string = 6;}
			String green_2 = green_1.substring(0, end_green_string);
			vert = Float.parseFloat(green_2);
			*/
			//value of B
			float bleu = color_sample_balle[2];
			if(bleu <= 0.01){
				bleu = 0;
			}
			String blue_1 = Float.valueOf(bleu).toString();
			//LCD.clear(6);
			//LCD.drawString(blue_1, 0, 6);
			int end_blue_string = blue_1.length() - 2 ;
			if(end_blue_string >= 6){
				end_blue_string = 6;}
			String blue_2 = blue_1.substring(0, end_blue_string);
			bleu = Float.parseFloat(blue_2);

			//affiche les valeurs RGB sur la brique
			//LCD.clear(1);
			//LCD.drawString("R : " + rouge, 0, 1);
			//LCD.drawString("G : " + vert, 0, 3);
			//LCD.clear(5);
			//LCD.drawString("B : " + bleu, 0, 5);
			//Delay.msDelay(500); //toutes les 250, on affiche les RGB du capteur
			if(rouge >= 3*bleu && rouge >= 0.001){
				color_detected = "rouge";
			}
			else if(bleu >= 3*rouge && bleu >= 0.001){
				color_detected = "bleu";
			}
			//else if(rouge <= 0.1 && bleu <= 0.1){
			//	color_detected = "!!!";
			//}
			else{
				color_detected = "...";
			}
			//LCD.clear(7);
			//LCD.drawString(color_detected,6,7);
		}
		return color_detected;
	}



	public void close_balle_sensor(){
		//pour fermer le sample
		color_sensor_balle.close();
	}


	public static void main(String[] args){
		Color_balle balle = new Color_balle();
		balle.init_balle_sensor();
		while(Button.ENTER.isUp()){
			balle.calcul_balle();
		}
		balle.close_balle_sensor();
	}


	/*
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.calcul_balle();
	}
	 */
}


