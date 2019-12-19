package systemMachine;
import java.beans.PropertyChangeEvent;  
import java.beans.PropertyChangeListener;
//Como eh uma maquina de estados autonoma, os comandos e logicas devem estar todos concentrados  
//nos constutores de cada estado 
import controler.Launcher;  
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;
import systemMachine.Commands;

import sensors.ColorGSensor2;


@SuppressWarnings("unused")
public class SMMachine implements PropertyChangeListener{	
	
	//char to know if it will turn left or right
	private static char direction = 'r'; //default	
	public static char getDirection() {return direction;}
	public static void setDirection(char direction) {SMMachine.direction = direction;}

	//Each state of the state machine. All of them will be a different class, however they all will have the same constructors and the only same
//attribute, one SMMachine. In this case will be the robot. And each one of those classes will be a state because they implement the methods of
//the SMState interface. First of all they will be declared as attributes of the class SMMachine, being considered as a SMState, and inside the
//constructor they will be instanced, each one in its respective class. Having its methods from the interface overridden, for configure each
//command. Each state will have 5 methods, 4 for each action which shall be done in case the sensors fire a event, and the fifth will be the 
//executer. This one will says which actions the state must do, it will be called in the transition, in the moment after the set.state by the
//command robo.getState().executer(). Robot will be the attribute (of type SMMachine) inside each class that is a state.

	SMStates initial;		 // initialing the robot, verification of the variables and sending moving forward
	SMStates movingForward;
	SMStates movingBackward;
	SMStates turning;
	SMStates gettingBall;
	SMStates puttingBall; 	
	SMStates recoveringCollisions; 		//commands to recover a certain collision
	SMStates recoveringShifts;			//commands to recover the shifts of the black line
	//A pause state, to active it, press the touch back sensor in any of the states in which the robot is doing something
	//except in the movingBackward, in this state to change to wait press the touch front sensor
	SMStates wait; 
	
	
	//the actual state, the one that will be changing, it will be instanced in the constructor as being the first state
	SMStates roboState;
	public SMMachine() {
		//Here all the states are being instanced inside their respective classes, and already receiving this state machine in the constructor
//to instantiate the SMMachine attribute in each constructor. It will be useful because, each command that will switch the state or execute
//the executer, inside each state, will be using this attribute instantiated with this SMMachine. In another words, it will be always making
//this SMMachine do the methods of the actual state(thanks to the robo.getState().executer()), and this state will be the ones 
//instantiated down here.

		initial = new Initial(this);
		movingForward = new MovingForward(this);
		movingBackward = new MovingBackward(this);
		turning = new Turning(this);
		gettingBall = new GettingBall(this);
		puttingBall = new PuttingBall(this); 
		recoveringCollisions = new RecoveringCollisions(this);
		recoveringShifts = new RecoveringShifts(this);
		wait = new Wait(this);
		
		//initial, the default state
		roboState = initial;	
		roboState.executer();

	}
	
	
	//Getter and setter of the actual state
	public void setState(SMStates newState) {
		roboState = newState;
	}
	public SMStates getState() {
		return roboState;
	}
	
	//They will be triggered by the events, and they will trigger each SMState method (the ones of the sensors)
	public void changeColorBall(char color) {
		roboState.changeColorBall(color);
	}
	public void changeColorGround(char color){
		roboState.changeColorGround(color);
	}
	public void changeTouchFront(boolean touch) {
		roboState.changeTouchFront(touch);
	}
	public void changeTouchBack(boolean touch) {
		roboState.changeTouchBack(touch);
	}

	//getters to change the states
	public SMStates get_initial() {return initial;}
	public SMStates get_movingForward() {return movingForward;}
	public SMStates get_movingBackward() {return movingBackward;}
	public SMStates get_turning() {return turning;}
	public SMStates get_gettingBall() {return gettingBall;}
	public SMStates get_puttingBall() {return puttingBall;}
	public SMStates get_recoveringCollisions() {return recoveringCollisions;}
	public SMStates get_recoveringShifts() {return recoveringShifts;}
	public SMStates get_wait() {return wait;}

	//Here the actions of the events
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
        case "colorB":
        	System.out.println("Trigou colorB");
        	changeColorBall(Launcher.getColorBall());       	
        	break;
        case "colorG":
        	System.out.println("Trigou colorG"); 
        	changeColorGround(Launcher.getColorGround());        	
        	break;
        case "touchB":
        	System.out.println("Trigou touchB");
        	changeTouchBack(Launcher.getHitBack());       	
        	break;
        case "touchF":
        	System.out.println("Trigou touchF");
        	changeTouchFront(Launcher.getHitFront());     
        	break;		
        }
	}
}
