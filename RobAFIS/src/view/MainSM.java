package view;


public class MainSM {
	
	public static void main(String args[]){
	
		
		InterfaceGraphique UI = new InterfaceGraphique();
		
				
		Thread userThread = new Thread(new User());
		userThread.setDaemon(true);
		userThread.start();
				
		SMControler stateMachine = new SMControler();	
		new Thread(stateMachine).start();
		

	}
}
