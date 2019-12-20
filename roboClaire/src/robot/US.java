package robot;

import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;

public class US extends AbstractFilter implements Runnable{

	float[] sample;
	private EV3UltrasonicSensor US_sens;
	NXTRegulatedMotor left_motor = Motor.B;
	NXTRegulatedMotor right_motor = Motor.C;
	boolean obstacle = false;
	int seuil_obstacle = 18;
	 static boolean obstacle_detection = false;

	/*
	public US(SampleProvider source) {
		super(source);
		Port port_US = LocalEV3.get().getPort("S3"); 
		US_sensor = new EV3UltrasonicSensor(port_US);	
		sample = new float[sampleSize];	
		US_sensor.getMode("Distance");
	}
	*/
	public void run(){
		init_us_sensor();
		while(true){
			obstacle_detection = detection_obstacle();
		}
	}
	
	public US(SampleProvider source){
		super(source);
		//Port port_US = LocalEV3.get().getPort("S3"); 
		//US_sensor = new EV3UltrasonicSensor(port_US);	
		//US_sensor = US_thread.get().getClass().getResource("US_sensor");
		//US_sensor = US_thread.this.US_sensor;
		sample = new float[sampleSize];	
		//US_sensor.getMode("Distance");	
	}
	
	public void init_us_sensor(){
		//Port port_US = LocalEV3.get().getPort("S3"); 
		//US_sensor = new EV3UltrasonicSensor(port_US);	
		//sample = new float[sampleSize];	
		//US_sensor.getMode("Distance");		
	}
	
	public boolean detection_obstacle(){
		//US_sens.getMode("Distance");
			float distance_mesuree = distance()*100;
			//LCD.clear(4);
			//LCD.drawString(Float.valueOf(distance_mesuree).toString(), 0, 4);

			if(distance_mesuree >= seuil_obstacle){
				obstacle = false;
			}
			else{
				//left_motor.stop(true); //until stop is called
				//right_motor.stop(true);
				//Sound.playTone(2, 250);
				obstacle = true;
			}
			return obstacle;
		
	}
	
	public void close_us_sensor(){
		//US_sensor.close();
	}

	public float distance(){
		super.fetchSample(sample, 0);
		return sample[0]; //retourne une distance en metre
	}

	/*
	public static void main(String[] args){
		new US(US_sensor);
	}
	 */

}
