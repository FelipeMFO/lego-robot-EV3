package systemMachine;
import controler.Launcher;   
import lejos.utility.Delay;

public class Commands{
	// ----------------Begin Auxiliary-------------------------

	public static int AngleMove(float distance) {
		//This function calculates the angle needed for a certain 
		//distance movement (in centimeters)
		float r = (float) 2.75;
		float pi = (float) Math.PI;
		int ang;		
		ang = (int) (180*distance/(pi*r));		
		return ang;		
	}
	public static void motorStop() { //stop all the motors
		Launcher.getmL().setSpeed(0);
		Launcher.getmR().setSpeed(0);
		Launcher.getmW().setSpeed(0);
		Launcher.getmR().stop();
		Launcher.getmL().stop();		
		Launcher.getmW().stop();	
	}
	// ----------------End Auxiliary-------------------------

	// ----------------Begin Moves-------------------------
	public static void CmForward() {
		 //Getting the left motor and setting its speed
		Launcher.getmL().setSpeed(Launcher.getSpeed());
		 //Getting the right motor and changing its speed
		Launcher.getmR().setSpeed(Launcher.getSpeed());
		//Commands to the two motors move		
		Launcher.getmL().backward();
		Launcher.getmR().backward();
	}
	public static void CmForward(int angle) {		
		Launcher.getmL().setSpeed(Launcher.getSpeed());
		Launcher.getmR().setSpeed(Launcher.getSpeed());
		Launcher.getmL().rotate(-angle, true);
		Launcher.getmR().rotate(-angle);
	}
	
	public static void CmBackward() {
		Launcher.getmL().setSpeed(Launcher.getSpeed());
		Launcher.getmR().setSpeed(Launcher.getSpeed());
		Launcher.getmL().forward();
		Launcher.getmR().forward();
	}
	public static void CmBackward(int angle) {
		Launcher.getmL().setSpeed(Launcher.getSpeed());
		Launcher.getmR().setSpeed(Launcher.getSpeed());		
		Launcher.getmL().rotate(angle, true);
		Launcher.getmR().rotate(angle);
	}	
	// ----------------End Moves-------------------------	
	
	// ----------------Begin Turns-------------------------
	public static void CmTurn(char direction,int speed) { //direction being 'r' for right, 'l' for left
		Launcher.getmL().setSpeed(speed);
		Launcher.getmR().setSpeed(speed);	
		if(direction == 'r') {
		Launcher.getmL().backward();
		Launcher.getmR().forward();
		}
		if(direction == 'l') {
		Launcher.getmL().forward();
		Launcher.getmR().backward();
		}
	}
	public static void CmTurn(char direction,int speed,  int angle) { //direction being 'r' for right, 'l' for left
		Launcher.getmL().setSpeed(speed);
		Launcher.getmR().setSpeed(speed);	
		if(direction == 'r') {
		Launcher.getmL().rotate(-angle, true);
		Launcher.getmR().rotate(angle);
		}
		if(direction == 'l') {
		Launcher.getmL().rotate(angle, true);
		Launcher.getmR().rotate(-angle);
		}
	}
	
	public static void TurnOne(char wheel, int speed) {
		if (wheel == 'r') {
			Launcher.getmR().forward();
		}
		if (wheel == 'l') {
			Launcher.getmL().forward();
		}	
	}
	// ----------------End Turns-------------------------

	// ----------------Begin Windmill-------------------------
	public static void CmWindmillDefault(int origin_angle) {
		Launcher.getmW().setSpeed(90);
		Launcher.getmW().rotateTo(origin_angle, true);
		System.out.println("Default");
	}
	public static void CmGetBall(int angle) {
		Launcher.getmW().setSpeed(Launcher.getSpeedWindmill());
		Launcher.getmW().rotate(-angle);
		Launcher.getmW().setSpeed(Launcher.getSpeedWindmill());
		Delay.msDelay(1000);
		Launcher.getmW().rotate(angle);	
		System.out.println("getBall");
	}
	public static void CmPutBall(int angle) {
		Launcher.getmW().setSpeed(150);
		Launcher.getmW().rotate(angle);
		Delay.msDelay(1000);
		Launcher.getmW().setSpeed(Launcher.getSpeedWindmill());
		Launcher.getmW().rotate(-angle);	
		System.out.println("putBall");
		//motorStop();
	}
	public static void CmRotateMW(int angle) {
		Launcher.getmW().setSpeed(Launcher.getSpeedWindmill());
		Launcher.getmW().rotate(-angle);
		System.out.println("rotateMW");
	}
	// ----------------End Windmill-------------------------

	public static void CmShutDown() {
		Launcher.getmR().close();
		Launcher.getmL().close();		
		Launcher.getmW().close();	
	}
		
}

