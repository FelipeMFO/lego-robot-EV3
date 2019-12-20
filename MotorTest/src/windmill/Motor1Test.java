package windmill;

import lejos.hardware.motor.*;

import lejos.utility.*;

public class Motor1Test {
	
	public static void gira() {
		Motor.A.rotate(100, true);
		Motor.D.rotate(100);
		
	}
	
	public static void vai() {
		Motor.A.forward();
		Motor.D.forward();
		
	}
	
	public static void main(String[] args) {
		
		int i;
		//GETTING
//		Motor.B.setSpeed(120);
//		Motor.B.backward();
//		i = Motor.B.getLimitAngle();
//		System.out.println(i);
//		Delay.msDelay(1300);
//		Motor.B.stop();
//		Motor.B.setSpeed(40);
//		Motor.B.forward();
//		Delay.msDelay(3700);
//		Motor.B.stop();
		
		//PUTTING 1
//		Motor.B.setSpeed(50);
//		Motor.B.forward();
//		Delay.msDelay(1000);
//		Motor.B.stop();

		//A PORRA DO BACKWARD E FORWARD SO ANDAM SE TIVER DELAY DEPOIS
		//
//		System.out.println("t1");
//		Motor.A.setSpeed(90);
//		Motor.D.setSpeed(90);
//		Motor.D.backward();
//		Motor.A.forward();
//		System.out.println("t2");
//		Motor.D.stop();
//		Motor.A.stop();
//		
		

		
		System.out.println("t1");
		Motor.A.setSpeed(90);
		Motor.D.setSpeed(90);
//		Motor.D.rotate(90);
//		Motor.A.rotate(90);
		gira();
		System.out.println("depois do gira1 entrando no forward");
		vai();
		System.out.println("depois do vai");
//		gira();
//		System.out.println("depois do gira2 e indo pro stop");
		while(true) {		System.out.println("girando");}
//		Motor.D.stop();
//		Motor.A.stop();

	}

	private static void turn() {
		// TODO Auto-generated method stub
		
	}

}
