package systemMachine;

import controler.Launcher;  
import lejos.hardware.Button;
import lejos.utility.Delay;

	//First State, only for initialization and to set the ball color which will be organized 

@SuppressWarnings("unused")
public class Initial implements SMStates{
	SMMachine robo;
	
	public Initial(SMMachine smMachine) {
		robo = smMachine;
	}

	@Override
	public void changeColorBall(char color) {}

	@Override
	public void changeColorGround(char color) {}

	@Override
	public void changeTouchFront(boolean touch) {}

	@Override
	public void changeTouchBack(boolean touch) {
		Launcher.setTouchingBack(false);
		System.out.println("Right to blue");
		System.out.println("Left to red");
		System.out.println("Then press Enter");
		while(!Button.ENTER.isDown()) {
			if(Button.RIGHT.isDown()) {Launcher.setGoodColor('u'); System.out.println("Blue Ball Set");}
			if(Button.LEFT.isDown()) {Launcher.setGoodColor('r'); System.out.println("Red Ball Set");}
		}
		Launcher.setGettingColorB(false);
		Launcher.setGettingGround(false);
		Launcher.setTouchingBack(false);
		Launcher.setTouchingFront(false);
	    robo.setState(robo.get_movingForward());
		robo.getState().executer(); 
	}

	@Override
	public void executer() {
		System.out.println("Initializing Variables");
		System.out.println("When the light sensors turn blue, press the TouchBack Sensor");
	}
}