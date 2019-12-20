package robot;


public class Direction_pastille {

	Wheels mouvement = new Wheels();

	public Direction_pastille(){

	}

	public void direction_pastille(String direction){
		if(direction == "gauche"){
			mouvement.move_forward_milsec(2000);
			mouvement.turn_left_milsec(1500);
			//Robot.retrouve_ligne("gauche");
		}
		if(direction == "gauche_stock"){
			//mouvement.turn_left_milsec(2800);
			mouvement.turn_left_milsec(1500);
			//Robot.retrouve_ligne("gauche");
		}
		if(direction == "droite"){
			mouvement.move_forward_milsec(2000);
			mouvement.turn_right_milsec(1500);
			//Robot.retrouve_ligne("droite");
		}
		if(direction == "droite_stock"){
			mouvement.turn_right_milsec(1500);
			//Robot.retrouve_ligne("droite");
		}
		if(direction == "avance"){
			mouvement.move_forward_milsec(2600);
		}
		if(direction == "avance_erreur"){
			mouvement.move_forward_milsec(800);
		}
		if(direction == "avance_stock"){
			mouvement.move_forward_milsec(1200);
		}
		if(direction == "avance_stock_final"){
			mouvement.move_forward_milsec(2200);
		}
		if(direction == "recule_stock"){
			mouvement.move_backward_milsec(1300);
		}
		if(direction == "recule_stock_debut"){
			mouvement.move_backward_milsec(1100);
		}
		if(direction == "retour"){
			//puis on fait demi-tour
			mouvement.turn_right_milsec(4000);
			//Robot.retrouve_ligne("droite");
		}
		if(direction == "demi-tour"){
			//d'abord on recule
			mouvement.move_backward_milsec(400);
			//puis on fait demi-tour
			mouvement.turn_right_milsec(4300);
			//Robot.retrouve_ligne("droite");
		}

	}
}
