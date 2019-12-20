package robot;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class US_thread implements Runnable{

	Port port_US = LocalEV3.get().getPort("S3"); 
	//private EV3UltrasonicSensor US_sens;
	SampleProvider US_sensor = new EV3UltrasonicSensor(port_US);	
	
	
	
	
//	TouchFSensor tF = new TouchFSensor();
	US us_sensor = new US(US_sensor);
	//Color_pastille pastille = new Color_pastille();
//	Thread toucheF = new Thread(tF);
	Thread sonar = new Thread(us_sensor);
	//Thread past = new Thread(pastille);
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//US_sens.getMode("Distance");
		sonar.run();
		//past.run();
	}
}

