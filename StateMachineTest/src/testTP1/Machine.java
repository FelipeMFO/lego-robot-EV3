package testTP1;

public class Machine {	
	//each state of the state machine
	States avancer;
	States reculer;
	States wait;
	//the actual state, the one that will be changing
	States roboState;
	
	public Machine() {
		
		avancer  = new Avancer(this);
		reculer = new Reculer(this);
		wait = new Wait(this);
		
		//wait, the default state
		roboState = wait;
		
	}
	
	//The setter, receiving the new state
	void setState(States newState) {
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
	public States get_avancer() {return avancer;}
	public States get_reculer() {return reculer;}
	public States get_wait() {return wait;}
	
}
