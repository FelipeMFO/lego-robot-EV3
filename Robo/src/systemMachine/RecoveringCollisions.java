package systemMachine;

import controler.Launcher;

public class RecoveringCollisions implements SMStates {
	SMMachine robo;
	
	public RecoveringCollisions(SMMachine newState) {
		robo = newState;
	}

	@Override
	public void executer() {
		Commands.motorStop();
		//beep
		lejos.hardware.Sound.twoBeeps();
		System.out.println("Recovering Colision");		
		//Waiting some seconds
		try { Thread.sleep (3000); } catch (InterruptedException ex) {}
		System.out.println("Touch the back or front sensor to restart");
		char state;
		state = Launcher.getPastStates()[0];
		switch(state){
		case 'f':
			robo.setState(robo.get_movingForward());
			break;
		case 'b':
			robo.setState(robo.get_movingBackward());
			break;
		}
	}
	
				@Override
				public void changeColorBall(char color) {}
			
				@Override
				public void changeColorGround(char color) {}
			
				@Override
				public void changeTouchFront(boolean touch) {
					robo.getState().executer();
				}
			
				@Override
				public void changeTouchBack(boolean touch) {
					robo.getState().executer();
				}

}
