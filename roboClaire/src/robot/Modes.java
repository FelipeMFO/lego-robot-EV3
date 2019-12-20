package robot;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Modes {

	static Motor_mill moulin = new Motor_mill();
	//	Line_2 ligne = new Line_2();
	Decision positionnement = new Decision();
	Direction_pastille decalage_pastille = new Direction_pastille();
	int nb_balles = 0;

	public Modes(){	}

	public String choix_modes(int mode){
		//LCD.drawString("num_p3:"+positionnement.getPosition(), 0, 4);
		//Delay.msDelay(4000);
		String retour = "";
		if(mode == 0){
			//rien
			//decalage_pastille.direction_pastille("avance");
		}
		if(mode == 1){
			//stock_echange_sortie_mill
			stock_echange_sortie_mill();
			retour = "droite";
		}
		if(mode == 2){
			//stock_sortie_finale_mill
			stock_sortie_finale_mill();
			retour = "droite";
		}
		if(mode == 3){
			//fin de mission
			fin_mission();
		}
		if(mode == 4){
			//fin de mission
			stock_depart();
			retour = "gauche";
		}
		if(mode == 5){
			//fin de mission
			stock_echange_entree();
			retour = "droite";
		}
		if(mode == 6){
			//fin de mission
			stock_echange_sortie();
			retour = "gauche";
		}
		if(mode == 7){
			//fin de mission
			stock_sortie_finale();
			retour = "droite";
		}
		if(mode == 8){
			//fin de mission
			pre_fin_mission();
		}
		if(mode == 9){
			//fin de mission
			pre_fin_mission_false();
			retour = "droite";
		}
		if(mode == 10){
			//fin de mission
			pre_fin_mission_true();
			retour = "gauche";
		}
		if(mode == 11){
			//second passage au stock de depart
			String blabla = stock_depart_2();
			retour = blabla;
		}
		//LCD.drawString("num_p4:"+positionnement.getPosition(), 0, 4);
		//Delay.msDelay(4000);
		return retour;
	}

	public void prehension_balle(){
		LCD.drawString("NB=" + nb_balles, 13, 2);
		LCD.drawString("MODE:prendre    ", 0, 7);
		moulin.motor_mill_prehension();
		Robot.stock_balle("prehension");
		nb_balles++;
		//decalage_pastille.direction_pastille("demi-tour");
	}

	public void depose_balle(boolean echange){
		//alors on peut la déposer
		LCD.drawString("NB=" + nb_balles, 13, 2);
		LCD.drawString("MODE:deposer    ", 0, 7);
		moulin.motor_mill_depose();
		if(echange == true){
			Robot.stock_balle("depose_echange");				
		}
		else{
			Robot.stock_balle("depose");			
		}
		//Delay.msDelay(500);
		nb_balles--;
		//decalage_pastille.direction_pastille("demi-tour");

		/*
		 * moulin.motor_mill_echange();
			Delay.msDelay(500);
		 */
	}
	//mode 1
	public void stock_echange_sortie_mill(){
		decalage_pastille.direction_pastille("avance_stock");
		positionnement.setPosition(3);
		moulin.motor_mill_echange();
		Delay.msDelay(500);
		depose_balle(true);
		if(Robot.stock_balle.get(0) == Robot.stock_balle.get(1)){
			Delay.msDelay(200);
			depose_balle(false);
		}
		decalage_pastille.direction_pastille("recule_stock");
		decalage_pastille.direction_pastille("droite_stock");
		positionnement.setPosition(4);
	}
	//mode 2
	public void stock_sortie_finale_mill(){
		positionnement.setPosition(7);
		decalage_pastille.direction_pastille("avance_stock_final");
		positionnement.setPosition(5);
		moulin.motor_mill_echange();
		Delay.msDelay(500);
		if(Robot.stock_balle.get(0) == Robot.stock_balle.get(1)){
			depose_balle(true);
			Delay.msDelay(200);
			depose_balle(false);
		}
		else{
			depose_balle(true);
		}
		decalage_pastille.direction_pastille("recule_stock");
		decalage_pastille.direction_pastille("demi-tour");
		positionnement.setPosition(7);
		//Sound.twoBeeps();
		//Delay.msDelay(6000);
	}
	//mode 3
	public void fin_mission(){
		LCD.drawString("MODE:fin mission", 0, 7);
		decalage_pastille.direction_pastille("avance");

	}
	//mode 4
	public void stock_depart(){
		positionnement.setPosition(6);
		decalage_pastille.direction_pastille("avance_stock");
		positionnement.setPosition(1);
		prehension_balle();
		Delay.msDelay(500);
		prehension_balle();
		decalage_pastille.direction_pastille("recule_stock");
		decalage_pastille.direction_pastille("gauche_stock");
		positionnement.setPosition(6);
	}//mode 5
	public void stock_echange_entree(){
		positionnement.setPosition(6);
		decalage_pastille.direction_pastille("avance_stock");
		positionnement.setPosition(2);
		prehension_balle();
		Delay.msDelay(500);
		prehension_balle();
		decalage_pastille.direction_pastille("recule_stock");
		decalage_pastille.direction_pastille("droite_stock");
		positionnement.setPosition(6);
	}
	//mode 6
	public void stock_echange_sortie(){
		//positionnement.setPosition(4);
		decalage_pastille.direction_pastille("avance_stock");
		positionnement.setPosition(3);
		depose_balle(false);
		decalage_pastille.direction_pastille("recule_stock");
		decalage_pastille.direction_pastille("gauche_stock");
		positionnement.setPosition(4);
	}
	//mode 7
	public void stock_sortie_finale(){
		positionnement.setPosition(7);
		decalage_pastille.direction_pastille("avance_stock_final");
		positionnement.setPosition(5);
		depose_balle(false);
		decalage_pastille.direction_pastille("recule_stock");
		decalage_pastille.direction_pastille("demi-tour");
		positionnement.setPosition(7);
	}
	//mode 8
	public void pre_fin_mission(){
		decalage_pastille.direction_pastille("avance_stock");
		positionnement.setPosition(2);
		prehension_balle();
		if(Robot.stock_balle.get(0) == 0 && Robot.stock_balle.get(1) == 0){
			Delay.msDelay(500);
			prehension_balle();			
		}
		if(Robot.stock_balle.get(1) == 0 && Robot.stock_balle.get(0)==0){
			positionnement.pre_fin_mission = true;
			positionnement.fin_mission = true;
			//Sound.twoBeeps();
			decalage_pastille.direction_pastille("recule_stock");
			choix_modes(10);
		}
		else{
			//if(balle_presente() != 0){
			positionnement.pre_fin_mission = false;
			decalage_pastille.direction_pastille("recule_stock");
			choix_modes(9);
		}
	}
	//mode 9
	public void pre_fin_mission_false(){
		decalage_pastille.direction_pastille("droite_stock");
		positionnement.setPosition(6);
	}
	//mode 10
	public void pre_fin_mission_true(){
		decalage_pastille.direction_pastille("gauche_stock");
		positionnement.setPosition(6);
	}
	//mode 11
	public String stock_depart_2(){
		//on prend la dernière balle dans le stock d'entree du départ
		String retour = "";
		decalage_pastille.direction_pastille("avance_stock");
		positionnement.setPosition(1);
		prehension_balle();
		decalage_pastille.direction_pastille("recule_stock_debut");
		positionnement.setPosition(6);
		//on fait ensuite demi-tour pour aller verifier l'etat du stock d'change en entrée
		decalage_pastille.direction_pastille("demi-tour");
		Robot.retrouve_ligne("droite");
		positionnement.setPosition(2);
		prehension_balle();
		if(Robot.stock_balle.get(0) == 0 && Robot.stock_balle.get(1) == 0){
			Delay.msDelay(500);
			prehension_balle();			
		}
		decalage_pastille.direction_pastille("recule_stock");
		if(Robot.stock_balle.get(0) == 0 && Robot.stock_balle.get(1) == 0){
			decalage_pastille.direction_pastille("gauche_stock");
			positionnement.setPosition(6);
			positionnement.pre_fin_mission = true;
			positionnement.fin_mission = true;
			retour = "gauche_depart";
		}
		else{
			decalage_pastille.direction_pastille("droite_stock");
			positionnement.setPosition(6);
			positionnement.pre_fin_mission = false;
			retour = "droite";
		}
		return retour;
	}
}
