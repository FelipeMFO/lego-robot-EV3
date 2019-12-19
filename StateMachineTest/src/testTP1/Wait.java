package testTP1;

public class Wait implements States {
	
	//storing the object which will be used to pass in this class
	Machine robo;
	//Constructor: receiving one newState (itself, the this in the Machine class) to set to the object in which will be working
	public Wait (Machine newState) {
		
		robo = newState;
		
	}

	@Override
	public void executer() {
		int i = 1;
		while (i<5) {
			try { Thread.sleep (1000); } catch (InterruptedException ex) {}
			System.out.println("executando wait");
			i = i +1;
		}
		
	}
	@Override
	public void graphique_droite() {
		System.out.println("robot turning right");
	}

	@Override
	public void graphique_gauche() {
		System.out.println("robot turning left");

	}

	@Override
	public void graphique_stop() {
		System.out.println("turning down the robot");
		//Put some close or anything
	}

	@Override
	public void graphique_reculer() {
		System.out.println("changing to reculer");
		robo.setState(robo.get_reculer());

	}

	@Override
	public void graphique_avancer() {
		System.out.println("changing to avancer");
		robo.setState(robo.get_avancer());
		robo.get_avancer().executer();
	}

	@Override
	public void user_val1(int val1) {

		System.out.println("Entering a value does nothing" );

	}

	@Override
	public void user_val2(int val2) {

		System.out.println("Entering a value does nothing");

	}

}