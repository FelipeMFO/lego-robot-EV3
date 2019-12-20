package model;
//Nao ta aceitando pq nao eh um lejos project
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.port.Port;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Commands{
	
	EV3ColorSensor colorSensor;
	EV3TouchSensor touchSensor;
	
	public void Cmfoward(int speed, int time, int motor1, int motor2, String[] portas) {
		
	}
	public void CmBackward(int speed, int time, int motor1, int motor2, String[] portas) {
		
	}
	public void CmTurn(int speed, int angle, int motor1, int motor2, char direction) { //direction being 'r' for right, 'l' for left
		
	}
	public void CmStop(int motor) {
		
	}
	public void CmShutDown(int motor1, int motor2, int motor3, String[] portas) {
		
	}
	public void CmGetBall(int speed, int angle, int motor, String porta) {
		
	}
	public void CmPutBall(int speed, int angle, int motor, String porta) {
		
	}
	public char CmGetColor() {
		
		char color;
		//logica da deteccao de cores		
		return color;
		
	}
}

