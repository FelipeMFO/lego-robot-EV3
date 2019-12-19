package controler;

import sensors.ColorGSensor2;
import sensors.ColorBSensor;
import sensors.TouchBSensor;
import sensors.TouchFSensor;
import systemMachine.SMMachine;

public class Sensors implements Runnable {
	
	//SMMachine machine;
			//The order of the ports
			//1-> touch front
			//2-> touch back
			//3-> color ground
			//4-> color ball
	public static String[] portas = new String[] {"S1","S3", "S4", "S2" };
	
	ColorGSensor2 cG = new ColorGSensor2();	
	Thread colorG = new Thread(cG);
	ColorBSensor cB = new ColorBSensor();
	Thread colorB = new Thread(cB);
	TouchFSensor tF = new TouchFSensor();
	Thread toucheF = new Thread(tF);
	TouchBSensor tB = new TouchBSensor();
	Thread toucheB = new Thread(tB);
	
	@Override
	public void run() {
		//try { Thread.sleep (5000); } catch (InterruptedException ex) {}
		toucheF.start();
		toucheB.start();
		colorG.start();
		colorB.start();
	}
}
