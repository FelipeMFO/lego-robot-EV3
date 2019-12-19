package systemMachine;

import controler.Launcher;

public class PuttingBall implements SMStates{
	SMMachine robo;
	
	public PuttingBall(SMMachine smMachine) {
		robo = smMachine;
	}
	
	@Override
	public void executer() {
		Commands.motorStop();
		Launcher.passState('p');
		System.out.println("putting");	
		Launcher.setTouchingFront(true);
		Commands.CmForward();	
	}

			@Override
			public void changeColorBall(char color) {}
		
			@Override
			public void changeColorGround(char color) {
				if (color == 'g' || color == 'r') {
					Commands.motorStop();
					Launcher.setGettingColorB(false);
					Launcher.setGettingGround(false);
					Launcher.setTouchingBack(false);
					Launcher.setTouchingFront(false);
					robo.setState(robo.get_turning()); 
					robo.getState().executer();			
				}
				if (color == 'w') {
					Commands.motorStop();
					Launcher.setGettingColorB(false);
					Launcher.setGettingGround(false);
					Launcher.setTouchingBack(false);
					Launcher.setTouchingFront(false);
					robo.setState(robo.get_recoveringShifts()); 
					robo.getState().executer();
				}
			}
		
			@Override
			public void changeTouchFront(boolean touch) {
				Commands.motorStop();
				Launcher.setGettingGround(false);
				Launcher.setTouchingFront(false);
				// If it would put two balls:
//				if (Launcher.getBalls()[0] == Launcher.getBalls()[1]) {
//					Commands.CmPutBall(120);
//					//Changing the logical variable of the stocks in the Launcher
//					//Didn't change before to use as the condition for the if
//					Launcher.putBall();
//				}
				Launcher.putBall();
				Commands.CmPutBall(130);
				Launcher.setTurnToGetOrPut('n');
		
				
				Launcher.setGettingColorB(false);
				Launcher.setGettingGround(false);
				Launcher.setTouchingBack(false);
				Launcher.setTouchingFront(false);
				robo.setState(robo.get_movingBackward());
				robo.getState().executer();
				
//				if (Launcher.getPastStates()[1] == 't') {
//					Launcher.setGettingGround(true);
//					Commands.CmBackward();	
//				}
//				else {
//				Launcher.setGettingColorB(false);
//				Launcher.setGettingGround(false);
//				Launcher.setTouchingBack(false);
//				Launcher.setTouchingFront(false);
//				robo.setState(robo.get_movingBackward());
//				robo.getState().executer();
//				}
			}
		
			@Override
			public void changeTouchBack(boolean touch) {
				//Press it to go to the Wait State
//				robo.setState(robo.get_wait());
//				robo.getState().executer();
			}

}
