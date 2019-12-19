package controler;
//TODO SENSORS ACTIONS, AND THE CHOSE OF THE STATES OF THE SYSTEM MACHINE
//TODO SO DO THE CHANGE, IF THE SENSOR SEND DIRECTLY THE STRING, OR IT WILL JUST REFRESH THE DATA
//TO THE LAUNCHER SEND THE COMMANDS SPECIFIED IN THE INTERFACE TO THE STATE MACHINE
//Resumo, ou o robo ele vai detectar as coisas e nos proprios sensores ja pensamos a logica para jogar qual
//sera o proximo estado da SM e o Launcher com a funcao de troca ja lanca direto e a state machine
//so vai praticamente executar as coisas e ser trocada quando os sensores mandare
//ou (opcao mais trabalhosa) os sensores vao ficar atualizando as variaveis do Launcher, a System machine
//vai ter varios whiles pra tentar captar os casos de variacao de variavel para a tomada de decisao.

//import lejos.utility.Delay; 
//import systemMachine.Commands;
//import systemMachine.SMMachine;

//TODO OS EVENTOS TRIGADOS PELOS SENSORES
public class Init {
	
	public static void main(String args[]) {
		
		Starter launch = new Starter();
		Thread M = new Thread(launch);
		M.start();
		Sensors sens = new Sensors();
		Thread S = new Thread(sens);	
		S.start();


	}
}
