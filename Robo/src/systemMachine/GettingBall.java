package systemMachine;

import controler.Launcher; 
//import lejos.utility.Delay;

public class GettingBall implements SMStates{
	SMMachine robo;
	
	public GettingBall(SMMachine smMachine) {
		robo = smMachine;
	}
	
	@Override
	public void executer() {
		Commands.motorStop();
		Launcher.passState('g');
		System.out.println("getting");	
		Launcher.setTouchingFront(true);
		Commands.CmForward();	
	}		
			@Override
			public void changeColorBall(char color) {}
		
			@Override
			public void changeColorGround(char color) {
				//returning to the axis
				if (color == 'g' || color == 'r') {
					Commands.motorStop();
					Launcher.setGettingColorB(false);
					Launcher.setGettingGround(false);
					Launcher.setTouchingBack(false);
					Launcher.setTouchingFront(false);
					Commands.CmForward(Commands.AngleMove((float) 10.5)); //Adjust 
					Launcher.setTurnToGetOrPut('n');
					robo.setState(robo.get_turning()); 
					robo.getState().executer();
				}
			}
		
			@Override
			public void changeTouchFront(boolean touch) {
				if (Launcher.getHitFront() == true) {
					Launcher.setGettingGround(false);
					Launcher.setTouchingFront(false);
					Commands.motorStop();
					Commands.CmGetBall(140);
					// Getting the ball color
					Launcher.getBall(Launcher.getColorBall());
					// If it would get two balls:
					//				Commands.CmGetBall(120);
					//				// Verify if there is some ball to get the color
					//				if (Launcher.getColorBall() == 'r' || Launcher.getColorBall() == 'b') {
					//					Launcher.getBall(Launcher.getColorBall());
					//				}
					Launcher.setTurnToGetOrPut('n');
			
					//Setting the rotation
					if (SMMachine.getDirection() == 'r') {
						SMMachine.setDirection('l');
						}
					else {SMMachine.setDirection('r');}
							
					//Going Back to rotate
					Commands.motorStop();
					Launcher.setGettingGround(true);
					Launcher.setTouchingFront(true);
					Commands.CmBackward();	
				}
				
				
			}
		
			@Override
			public void changeTouchBack(boolean touch) {
//				//Press it to go to the Wait State
//				robo.setState(robo.get_wait());
//				robo.getState().executer();
			}

}
