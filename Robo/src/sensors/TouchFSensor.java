package sensors;

import controler.Sensors;   
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import controler.Launcher;

public class TouchFSensor implements Runnable{

	EV3TouchSensor touchFront;
	SampleProvider touchFProvider;
	float[] touchFSamples;
	
	public TouchFSensor(){
		touchFront = new EV3TouchSensor(LocalEV3.get().getPort(Sensors.portas[0]));
		touchFProvider = touchFront.getTouchMode();
		touchFSamples = new float[touchFProvider.sampleSize()];  
	}
	
	void get() {
		while(true) {
			if (Launcher.getTouchingFront()){
				Delay.msDelay(1); 
				touchFProvider.fetchSample(touchFSamples, 0);
				if (touchFSamples[0] == 1) {Launcher.setHitFront(true);}
			else{Launcher.setHitFront(false);}
			}
		}
	}
	
	@Override
	public void run() {
		this.get();
	}

}
