package systemMachine;

import controler.Launcher;   
import lejos.utility.Delay;

public class Turning implements SMStates {
	SMMachine robo;

	public Turning(SMMachine smMachine) {
		robo = smMachine;
	}
	@Override
	public void executer() {

		Commands.motorStop();
		Launcher.passState('t');
		System.out.println("Turning");
		// Turning
		
		Commands.CmTurn(SMMachine.getDirection(), 60);
		Delay.msDelay(1500);
		Commands.motorStop();
		Launcher.setGettingGround(true);
		Commands.CmTurn(SMMachine.getDirection(), 60);


	}

			@Override
			public void changeColorBall(char color) {}
		
			@Override
			public void changeColorGround(char color) {
				if(color == 'b') {
					Commands.motorStop();					
					Launcher.setGettingColorB(false);
					Launcher.setGettingGround(false);
					Launcher.setTouchingBack(false);
					Launcher.setTouchingFront(false);
					//inversion to adjust
					if (SMMachine.getDirection() == 'l') {
						SMMachine.setDirection('r');
						}
					else {
						SMMachine.setDirection('l');
						}					
					
					if (SMMachine.getDirection() == 'l') {
						Commands.CmTurn(SMMachine.getDirection(), 50,11); //ADJUST
						}
					else {
						Commands.CmTurn(SMMachine.getDirection(), 50,20);
						}
					
					if (SMMachine.getDirection() == 'l') {
						SMMachine.setDirection('r');
						}
					else {
						SMMachine.setDirection('l');
						}
					//end of inversion to adjust
					
					char i = Launcher.getTurnToGetOrPut();
					switch (i) {
					case 'n':
						robo.setState(robo.get_movingForward());
						robo.getState().executer();	
						break;
					case 'g':
						robo.setState(robo.get_gettingBall());
						robo.getState().executer();
						break;
					case 'p':
						robo.setState(robo.get_puttingBall());
						robo.getState().executer();	
						break;
					}

				}

			}
		
			@Override
			public void changeTouchFront(boolean touch) {

			}
		
			@Override
			public void changeTouchBack(boolean touch) {
				//Press it to go to the Wait State
//				robo.setState(robo.get_wait());
//				robo.getState().executer();
			}
}
