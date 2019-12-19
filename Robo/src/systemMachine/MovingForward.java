package systemMachine;

import controler.Launcher; 
import lejos.utility.Delay;

@SuppressWarnings("unused")
public class MovingForward implements SMStates{
	SMMachine robo;
	
	public MovingForward(SMMachine smMachine) {
		robo = smMachine;
		
	}
	
	
	@Override
	public void executer() {

		Commands.motorStop();		
		Launcher.toTurn = false;
		System.out.println("Moving Forward");
		Launcher.setGettingGround(true);
		Launcher.setTouchingFront(true);
		Commands.CmForward();	

	}

			@Override
			public void changeColorBall(char color) {}
		
			@Override
			public void changeColorGround(char color) {				
				Launcher.setGettingGround(false);
				//change the values of the ground
				if(color == 'g') {
					Launcher.passedColor('g');
				}
				if(color == 'r') {
					Launcher.passedColor('r');
				}	
			
				//NEW 1
				if (Launcher.getColorsCrossed()[0] == 'g' &&
						Launcher.getBalls()[0] == 'n' &&
						Launcher.getBalls()[1] == 'n') {
					SMMachine.setDirection('r');
					Launcher.setTurnToGetOrPut('g');
					Launcher.setGettingColorB(false);
					Launcher.setGettingGround(false);
					Launcher.setTouchingBack(false);
					Launcher.setTouchingFront(false);
					//Manual adjusts based on observations so the turning will be made
					//considering the rotation's center of the robot in the cross of the two lines
					Commands.CmForward(Commands.AngleMove((float) 14)); //Adjust 
					robo.setState(robo.get_turning());	
					robo.getState().executer();
					
				}
				
				//NEW 2
				if (Launcher.getColorsCrossed()[0] == 'r') {
					Launcher.setGettingColorB(false);
					Launcher.setGettingGround(false);
					Launcher.setTouchingBack(false);
					Launcher.setTouchingFront(false);
					robo.setState(robo.get_movingForward());
					robo.getState().executer();
				}
				
				//NEW 3	
				if (Launcher.getColorsCrossed()[0] == 'r') {
					Launcher.setGettingColorB(false);
					Launcher.setGettingGround(false);
					Launcher.setTouchingBack(false);
					Launcher.setTouchingFront(false);
					robo.setState(robo.get_puttingBall());
					robo.getState().executer();
				}
				
//		//1		
//				if (Launcher.getColorsCrossed()[0] == 'g' &&
//					Launcher.getBalls()[0] == 'n' &&
//					Launcher.getBalls()[1] == 'n') 
//				{
//					SMMachine.setDirection('r');
//					Launcher.setTurnToGetOrPut('g');
//					Launcher.setGettingColorB(false);
//					Launcher.setGettingGround(false);
//					Launcher.setTouchingBack(false);
//					Launcher.setTouchingFront(false);
//					Commands.CmForward(Commands.AngleMove((float) 14)); //TODO Adjust 
//					robo.setState(robo.get_turning());	
//					robo.getState().executer();
//				}
//				
//				
//		//NEW 2
//				if (Launcher.getColorsCrossed()[0] == 'r') {
//					
//					robo.setState(robo.get_movingForward());
//					robo.getState().executer();
//					Launcher.estado = 3;
//					
//					
//				}
//				
//		//NEW 3	
//				if (Launcher.getColorsCrossed()[0] == 'r' && Launcher.estado==3) {
//					robo.setState(robo.get_puttingBall());
//					robo.getState().executer();
//				}
				
//		//2		
//				if (Launcher.getColorsCrossed()[0] == 'g' &&
//					Launcher.getColorsCrossed()[1] == 'g' &&
//					Launcher.getBalls()[0] != 'n' && 
//					Launcher.getBalls()[1] != 'n') 
//				{
//					Launcher.setGettingColorB(false);
//					Launcher.setGettingGround(false);
//					Launcher.setTouchingBack(false);
//					Launcher.setTouchingFront(false);
//					robo.setState(robo.get_movingForward());
//					robo.getState().executer();
//					System.out.println("passo2");
//				}
//		//3		
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'g' &&
//					Launcher.getColorsCrossed()[2] == 'g' &&
//					Launcher.getBalls()[0] != 'n' && 
//					Launcher.getBalls()[1] != 'n' &&
//					Launcher.getGoodColor() == Launcher.getBalls()[0]) 
//					{
//					Launcher.setGettingColorB(false);
//					Launcher.setGettingGround(false);
//					Launcher.setTouchingBack(false);
//					Launcher.setTouchingFront(false);
//					robo.setState(robo.get_movingForward());
//					robo.getState().executer();
//					}
//		//4
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'g' &&
//					Launcher.getBalls()[0] != 'n' && 
//					Launcher.getBalls()[1] != 'n' &&
//					Launcher.getGoodColor() == Launcher.getBalls()[0]) 
//					{
//					Launcher.setGettingColorB(false);
//					Launcher.setGettingGround(false);
//					Launcher.setTouchingBack(false);
//					Launcher.setTouchingFront(false);
//					robo.setState(robo.get_movingForward());
//					robo.getState().executer();
//					}				
//		//5
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'g' &&
//					Launcher.getBalls()[0] != 'n' &&
//					Launcher.getGoodColor() != Launcher.getBalls()[0]) 
//					{
//					Launcher.setGettingColorB(false);
//					Launcher.setGettingGround(false);
//					Launcher.setTouchingBack(false);
//					Launcher.setTouchingFront(false);
//					robo.setState(robo.get_movingBackward());
//					robo.getState().executer();
//					}	
//		//6		/TODO A PARTIR DAQUI VER CORRECAO
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[0] != 'n' &&
//					Launcher.getGoodColor() != Launcher.getBalls()[0] &&
//					Launcher.getPastStates()[1] == 'p' &&
//					Launcher.getPastStates()[2] == 'f' &&
//					Launcher.getPastStates()[3] == 'f') 
//					{
//						robo.setState(robo.get_movingBackward());
//						robo.getState().executer();
//					}			
//		//7		
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[0] != 'n' &&
//					Launcher.getGoodColor() != Launcher.getBalls()[0] &&
//					Launcher.getPastStates()[1] == 'b' &&
//					Launcher.getPastStates()[2] == 'p' &&
//					Launcher.getPastStates()[3] == 'f') 
//					{
//						SMMachine.setDirection('l');
//						robo.setState(robo.get_turning());
//						robo.getState().executer();
//					}	
//		//8
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[0] == 'n' &&
//					Launcher.getBalls()[1] == 'n' &&
//					Launcher.getPastStates()[1] == 'p' &&
//					Launcher.getPastStates()[2] == 't' &&
//					Launcher.getPastStates()[3] == 'b') 
//					{
//						SMMachine.setDirection('r');
//						robo.setState(robo.get_turning());
//						robo.getState().executer();
//						}	
//		//9
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[0] == 'n' &&
//					Launcher.getBalls()[1] == 'n' &&
//					Launcher.getPastStates()[1] == 'p' &&
//					Launcher.getPastStates()[2] == 'b') 
//					{
//						robo.setState(robo.get_movingBackward());
//						robo.getState().executer();
//					}	
//		//10
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'g' &&
//					Launcher.getColorsCrossed()[2] == 'g' &&
//					Launcher.getBalls()[0] != 'n' &&
//					Launcher.getBalls()[1] != 'n' &&
//					Launcher.getGoodColor() != Launcher.getBalls()[0]) 
//					{
//						SMMachine.setDirection('l');
//						robo.setState(robo.get_turning());
//						robo.getState().executer();
//					}					
//		//11
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'g' &&
//					Launcher.getColorsCrossed()[2] == 'g' &&
//					Launcher.getBalls()[0] != 'n' &&
//					Launcher.getGoodColor() == Launcher.getBalls()[0]) 
//					{
//						SMMachine.setDirection('r');
//						robo.setState(robo.get_turning());
//						robo.getState().executer();
//					}			
//		//12
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'g' &&
//					Launcher.getBalls()[0] != 'n' &&
//					Launcher.getGoodColor() == Launcher.getBalls()[0]) 
//					{
//						robo.setState(robo.get_movingForward());
//						robo.getState().executer();
//					}			
//		//13
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[1] != 'n' &&
//					Launcher.getGoodColor() == Launcher.getBalls()[0]) 
//					{
//						robo.setState(robo.get_movingForward());
//						robo.getState().executer();
//					}			
//		//14
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[0] == 'n' &&
//					Launcher.getBalls()[1] == 'n' &&
//					Launcher.getPastStates()[1] == 'p' &&
//					Launcher.getPastStates()[2] == 'f' &&
//					Launcher.getPastStates()[3] == 'f') 
//					{
//						robo.setState(robo.get_movingBackward());
//						robo.getState().executer();
//					}	
//		//15
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[0] == 'n' &&
//					Launcher.getBalls()[1] == 'n' &&
//					Launcher.getPastStates()[1] == 'b' &&
//					Launcher.getPastStates()[2] == 'p' &&
//					Launcher.getPastStates()[3] == 'f') 
//					{
//						robo.setState(robo.get_movingBackward());
//						robo.getState().executer();
//					}			
//		//16
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[0] == 'n' &&
//					Launcher.getBalls()[1] == 'n' &&
//					Launcher.getPastStates()[1] == 'p' &&
//					Launcher.getPastStates()[2] == 'f' &&
//					Launcher.getPastStates()[3] == 'f') 
//					{
//						robo.setState(robo.get_movingBackward());
//						robo.getState().executer();
//					}			
//		//17
//				if (Launcher.getColorsCrossed()[0] == 'r' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'g' &&
//					Launcher.getBalls()[0] == 'n' &&
//					Launcher.getBalls()[1] == 'n' &&
//					Launcher.getPastStates()[1] == 'p' &&
//					Launcher.getPastStates()[2] == 't' &&
//					Launcher.getPastStates()[3] == 'f') 
//					{
//						Launcher.timePlus();
//						robo.setState(robo.get_movingBackward());
//						robo.getState().executer();
//					}					
//		//18
//				if (Launcher.getColorsCrossed()[0] == 'g' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[0] == 'n' &&
//					Launcher.getBalls()[1] == 'n' &&
//					Launcher.getTimes() == 2) 
//					{
//						SMMachine.setDirection('r');
//						robo.setState(robo.get_turning());
//						robo.getState().executer();
//					}			
//		//19
//				if (Launcher.getColorsCrossed()[0] == 'g' &&
//					Launcher.getColorsCrossed()[1] == 'r' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[0] != 'n' &&
//					Launcher.getBalls()[1] != 'n' &&
//					Launcher.getTimes() == 2 &&
//					Launcher.getPastStates()[1] == 'g' &&
//					Launcher.getPastStates()[2] == 't' &&
//					Launcher.getPastStates()[3] == 'b') 
//					{
//						SMMachine.setDirection('l');
//						robo.setState(robo.get_turning());
//						robo.getState().executer();
//					}					
//		//20
//				if (Launcher.getColorsCrossed()[0] == 'g' &&
//					Launcher.getColorsCrossed()[1] == 'g' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[0] != 'n' &&
//					Launcher.getBalls()[1] != 'n' &&
//					Launcher.getTimes() == 2 &&
//					Launcher.getPastStates()[1] == 't' &&
//					Launcher.getPastStates()[2] == 'g' &&
//					Launcher.getPastStates()[3] == 't') 
//					{
//						SMMachine.setDirection('l');
//						robo.setState(robo.get_turning());
//						robo.getState().executer();
//					}		
//		//21
//				if (Launcher.getColorsCrossed()[0] == 'g' &&
//					Launcher.getColorsCrossed()[1] == 'g' &&
//					Launcher.getColorsCrossed()[2] == 'r' &&
//					Launcher.getBalls()[0] != 'n' &&
//					Launcher.getBalls()[1] != 'n' &&
//					Launcher.getTimes() == 2 &&
//					Launcher.getPastStates()[1] == 'g' &&
//					Launcher.getPastStates()[2] == 't' &&
//					Launcher.getPastStates()[3] == 't') 
//					{
//						SMMachine.setDirection('r');
//						robo.setState(robo.get_turning());
//						robo.getState().executer();
//					}					
//				
//				
				
	//				
	//				//	case (ground = green) && [G, n, n] && True
	//				//	go to Turning(right)  		
	//				if (Launcher.getColorsCrossed()[0] == 'g' && 
	//					Launcher.getColorsCrossed()[1] == 'n' &&
	//					Launcher.getColorsCrossed()[2] == 'n' &&
	//					Launcher.getColorGround() == 'g')
	//				{
	//					SMMachine.setDirection('r');
	//					robo.setState(robo.get_turning());	
	//					robo.getState().executer();
	//				}
	//				//	case color (ground = R) && [R, G, G] && BadBall
	//				//	go to Turning(left)
	//				if (Launcher.getColorsCrossed()[0] == 'r' && 
	//					Launcher.getColorsCrossed()[1] == 'g' &&
	//					Launcher.getColorsCrossed()[2] == 'g' &&
	//					Launcher.getColorGround() == 'r'      &&
	//					Launcher.getColorBall() == 'b') //TODO CREATE A GOOD AND BAD COLOR BALL
	//				{
	//					SMMachine.setDirection('l');
	//					robo.setState(robo.get_turning());	
	//					robo.getState().executer();
	//				}			
			}
			
			@Override
			public void changeTouchFront(boolean touch) {
				Launcher.setTouchingFront(false);
				//The case where it goes to the end of the circuit
				if (Launcher.getColorsCrossed()[0] == 'r' && 
					Launcher.getBalls()[0] == Launcher.getGoodColor() &&
					Launcher.getColorsCrossed()[1] == 'r') {
					robo.setState(robo.get_puttingBall());
					robo.getState().executer();
				}
				else {
					robo.setState(robo.get_recoveringCollisions());
					robo.getState().executer();
				}
			}
			
			@Override
			public void changeTouchBack(boolean touch) {
				//Press it to go to the Wait State
//				robo.setState(robo.get_wait());
//				robo.getState().executer();
			}

}
