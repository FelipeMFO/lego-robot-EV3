package systemMachine;

import controler.Launcher;
import lejos.utility.Delay;
import sensors.ColorGSensor2;

public class RecoveringShifts implements SMStates {
	SMMachine robo;
	int positionR;
	int positionL;
	
	public RecoveringShifts(SMMachine newState) {	
		robo = newState;
	}
	
	@Override
	public void executer() {
		positionL = Launcher.getmL().getTachoCount();
		positionR = Launcher.getmR().getTachoCount();
		
		Commands.motorStop();
		
		Launcher.setGettingGround(false);
		Delay.msDelay(2000);
		Launcher.setGettingGround(true);

		
		System.out.println("Rec Shift");	
		//Launcher.setGettingGround(true);
		
		while (Launcher.getColorGround() == 'w') {
			System.out.println("o valor e " + Launcher.getColorGround());
			Delay.msDelay(1000);
			if (Launcher.getColorGround() == 'b') {break;}
		}
		
//		System.out.println("antes do ajuste rec");
//		if (SMMachine.getDirection() == 'r') {
//			Commands.TurnOne('l', 40);
//			Delay.msDelay(200);
//		}
//		else {
//			Commands.TurnOne('r', 40);
//			Delay.msDelay(200);
//
//		}
//		System.out.println("depois do ajuste rec");
	}
			@Override
			public void changeColorBall(char color) {}
		
			@Override
			public void changeColorGround(char color) {				
				//Commands.motorStop();
				System.out.println(Launcher.getColorGround());
//				System.out.println("Trigou pra parar");		
//				if ((Launcher.getmL().getTachoCount() - positionL >= 45) || (Launcher.getmR().getTachoCount() - positionR >= 45)) {
//					char state;
//					state = Launcher.getPastStates()[0];
//					switch(state){
//					case 'b':
//						Launcher.setGettingColorB(false);
//						Launcher.setGettingGround(false);
//						Launcher.setTouchingBack(false);
//						Launcher.setTouchingFront(false);
//						robo.setState(robo.get_movingBackward());
//						robo.getState().executer();
//						break;
//					default:
//						Launcher.setGettingColorB(false);
//						Launcher.setGettingGround(false);
//						Launcher.setTouchingBack(false);
//						Launcher.setTouchingFront(false);
//						robo.setState(robo.get_movingForward());
//						robo.getState().executer();
//						break;
//						}	
//				}
//				else {
//					Commands.CmTurn('r', 50, 180);
//					char state;
//					state = Launcher.getPastStates()[0];
//					switch(state){
//					case 'b':
//						Launcher.setGettingColorB(false);
//						Launcher.setGettingGround(false);
//						Launcher.setTouchingBack(false);
//						Launcher.setTouchingFront(false);
//						robo.setState(robo.get_movingBackward());
//						robo.getState().executer();
//						break;
//					default:
//						Launcher.setGettingColorB(false);
//						Launcher.setGettingGround(false);
//						Launcher.setTouchingBack(false);
//						Launcher.setTouchingFront(false);
//						robo.setState(robo.get_movingForward());
//						robo.getState().executer();
//						break;
//						}	
//				}
			}
					
			@Override
			public void changeTouchFront(boolean touch) {}
		
			@Override
			public void changeTouchBack(boolean touch) {}
}
