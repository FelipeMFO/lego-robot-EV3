package view;

public final class Data {
	
	public static final char CodeRECULE ='r', CodeSTOP='s',CodeGAUCHE='g', CodeDROIT='d', CodeAVANCE='a'; // 
	public static final char CodeDISCONNECT='0';
	
	public static final String cmdRECULER ="Reculer", cmdSTOP="Arreter",cmdGAUCHE="Gauche", cmdDROITE="Droite", cmdAVANCER="Avancer"; // 
	public static final String cmdExit="EXIT";

	public static final String cmd1="1";
	public static final String cmd2="2";
	//aqui pra baixo eh a parte da state machine da prof
	public final static String ETAT_WAIT="wait";
	public final static String ETAT_AVANCE="avance";
	public final static String ETAT_RECULE="back";



}
