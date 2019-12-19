package sensors;

import controler.Sensors;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import controler.Launcher;

public class TouchBSensor implements Runnable{

	EV3TouchSensor touchBack;
	SampleProvider touchBProvider;
	float[] touchBSamples;
	
	public TouchBSensor(){
		//getting the port written in Sensors.java
		Port p = LocalEV3.get().getPort(Sensors.portas[1]);
		touchBack = new EV3TouchSensor(p);
		touchBProvider = touchBack.getTouchMode();
		touchBSamples = new float[touchBProvider.sampleSize()];  
	}
	
	void get() {
		if (Launcher.getTouchingBack()) {
			while(true) {
				Delay.msDelay(100);
				touchBProvider.fetchSample(touchBSamples, 0);
				if (touchBSamples[0] == 1) {
					Launcher.setHitBack(true);
				}
				else{
					Launcher.setHitBack(false);
				}
			}
		}
	}

	@Override
	public void run() {
		this.get();		
	}

}
