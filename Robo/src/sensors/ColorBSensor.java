package sensors;

import controler.Launcher;  
import controler.Sensors;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
//import lejos.utility.Delay;

public class ColorBSensor implements Runnable{
	
	EV3ColorSensor colorBall;
	SampleProvider colorBProvider;
	float[] colorBSamples;
	
	public ColorBSensor(){
		//getting the port written in Sensors.java
		Port p = LocalEV3.get().getPort(Sensors.portas[3]);
		colorBall = new EV3ColorSensor(p);
		colorBProvider = colorBall.getColorIDMode();
		colorBSamples = new float[colorBProvider.sampleSize()];
	}
	
	void get() {		
		while(true) {
			if(Launcher.getGettingColorB()) {
				int i;
				colorBProvider.fetchSample(colorBSamples, 0);
				i = (int) colorBSamples[0];
				switch (i) {
					case 0: Launcher.setColorBall('r');
						break;
					case 2: Launcher.setColorBall('u');
						break;
					default : Launcher.setColorBall('n');
						break;
				}
			}
		}
	}
	//7 -> Black - B      
	//6 -> White - W
	//1 -> Green - G
	//0 -> Red   - R
	//2 -> Blue  - U
	@Override
	public void run() {
		this.get();		
	}

}
