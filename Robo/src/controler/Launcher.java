package controler;
import java.beans.PropertyChangeEvent; 
import java.beans.PropertyChangeListener;
import lejos.hardware.sensor.*;
import lejos.hardware.port.*;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;   
import lejos.hardware.motor.NXTRegulatedMotor;
import systemMachine.Commands;
import systemMachine.SMMachine;

@SuppressWarnings("unused")
// implements Runnable
public class Launcher{
	
	//Variable to recover the line in the new algorithm, using it as true it will recover the line
	//using as false it will serve to complete the turning methods
	public static boolean toTurn = true;	
	
			//motors
	private static EV3LargeRegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.A);
	private static EV3LargeRegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.D);
	private static EV3MediumRegulatedMotor mW = new EV3MediumRegulatedMotor(MotorPort.B);
			//variables to set
	private static float speed;
	private static float speedWindmill;
	private static float originAngle;
	//variables to the control logic, State Machine decisions
	private static char turnToGetOrPut;//variable to define what will be the action after turning. 'n'none, 'g'get, 'p'put 
	private static char[] balls = {'n','n'};//the balls in the robot
	private static char[] colorsCrossed = {'n', 'n', 'n'};//those are the colors crossed in the ground by the robot
	private static char[] pastStates = {'n', 'n', 'n','n'};	//those are the past states of the robot, the first one is the actual
	private static char goodColor;		//'r' red or 'u' blue to select which color the robot will sort
	private static int times;			//to count how many times the robot did the trajectory
		//'f' to forward										//0 -> Red   - 'r'						
		//'b' to backward										//1 -> Green - 'g'
		//'t' to turning										//2 -> Blue  - 'u'
		//'g' to getting										//6 -> White - 'w'   
		//'p' to putting										//7 -> Black - 'b'
		//'s' to stock stop										//-1-> None  - 'N'
		//'n' to none											//'N' is when the sensor gets no one color

	//real time variables	
	private static Boolean hitFront;
	private static Boolean hitBack;
	private static char colorBall;
	private static char colorGround;
	//Booleans to lock the sensors actions
	private static Boolean gettingGround;
	private static Boolean gettingColorB;
	private static Boolean touchingFront;
	private static Boolean touchingBack;
	//The State Machine and the sender
	private SMMachine machineP;
	private static java.beans.PropertyChangeSupport sender;

	public Launcher() { //Constructor initializing the values
		System.out.println("launcher");
		gettingGround = true;
		touchingFront = true;
		touchingBack = true;
		gettingColorB = false;
		
		turnToGetOrPut = 'n'; 
		times = 1;
		colorGround = 'b';			
		colorBall = 'n';
		hitFront = false;
		hitBack = false;
		
		setSpeed(90);
		setSpeedWindmill(150);
		originAngle = mW.getPosition();
		
		machineP = new SMMachine();	
		sender = new java.beans.PropertyChangeSupport(this);
		sender.addPropertyChangeListener(machineP); //adding the listener
	}
//--------------------------------------------------------------------------------------	
	
//	public static SMMachine getMachine() {
//		return machineP;
//	}
			//BALLS FUNCTIONS BEGIN
	public static void getBall(char color) { // color being //'r' for red, 'u' for blue
		balls[1] = balls[0];
		balls[0] = color;
	}
	public static void putBall() {
		balls[0] = balls[1];
		balls[1] = 'n';
	}
	public static char[] getBalls() {
		return balls;
	}	
	public static char getGoodColor() {
		return goodColor;
	}

	public static void setGoodColor(char goodColor) {
		Launcher.goodColor = goodColor;
	}
			//BALLS FUNCTIONS END
//--------------------------------------------------------------------------------------
			//PASSEDCOLOR FUNCTIONS BEGIN
	public static void passedColor(char color) {
		colorsCrossed[2] = colorsCrossed[1];
		colorsCrossed[1] = colorsCrossed[0];
		colorsCrossed[0] = color;
	}
	public static char[] getColorsCrossed() {
		return colorsCrossed;
	}
			//PASSEDCOLOR FUNCTIONS END
