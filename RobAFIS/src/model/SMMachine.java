package model;

public class SMMachine {	
	//each state of the state machine
	SMStates avancer;
	SMStates reculer;
	SMStates wait;
	//the actual state, the one that will be changing
	SMStates roboState;
	
	public SMMachine() {
		
		avancer  = new SMAvancer(this);
		reculer = new SMReculer(this);
		wait = new SMWait(this);
		
		//wait, the default state
		roboState = wait;
		
	}
	
	//The setter, receiving the new state
	void setState(SMStates newState) {
		roboState = newState;
	}
	
	//The responses of each command
	public void avancer() {
		roboState.graphique_avancer();
		}
	public void reculer() {
		roboState.graphique_reculer();
		}
	public void droite() {
		roboState.graphique_droite();
		}
	public void gauche() {
		roboState.graphique_gauche();
		}
	public void stop() {
		roboState.graphique_stop();
		}
	public void val1(int val1) {
		roboState.user_val1(val1);
		}
	public void val2(int val2) {
		roboState.user_val2(val2);
		}
	
	//getters to change the states
	public SMStates get_avancer() {return avancer;}
	public SMStates get_reculer() {return reculer;}
	public SMStates get_wait() {return wait;}
	
}
