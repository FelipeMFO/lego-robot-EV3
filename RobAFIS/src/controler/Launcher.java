package controler;

public class Launcher {
	
	private static int speed;
	private static char[] balls; //'r' for red, 'b' for blue, 'n' for no ball
	//motores
	//sensores
	
	public Launcher() {	
		balls[0] = 'n';
		balls[1] = 'n';
		balls[2] = 'n';
	
	}
	
	public void changeSpeed(int value) {
		speed = value;	
	}
	public void getBall(char color) { // color being //'r' for red, 'b' for blue
		balls[2] = balls[1];
		balls[1] = balls[0];
		balls[0] = color;
		
	}
	public void putBall() {
		balls[0] = balls[1];
		balls[1] = balls[2];
		balls[2] = 'n';
	}
	
	
	

	public static void Main(String args[]) {
		
	}

}
