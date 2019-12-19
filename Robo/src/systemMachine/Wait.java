package systemMachine;

import controler.Launcher;

public class Wait implements SMStates {
	//storing the object which will be used to pass in this class
	SMMachine robo;
	
	public Wait (SMMachine newState) {	
		robo = newState;
	}

	@Override
	public void executer() {
		Commands.motorStop();
		System.out.println("Waiting");
		System.out.println("Touch the back sensor to restart");
		char state;
		state = Launcher.getPastStates()[0];
		switch(state){
		case 'f':
			robo.setState(robo.get_movingForward());
			break;
		case 'b':
			robo.setState(robo.get_movingBackward());
			break;
		case 't':
			robo.setState(robo.get_turning());
			break;
		case 'g':
			robo.setState(robo.get_gettingBall());
			break;
		case 'p':
			robo.setState(robo.get_puttingBall());
			break;
//		case 's':
//			robo.setState(robo.get_stockStop());
//			break;
		default: //probably it will never be used
			robo.setState(robo.get_initial());
		}
		
	}

			@Override
			public void changeColorBall(char color) {}
		
			@Override
			public void changeColorGround(char color) {}
		
			@Override
			public void changeTouchFront(boolean touch) {}
		
			@Override
			public void changeTouchBack(boolean touch) {
				robo.getState().executer();
			}

}