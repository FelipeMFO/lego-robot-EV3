package robot;

import java.io.File;
import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Robot {


	//	TouchFSensor tF = new TouchFSensor();
	//	Thread toucheF = new Thread(tF);

	static Wheels mouvement = new Wheels();
	Direction_pastille decision_pastille = new Direction_pastille();
	Decision decision_circuit = new Decision();
	static Color_pastille couleur_pastille = new Color_pastille();
	static Color_balle couleur_balle = new Color_balle();
	Modes mode = new Modes();
	Parametre_p parametre_p = new Parametre_p();

	static File red_sound = new File("red.wav");
	static File blue_sound = new File("blue.wav");
	static ArrayList<Integer> stock_balle = new ArrayList<Integer>(2);
	boolean on_sticker;
	int place;
	boolean black_Line_2 = couleur_pastille.isBlack_line(); 	//true : ligne noire détectée
	boolean red_sticker = couleur_pastille.isRed_sticker();
	boolean green_sticker = couleur_pastille.isGreen_sticker();
	double n=0;
	boolean init = true;
	//int nombre_pastilles = 0;
	//String previous_pastille = "";
	String previous_previous_pastille = "";
	long current_time_green = 0;
	long current_time_red = 0;
	String direction = "gauche";
	//int parametre_p = 0; //0 => on veut les bleues
	int decision;
	static boolean fin_mission = false;
	boolean fin_mission_inter = false;

	public Robot() throws InterruptedException{
		couleur_pastille.init_pastille_sensor();
		couleur_balle.init_balle_sensor();
		stock_balle.add(0);
		stock_balle.add(0);
		//US_thread prout = new US_thread();
		//Thread caca = new Thread(prout);
		//caca.start();
		//caca.run();
	}



	//fonction pour vérifier si le robot transporte déjà une balle ou non
	public static int balle_presente(){
		//balle.sensor_balle_init();
		//balle.run();
		int balle_presente = 0;
		String ball_color = couleur_balle.calcul_balle();
		//couleur_balle.calcul_balle();
		//String ball_color = couleur_balle.getColor_detected();
		LCD.drawString("Balle:" + ball_color, 0, 2);
		if(ball_color == "..."){
			balle_presente = 0;
		}		
		if(ball_color == "rouge"){
			balle_presente = 1;
			Sound.playSample(red_sound, 8);
		}	
		if(ball_color == "bleu"){
			balle_presente = 2;
			Sound.playSample(blue_sound, 8);
		}		
		return balle_presente;
	}


	public static void retrouve_ligne(String sens){
		if(sens == "droite"){
			int couleur = couleur_pastille.place_detection();
			long curent_time = System.currentTimeMillis();
			while(System.currentTimeMillis()-curent_time <= 500){
				mouvement.turn_right();
			}
			while(couleur == 0){
				//mouvement.turn_right();
				couleur = couleur_pastille.place_detection();
			}
			mouvement.stop();
			Delay.msDelay(50);
			mouvement.turn_right_milsec(170);	
			//Delay.msDelay(2000);		
		}
		if(sens == "gauche"){
			int couleur = couleur_pastille.place_detection();
			long curent_time = System.currentTimeMillis();
			while(System.currentTimeMillis()-curent_time <= 500){
				mouvement.turn_left();
			}
			while(couleur == 0){
				mouvement.turn_left();
				couleur = couleur_pastille.place_detection();
			}
			mouvement.stop();
			Delay.msDelay(50);
			mouvement.turn_left_milsec(370);	

		}
		if(sens == "gauche_depart"){
			int couleur = couleur_pastille.place_detection();
			long curent_time = System.currentTimeMillis();
			while(System.currentTimeMillis()-curent_time <= 500){
				mouvement.turn_left();
			}
			while(couleur == 0){
				mouvement.turn_left();
				couleur = couleur_pastille.place_detection();
			}
			mouvement.stop();
			Delay.msDelay(50);
			mouvement.turn_left_milsec(370);	

		}
	}

	public void correction_ligne(){
		while(couleur_pastille.place_detection() == 0){ 
			long cur_time = System.currentTimeMillis();			
			while(System.currentTimeMillis() - cur_time < n && couleur_pastille.place_detection() == 0){
				//sens = "gauche";
				//LCD.drawString("ok1", 5, 6);
				Wheels.left_motor.forward();
				Wheels.right_motor.backward();
				couleur_pastille.place_detection();//on vérifie la couleur de la ligne tout le temps de la boucle
			}
			couleur_pastille.place_detection();//on vérifie la couleur de la ligne tout le temps de la boucle

			//LCD.drawString("ok2", 5, 6);
			cur_time = System.currentTimeMillis();
			while(System.currentTimeMillis() - cur_time < n && couleur_pastille.place_detection() == 0 ){
				//sens = "droite";
				//LCD.drawString("ok3", 5, 6);
				Wheels.left_motor.backward();
				Wheels.right_motor.forward();
				couleur_pastille.place_detection();//on vérifie la couleur de la ligne tout le temps de la boucle
			}

			couleur_pastille.place_detection();//on vérifie la couleur de la ligne tout le temps de la boucle
			//LCD.drawString("ok5", 5, 6);

			cur_time = System.currentTimeMillis();
			while(System.currentTimeMillis() - cur_time < n && couleur_pastille.place_detection() == 0){
				//sens = "droite";
				//LCD.drawString("ok6", 5, 6);
				Wheels.right_motor.forward();
				Wheels.left_motor.backward();
				couleur_pastille.place_detection();//on vérifie la couleur de la ligne tout le temps de la boucle
			}

			couleur_pastille.place_detection();//on vérifie la couleur de la ligne tout le temps de la boucle
			//LCD.drawString("ok7", 5, 6);
			cur_time = System.currentTimeMillis();
			while(System.currentTimeMillis() - cur_time < n && couleur_pastille.place_detection() == 0){
				//sens = "gauche";
				//LCD.drawString("ok8", 5, 6);
				Wheels.right_motor.backward();
				Wheels.left_motor.forward();
				couleur_pastille.place_detection();//on vérifie la couleur de la ligne tout le temps de la boucle
			}

			couleur_pastille.place_detection();//on vérifie la couleur de la ligne tout le temps de la boucle
			//LCD.drawString("ok9", 5, 6);
			n=n*2;

			//si jamais n devient trop grand, le robot va simpler tourner sur lui
			//dans le même sens jusqu'à détecter la ligne noire
			if(n>=2000){
				//LCD.drawString("ok0", 5, 6);
				//Delay.msDelay(5000);
				while(couleur_pastille.place_detection() == 0){
					Wheels.left_motor.forward();
					Wheels.right_motor.backward();
					couleur_pastille.place_detection();
				}
				//sens="";
				mouvement.stop();
				couleur_pastille.place_detection();
			}
		}

	}




	int p = 2;
	public void competition() throws InterruptedException {
		while(p == 2){
			int para_2 = parametre_p.get_p();
			if(para_2 != 2){
				LCD.clear(2);
				LCD.clear(3);
				LCD.clear(4);	
				p=para_2;				
			}				
		}
		LCD.drawString("p: " + p, 0, 0);
		//on est sur une ligne noire
		long previous_time = System.currentTimeMillis();
		long curent_time = System.currentTimeMillis();
		while(couleur_pastille.place_detection() == 1 && Button.ENTER.isUp() && US.obstacle_detection==false){ //&& 	
			mouvement.move_forward();
			if(decision_circuit.fin_mission == true	&&	curent_time-previous_time >= 4000){
				mouvement.stop();
				Sound.twoBeeps();
				fin_mission_inter = true;
				break;
			}
			curent_time = System.currentTimeMillis();
			n=150; //on réinitialise n pour la prochaine perte de ligne
			couleur_pastille.place_detection();//on vérifie la couleur de la ligne tout le temps de la boucle
		}
		mouvement.stop();
		while(US.obstacle_detection==true){
			Sound.playTone(2, 250);
			mouvement.stop();
		}
		if(fin_mission_inter == true){
			fin_mission = true;
		}
		//caca.wait();
		mouvement.stop();

		//on n'est pas sur une ligne noire, pas sur une pastille (ourge ou verte)
		//String sens = "";

		correction_ligne();

		//-------------------DECISION PASTILLE - START -------------------------
		int place = couleur_pastille.place_detection();
		if(place == 2 || place == 3){ //pastille verte ou rouge
			//caca.wait();
			//Delay.msDelay(100);
			int balle = stock_balle.get(1);
			//LCD.drawString("num_p1:"+decision_circuit.getPosition(), 0, 4);
			//Delay.msDelay(4000);
			int position = decision_circuit.getPosition();
			//position = decision_circuit.position;
			decision = decision_circuit.prendre_decision(place, balle, position, p);
			LCD.drawInt(decision, 0, 5);
			//Delay.msDelay(2000);
			/*
			if(decision == 4||decision == 8||decision == 11||decision == 5||decision == 6){
				couleur_pastille.close_pastille_sensor();
				couleur_balle.init_balle_sensor();
			}
			 */
			String sens = mode.choix_modes(decision);	
			//LCD.drawString("num_p5:"+decision_circuit.getPosition(), 0, 4);
			//Delay.msDelay(4000);
			/*
			if(decision == 4||decision == 8||decision == 11||decision == 5||decision == 6){
				couleur_balle.close_balle_sensor();
				couleur_pastille.init_pastille_sensor();
			}
			 */
			/*
			if(decision == 8 || decision == 11){
				// ??????
				if(stock_balle.get(1) == 0 && stock_balle.get(0)==0){
					decision_circuit.fin_mission = true;
					//Sound.twoBeeps();
					mode.choix_modes(10);
				}
				else{
					//if(balle_presente() != 0){
					decision_circuit.pre_fin_mission = false;
					mode.choix_modes(9);
				}
			}
*/
			retrouve_ligne(sens);
		}
		//-------------------DECISION PASTILLE - END -------------------------

		//caca.notifyAll();
		//couleur_pastille.close_pastille_sensor();
	}


	public Color_pastille getCouleur_pastille() {
		return couleur_pastille;
	}

	public static boolean stock_balle(String mode){
		//0 = vide / 1 = rouge / 2 = bleue
		//index 0 = balle au fond		//index 1 = balle prete à sortir
		//boolean renvoyé : true si erreur
		boolean erreur = false;
		Delay.msDelay(200); //pour attendre que la balle soit bien devant le capteur
		if(mode == "prehension"){
			int couleur = balle_presente();
			stock_balle.set(0, stock_balle.get(1));
			stock_balle.set(1,couleur);			
			if(stock_balle.get(1)==0 && stock_balle.get(0)!=0){
				int inter = stock_balle.get(0);
				stock_balle.set(0, stock_balle.get(1));
				stock_balle.set(1, inter);
			}
		}
		if(mode == "depose"){
			stock_balle.set(1,stock_balle.get(0));
			stock_balle.set(0,0);
		}
		if(mode == "depose_echange"){
			stock_balle.set(0,0);
		}
		LCD.drawString("stock:  /", 0, 1);
		if(stock_balle.get(0) == 0){
			LCD.drawString("O", 7, 1);			
		}
		else if(stock_balle.get(0) == 1){
			LCD.drawString("R", 7, 1);			
		}
		else if (stock_balle.get(0) == 2){
			LCD.drawString("B", 7, 1);			
		}
		if(stock_balle.get(1) == 0){
			LCD.drawString("O", 9, 1);			
		}
		else if(stock_balle.get(1) == 1){
			LCD.drawString("R", 9, 1);			
		}
		else if (stock_balle.get(1) == 2){
			LCD.drawString("B", 9, 1);			
		}
		return erreur;
	}





	public static void main(String[] args) throws InterruptedException{
		Robot fct = new Robot();
		//US_thread prout = new US_thread();
		//Thread caca = new Thread(prout);
		US_thread prout = new US_thread();
		Thread caca = new Thread(prout);
		caca.start();
		while(Button.ENTER.isUp()	&&	fin_mission == false){
			try {
				fct.competition();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//caca.interrupt();
				e.printStackTrace();
			}
		}
		couleur_pastille.close_pastille_sensor();
		couleur_balle.close_balle_sensor();
		//caca.interrupt();
	}
}