//--------------------------------------------------------------------------------------	
			//PASTSTATES FUNCTIONS BEGIN
	public static void passState(char state) {
		pastStates[3] = pastStates[2];
		pastStates[2] = pastStates[1];
		pastStates[1] = pastStates[0];
		pastStates[0] = state;
	}
	public static char[] getPastStates() {
		return pastStates;
	}
	public static int getTimes() {
		return times;
	}
	public static void timePlus() {
		Launcher.times = times + 1;
	}
	public static char getTurnToGetOrPut() {
		return turnToGetOrPut;
	}
	public static void setTurnToGetOrPut(char TurnToGetOrPut) {
		Launcher.turnToGetOrPut = TurnToGetOrPut;
	}
			//PASTSTATES FUNCTIONS END
//--------------------------------------------------------------------------------------	
			//BEGIN getters and setters of the real time variables 	
	public static char getColorGround() {return colorGround;}
	public static void setColorGround(char colorGround) {
		if(getColorGround() != colorGround) {
			Launcher.colorGround = colorGround;
			sender.firePropertyChange("colorG", true, false);
			//sender.firePropertyChange("colorG", getColorGround(), colorGround);
		}

		//Launcher.colorGround = colorGround;
	}
	//
	public static Boolean getHitBack() {return hitBack;}
	public static void setHitBack(Boolean hitBack) {
		sender.firePropertyChange("touchB", getHitBack(), hitBack);
		Launcher.hitBack = hitBack;
	}
	//
	public static Boolean getHitFront() {return hitFront;}
	public static void setHitFront(Boolean hitFront) {
		if(getHitFront() != hitFront) {
			Launcher.hitFront = hitFront;
			sender.firePropertyChange("touchF", true, false);
		}
	}
	//
	public static char getColorBall() {return colorBall;}
	public static void setColorBall(char colorBall) {
		if(getColorBall() != colorBall) {
			Launcher.colorBall = colorBall;
			sender.firePropertyChange("colorB", true, false);			
		}
	}
			//END getters and setters of the real time variables
//--------------------------------------------------------------------------------------	
			//BEGIN locks of the sensors
	public static Boolean getGettingGround() {
		return gettingGround;
	}	
	public static void setGettingGround(Boolean gettingGround) {
		Launcher.gettingGround = gettingGround;
	}
	//
	public static Boolean getGettingColorB() {
		return gettingColorB;
	}
	public static void setGettingColorB(Boolean gettingColorB) {
		Launcher.gettingColorB = gettingColorB;
	}
	//
	public static Boolean getTouchingFront() {
		return touchingFront;
	}
	public static void setTouchingFront(Boolean touchingFront) {
		Launcher.touchingFront = touchingFront;
	}
	//
	public static Boolean getTouchingBack() {
		return touchingBack;
	}
	public static void setTouchingBack(Boolean touchingBack) {
		Launcher.touchingBack = touchingBack;
	}

	//END locks of the sensors
//--------------------------------------------------------------------------------------				
						//SPEEDS & ANGLE BEGIN
	public static float getOriginAngle() {
		return originAngle;
	}

	public static void setOriginAngle(float originAngle) {
		Launcher.originAngle = originAngle;
	}
	public static float getSpeed() {
		return speed;
	}
	public static void setSpeed(float speed) {
		Launcher.speed = speed;
	}
	//
	public static float getSpeedWindmill() {
		return speedWindmill;
	}
	public static void setSpeedWindmill(float speedWindmill) {
		Launcher.speedWindmill = speedWindmill;
	}	
						//SPEEDS END
//--------------------------------------------------------------------------------------			
						//BEGIN Motors
	public static EV3LargeRegulatedMotor getmL() {
		return mL;
	}
	public static void setmL(EV3LargeRegulatedMotor mL) {
		Launcher.mL = mL;
	}
	public static EV3LargeRegulatedMotor getmR() {
		return mR;
	}
	public static void setmR(EV3LargeRegulatedMotor mR) {
		Launcher.mR = mR;
	}
	public static EV3MediumRegulatedMotor getmW() {
		return mW;
	}
	public static void setmW(EV3MediumRegulatedMotor mW) {
		Launcher.mW = mW;
	}
						//END Motors
//--------------------------------------------------------------------------------------

}
