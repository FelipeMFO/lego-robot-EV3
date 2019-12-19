package touch;

import lejos.hardware.Button;


import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.port.Port;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class TouchTest {
	
	EV3TouchSensor touchSensor;
	SampleProvider touchProvider;
	
	float[] touchSamples;
	
	public static void main (String[] args) {
	
		new TouchTest();
	
	}
		
	public TouchTest() {
	
		Port s1 = LocalEV3.get().getPort("S1");			// a porta que tem que botar no sensor
		touchSensor = new EV3TouchSensor(s1);				// comando para associar a porta ao sensor declarado
		touchProvider = touchSensor.getTouchMode();		// sensor detectou e ta passando para o processador de dado
		touchSamples = new float[touchProvider.sampleSize()];  // O provider fornece os numeros de elementos tratados em sua amostra
		
		for(int i = 0; i < 10; i++ ) {
//			if (!Button.ESCAPE.isUp()) {
//				i = 9;
//			}
			touchProvider.fetchSample(touchSamples, 0);
			System.out.println(touchSamples[0]);
			if (touchSamples[0]==1) {
				System.out.println("ok eh igual");
			}
			Delay.msDelay(3000);
			
		}

		touchSensor.close(); 
		
	}
}


