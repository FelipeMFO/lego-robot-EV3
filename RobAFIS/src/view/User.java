package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User implements Runnable{

	
	
	
	User(){
		
	}
	
	private int saisieInt(Scanner scan){
		
		int val=0 ;
		boolean valid=true;
		do {
			try{
				val=scan.nextInt(); 
				valid=true;
			}
			catch (InputMismatchException except)
			{	valid=false;
				scan.nextLine();
				System.out.println("erreur de saisie - recommencez");
			}
		} while (!valid);
		return val;
	}
		
	public void run() {
		// saisie d'un entier au clavier
		
		Scanner sc = new Scanner(System.in);
		int val;
		 
		while (true){
			
			val=saisieInt(sc);
			
			// TO DO
			// génération d'un événement 
		}
		
		
	}

}
