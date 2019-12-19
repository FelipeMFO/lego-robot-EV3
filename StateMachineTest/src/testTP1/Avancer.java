package testTP1;

public class Avancer implements States {
	
	//storing the object which will be used to pass in this class
	Machine robo;
	//Constructor: receiving one newState (itself, the this in the Machine class) to set to the object in which will be working
	public Avancer (Machine newState) {
		
		robo = newState;
		
	}

	@Override
	public void executer() {
		int i = 1;
		while (i<5) {
			try { Thread.sleep (1000); } catch (InterruptedException ex) {}
			robo.setState(robo.get_wait());
			System.out.println("executando avancer");
			i = i +1;
		}
	}
	@Override
	public void graphique_droite() {
		System.out.println("this command do nothing");
	}

	@Override
	public void graphique_gauche() {
		System.out.println("this command do nothing");

	}

	@Override
	public void graphique_stop() {
		System.out.println("Changing to wait");
		robo.setState(robo.get_wait());
		robo.get_wait().executer();

	}

	@Override
	public void graphique_reculer() {
		System.out.println("this command do nothing");

	}

	@Override
	public void graphique_avancer() {
		System.out.println("this command do nothing");

	}

	@Override
	public void user_val1(int val1) {

		System.out.println("Value 1 received:" );
		System.out.println(val1);

	}

	@Override
	public void user_val2(int val2) {

		System.out.println("Value 2 received:" );
		System.out.println(val2);

	}

}
