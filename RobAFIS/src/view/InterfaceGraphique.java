package view;

import view.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JFrame;

import model.SMMachine;


public class InterfaceGraphique extends JFrame implements KeyListener{
	
	Button btnAvance,btnRecule,btnStop,btnGauche,btnDroite;
	SMMachine macT = new SMMachine();  // mudado
	
	public InterfaceGraphique (){
	
		
		this.setSize(400,300);
		this.addWindowListener(new FrameListener()); 
		buildGUI();
		this.setVisible(true);
	}
	
	
	private void buildGUI(){
		
		Panel mainPanel= new Panel (new BorderLayout());
		ControlListener cl = new ControlListener(); 
		
		Panel centre= new Panel (new GridLayout(4,4)); 

		btnAvance=new Button(Data.cmdAVANCER);
		btnRecule=new Button(Data.cmdRECULER);
		btnStop=new Button(Data.cmdSTOP);
		btnGauche=new Button(Data.cmdGAUCHE);
		btnDroite=new Button(Data.cmdDROITE);
		
		btnAvance.addActionListener(cl);
		btnRecule.addActionListener(cl);
		btnStop.addActionListener(cl);
		btnGauche.addActionListener(cl);
		btnDroite.addActionListener(cl);
		

		centre.add(new Box(0));
		centre.add(btnAvance);
		centre.add(new Box(0));
		centre.add(btnGauche);
		centre.add(btnStop);
		centre.add(btnDroite);
		centre.add(new Box(0));
		centre.add(btnRecule);
		centre.add(new Box(0));

		mainPanel.add(centre,"Center");
		
		this.add(mainPanel);		
	}	

	private class ControlListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String command =e.getActionCommand();
				//eu mudei
			
			switch(command) {
			case "Avancer": macT.avancer();
			break;
			case "Reculer": macT.reculer();
			break;
			case "Arreter": macT.stop();
			break;
			case "Droite": macT.droite();
			break;
			case "Gauche": macT.gauche();
			break;
			}
			
	}
	}
	
	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed: " + e.getKeyChar());
		
		
	}
	public void keyReleased(KeyEvent e) {
			
	}

	public void keyTyped(KeyEvent e) {		
	}
//	
	private class FrameListener extends WindowAdapter
	{
		public void windowClosed(WindowEvent e){
			System.out.println("fin de session");
			System.exit(0);
			}
	} 
	

	

}
