package systemMachine;

import controler.Launcher;

public class MovingBackward implements SMStates{
	SMMachine robo;
	
	public MovingBackward(SMMachine smMachine) {
		robo = smMachine;
	}
	
	@Override
	public void executer() {
		Commands.motorStop();
		Launcher.toTurn = false;
		System.out.println("Backward");
		Commands.CmBackward();
		Launcher.setGettingGround(true);
		Launcher.setTouchingBack(true);
	}
		
			@Override
			public void changeColorBall(char color) {}
		
			@Override
			public void changeColorGround(char color) {
				Commands.motorStop();
				if(color == 'g') {
					Launcher.passedColor('g');
				}
				else if(color == 'r') {
					Launcher.passedColor('r');
				}	

		if(Launcher.getColorsCrossed()[0] == 'g') {
			Commands.motorStop();
		}
		
		//NEW 1
		
		if (Launcher.getColorsCrossed()[0] == 'g') {
			Commands.motorStop();
			Commands.CmShutDown();
		}
		
		if(Launcher.getColorGround() == 'b' || Launcher.getColorGround() == 'r') {
			Commands.CmBackward();
		}
				
//		//1		
//				if (Launcher.getColorsCrossed()[0] == 'g' &&
//					Launcher.getBalls()[0] == 'n' &&
//					Launcher.getBalls()[1] == 'n') 
//				{
//					SMMachine.setDirection('l');
//					Launcher.setGettingColorB(false);
//					Launcher.setGettingGround(false);
//					Launcher.setTouchingBack(false);
//					Launcher.setTouchingFront(false);
//					Commands.CmForward(Commands.AngleMove((float) 10)); //Perfect Adjust 
//					robo.setState(robo.get_turning());	
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
//		//6		//TODO A PARTIR DAQUI VER CORRECAO
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
			}
		
			@Override
			public void changeTouchFront(boolean touch) {
				//Press it to go to the Wait State
//				robo.setState(robo.get_wait());
//				robo.getState().executer();
			}
		
			@Override
			public void changeTouchBack(boolean touch) {
				Launcher.setGettingColorB(false);
				Launcher.setGettingGround(false);
				Launcher.setTouchingBack(false);
				Launcher.setTouchingFront(false);
				robo.setState(robo.get_recoveringCollisions());
				robo.getState().executer();
			}

}
