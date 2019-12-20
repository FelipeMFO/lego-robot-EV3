package robot;

import java.io.File;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Decision {

	Direction_pastille pastille = new Direction_pastille();
	Color_pastille ligne_stock = new Color_pastille();
	boolean fin_mission = false;
	boolean pre_fin_mission = false;

	//	File son = new File("C:\Users\Claire\Documents\Etudes\EMA\2A-Semestre_2-EMA
	//File son = new File(Decision.class.getClass().getResource("/Sounds/" + "game_over"));
	File game_over_sound = new File("game_over.wav");
	int position = 0;

	public Decision(){}

	boolean depart_mission = true;

	public int getPosition() {
		//0 si c'est le départ
		//1 si on est au transtockeur
		//2 si on est au stock partagé d'entrée
		//3 si on est au stock partagé de sortie
		//4 si on est sur la pastille rouge de croisement
		//5 si on est au stock de sortie
		//6 si on est sur la pastille verte centrale
		//7 si on est sur la 2nde pastille rouge
		Sound.beep();
		//Delay.msDelay(50);
		LCD.drawString("num_p?:"+position, 9, 4);
		return position;
	}
	public void setPosition(int position) {
		//0 si c'est le départ
		//1 si on est au transtockeur
		//2 si on est au stock partagé d'entrée
		//3 si on est au stock partagé de sortie
		//4 si on est sur la pastille rouge de croisement
		//5 si on est au stock de sortie
		//6 si on est sur la pastille verte centrale
		//7 si on est sur la 2nde pastille rouge
		//while(getPosition() != position){
			//this.position = position;			
		//}	
		this.position = position;	
		LCD.drawString("num_p!:"+position, 0, 4);
		
	}

	//renvoie l'action à effectuer
	//0 = rien
	//1 = vers stock échange sortie avec échange de balle
	//2 = vers stock sortie final avec échange de balle
	//3 = fin mission
	//4 = vers stock départ
	//5 = vers stock échange entrée
	//6 = vers stock échange sortie
	//7 = vers stock sortie final
	public int prendre_decision(int place, int ball_full, int position, int parametre_p){

		LCD.drawString("num_p*:"+position, 9, 4);
		if(fin_mission == true){
			if(position == 6){
				//Sound.twoBeeps();
				Sound.playSample(game_over_sound, 8);
				pastille.direction_pastille("droite");
				Robot.retrouve_ligne("droite");
				setPosition(0);
			}
			return 3;
		}
		else{
			//LCD.drawString("num_p2:"+getPosition(), 0, 4);
			//Delay.msDelay(4000);
			if(place == 2){ //green sticker
				//on vérifie l'état du stock du robot et s'il y a bien une balle dans le robot alors
				if(ball_full != 0){ // il y a une balle dans le robot
					//on vérifie le dernier emplacement connu
					if(position == 0){ //alors on vient du début
						//ici, le robot est sur un sticker vert, porte 1 balle et vient du départ
						//IMPOSSIBLE => message d'erreur
						//LCD.clear();
						//LCD.drawString("ERREUR !", 5, 4);
						//LCD.drawString("2, true, 0", 5, 5);
						return 0;
					}
					if(position == 3 || position == 5 || position == 7){ //alors on vient du stock d'échange de sortie
						//ici, le robot est sur un sticker vert, porte 1 balle et vient du stock d'échange de sortie
						//IMPOSSIBLE => on est forcé de passer par la 4
					//	LCD.clear();
						//LCD.drawString("ERREUR !", 5, 4);
						//LCD.drawString("2, true, 3", 5, 5);
						return 0;
					}
					if(position == 4 ){//alors on vient de la pastille rouge de croisement
						//ici, le robot est sur un sticker vert, porte 1 balle et vient de la pastille rouge centrale
						//très peu problable
						//on veut tout de même faire demi-tour
						setPosition(6);
						pastille.direction_pastille("retour");
						Robot.retrouve_ligne("droite");
						return 0;
					}
					if(position == 6){//alors on vient de la pastille verte centrale
						//alors on est devant 1 des 2 stocks d'entrée : on cherche à prendre une balle
						//IMPOSSIBLE : on ne peut venir de 6, être sur 1 ou 2 et déjà avoir une balle
						pastille.direction_pastille("avance_erreur");
						return 0;//prendre_balle
					}

				}
				//s'il n'y a pas de balle dans le robot alors 
				else{
					//on vérifie le dernier emplacement connu
					if(position == 0){ //alors on vient du début
						//ici, le robot est sur un sticker vert, ne porte pas de balle et vient du départ
						//on veut donc que le robot tourne sur sa droite
						setPosition(6);
						//LCD.drawString("num_p:6", 0, 4);
						//Delay.msDelay(2000);
						pastille.direction_pastille("droite");
						Robot.retrouve_ligne("droite");
						//Delay.msDelay(2000);
						//ligne_stock.retrouve_ligne("droite");
						//il faut enchaîner ici avec la préhension de la balle et le retour sur la pastille 6
						//ou créer un état qui se chargera de le faire
						return 4;
					}
					if(position == 3 || position == 5 || position == 7){ //alors on vient du stock d'échange de sortie
						//ici, le robot est sur un sticker vert, ne porte pas de balle et vient du stock d'échange de sortie
						//IMPOSSIBLE => on est forcé de passer par la postion 4
						return 0;
					}
					if(position == 4){//alors on vient de la pastille rouge de croisement
						//ici, le robot est sur un sticker vert, ne porte pas de balle et vient de la pastille rouge centrale
						setPosition(6);
						if(depart_mission == true){
							//on va chercher la 3eme balle dans le stock d'entree du debut
							//on veut aller à gauche 
							//pastille.direction_pastille("avance_6");
							pastille.direction_pastille("gauche");
							Robot.retrouve_ligne("gauche");
							//ligne_stock.retrouve_ligne("gauche");
							depart_mission = false;
							return 11;
						}
						else{
							//on veut vérifier que le stock d'échange en entrée est vide
							//on veut aller à droite
							pastille.direction_pastille("droite");
							Robot.retrouve_ligne("droite");
							//ligne_stock.retrouve_ligne("droite");


							//on est pré-fin de mission : tout va dépendre si le stock est vide
							pre_fin_mission = true;

							//---------------------On veut faire un créneau pour tourner à droite sans taper le stock
							//pastille.direction_pastille("creneau_droite");

							//---------------------On veut faire un créneau pour tourner à droite sans taper le stock
							if(pre_fin_mission == false){
								return 5;							
							}
							else{
								return 8;
							}
						}
					}
					if(position == 6){
						pastille.direction_pastille("avance_erreur");
						return 0;
					}
					return 0;				
				}
			}
			if(place == 3){ //red sticker
				if(ball_full != 0){	
					//on vérifie le dernier emplacement connu
					if(position == 0 || position == 1 || position == 2){ //alors on vient du début
						//ici, le robot est sur un sticker rouge, porte 1 balle et vient du départ
						//IMPOSSIBLE => il faut passer par la postion 6
						return 0;
					}
					if(position == 4){
						//alors on vient de la pastille rouge de croisement
						//ici, le robot est sur un sticker rouge, porte 1 balle et vient de la pastille rouge centrale
						//pour savoir si je suis en position 3 ou 7, il faut que je compare la couleur de ma balle
						//avec le paramètre
						setPosition(7);
						int balle_fond = Robot.stock_balle.get(0);
						int balle_devant = Robot.stock_balle.get(1);

						if(parametre_p == 0){
							//on veut les bleues
							if(balle_fond == 0){
								if(balle_devant == 1){
									//la balle de devant est rouge
									//Vers stock echange
									pastille.direction_pastille("demi-tour");
									Robot.retrouve_ligne("droite");
									//ligne_stock.retrouve_ligne("droite");
									return 0;
								}
								else if(balle_devant == 2){
									//la balle de devant est bleue
									//Vers stock sortie
									return 7; //deposer_balle
								}
							}
							else if(balle_fond == 1){
								//la balle du fond est rouge
								//vers stock echange sortie
								pastille.direction_pastille("demi-tour");
								Robot.retrouve_ligne("droite");
								//ligne_stock.retrouve_ligne("droite");
								return 0;
							}
							else if(balle_fond == 2){
								//la balle du fond est bleue
								//vers stock sortie
								return 2;
							}
						}
						else{
							//on veut les rouges
							if(balle_fond == 0){
								if(balle_devant == 1){
									//la balle de devant est rouge
									//Vers stock sortie
									return 7;

								}
								else if(balle_devant == 2){
									//la balle de devant est bleue
									//Vers stock echange
									pastille.direction_pastille("demi-tour");
									Robot.retrouve_ligne("droite");
									//ligne_stock.retrouve_ligne("droite");
									return 0;
								}
							}
							else if(balle_fond == 1){
								//la balle du fond est rouge
								//vers stock sortie
								return 2;
							}
							else if(balle_fond == 2){
								//la balle du fond est bleue
								//vers stock echange sortie
								pastille.direction_pastille("demi-tour");
								Robot.retrouve_ligne("droite");
								//ligne_stock.retrouve_ligne("droite");
								return 0;
							}

						}
					}
					if(position == 6){//alors on vient de la pastille verte centrale
						//depend de la couleur de la balle
						setPosition(4);
						int balle_fond = Robot.stock_balle.get(0);
						int balle_devant = Robot.stock_balle.get(1);

						if(parametre_p == 0){
							//on veut les bleues
							if(balle_fond == 0){
								if(balle_devant == 1){
									//la balle de devant est rouge
									//Vers stock echange
									pastille.direction_pastille("gauche");
									Robot.retrouve_ligne("gauche");
									//ligne_stock.retrouve_ligne("gauche");
									return 6;
								}
								else if(balle_devant == 2){
									//la balle de devant est bleue
									//Vers stock sortie
									setPosition(4);
									pastille.direction_pastille("avance");
									return 0;
								}
							}
							else if(balle_fond == 1){
								//la balle du fond est rouge
								//vers stock echange sortie
								pastille.direction_pastille("gauche");
								Robot.retrouve_ligne("gauche");
								//ligne_stock.retrouve_ligne("gauche");
								return 1;
							}
							else if(balle_fond == 2){
								//la balle du fond est bleue
								//vers stock sortie
								pastille.direction_pastille("avance");
								return 0;
							}
						}
						else{
							//on veut les rouges
							if(balle_fond == 0){
								if(balle_devant == 1){
									//la balle de devant est rouge
									//Vers stock sortie
									pastille.direction_pastille("avance");
									return 0;

								}
								else if(balle_devant == 2){
									//la balle de devant est bleue
									//Vers stock echange
									pastille.direction_pastille("gauche");
									Robot.retrouve_ligne("gauche");
									//ligne_stock.retrouve_ligne("gauche");
									return 6;
								}
							}
							else if(balle_fond == 1){
								//la balle du fond est rouge
								//vers stock sortie
								pastille.direction_pastille("avance");
								return 0;
							}
							else if(balle_fond == 2){
								//la balle du fond est bleue
								//vers stock echange sortie
								pastille.direction_pastille("gauche");
								Robot.retrouve_ligne("gauche");
								//ligne_stock.retrouve_ligne("gauche");
								return 1;
							}
						}
					}
					if(position == 7){//alors on vient de la 2nde pastille rouge
						setPosition(4);
						int balle_fond = Robot.stock_balle.get(0);
						int balle_devant = Robot.stock_balle.get(1);

						if(parametre_p == 0){
							//on veut les bleues
							if(balle_fond == 0){
								if(balle_devant == 1){
									//la balle de devant est rouge
									//Vers stock echange
									pastille.direction_pastille("droite");
									Robot.retrouve_ligne("droite");
									//ligne_stock.retrouve_ligne("droite");
									//setPosition(4);
									return 6;
								}
								else if(balle_devant == 2){
									//la balle de devant est bleue
									//Vers stock sortie
									pastille.direction_pastille("demi-tour");
									Robot.retrouve_ligne("droite");
									//ligne_stock.retrouve_ligne("droite");
									//setPosition(4);
									return 0;
								}
								else if(balle_devant == 0){
									//stock vide : on avance
									pastille.direction_pastille("avance");
									//setPosition(4);
									return 0;
								}
							}
							else if(balle_fond == 1){
								//la balle du fond est rouge
								//vers stock echange sortie
								pastille.direction_pastille("droite");
								Robot.retrouve_ligne("droite");
								//setPosition(4);
								//ligne_stock.retrouve_ligne("droite");
								return 0;
							}
							else if(balle_fond == 2){
								//la balle du fond est bleue
								//vers stock echange sortie
								pastille.direction_pastille("demi-tour");
								Robot.retrouve_ligne("droite");
								//setPosition(4);
								return 1;
							}
						}
						else{
							//on veut les rouges
							if(balle_fond == 0){
								if(balle_devant == 1){
									//la balle de devant est rouge
									pastille.direction_pastille("demi-tour");
									Robot.retrouve_ligne("droite");
									//setPosition(4);
									//ligne_stock.retrouve_ligne("droite");
									return 0;
								}
								else if(balle_devant == 2){
									//la balle de devant est bleue
									//Vers stock echange
									pastille.direction_pastille("droite");
									Robot.retrouve_ligne("droite");
									//setPosition(4);
									//ligne_stock.retrouve_ligne("droite");
									return 6;
								}
							}
							else if(balle_fond == 1){
								//la balle du fond est rouge
								//Vers stock echange
								pastille.direction_pastille("droite");
								Robot.retrouve_ligne("droite");
								//setPosition(4);
								//ligne_stock.retrouve_ligne("droite");
								return 1;
							}
							else if(balle_fond == 2){
								//la balle du fond est bleue
								pastille.direction_pastille("demi-tour");
								Robot.retrouve_ligne("droite");
								//setPosition(4);
								//ligne_stock.retrouve_ligne("droite");
								return 0;
							}
						}
					}
				}
				//s'il n'y a pas de balle dans le robot alors 
				else{
					//on vérifie le dernier emplacement connu
					if(position == 0 || position == 1 || position == 2){ //alors on vient du début
						//ici, le robot est sur un sticker rouge, ne porte pas de balle et vient du départ
						//IMPOSSIBLE => erreur
						//doit passer par la position 6
						return 0;
					}
					if(position == 4){//alors on vient de la pastille rouge centrale
						//alors on est en position 7
						//on fait demi-tour
						setPosition(7);
						pastille.direction_pastille("retour");
						Robot.retrouve_ligne("droite");
						return 0;
					}
					if(position == 6){//alors on vient de la pastille verte centrale
						//alors on est en position 4
						//on fait demi-tour
						setPosition(4);
						pastille.direction_pastille("retour");
						Robot.retrouve_ligne("droite");
						return 0;
					}
					if(position == 7){//alors on vient de la 2nde pastille rouge
						//on n'a pas de balle alors on vient forcément de la position 5
						setPosition(4);
						pastille.direction_pastille("avance");
						return 0;
					}
					return 0;
				}
				return 0;
			}
			else{
				return 0;
			}
		}
	}
}
