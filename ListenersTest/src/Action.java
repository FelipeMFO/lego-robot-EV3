import java.beans.PropertyChangeEvent; 
import java.beans.PropertyChangeListener;


public class Action implements PropertyChangeListener{ //aqui vai ser a maquina lendo da mudanca de variaveis do launcher

	Changes c;
	public Action() {
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
        case "colorB":
        	System.out.println("Trigou colorB");
        	break;
        case "colorG":
        	System.out.println("Trigou colorG");
        	break;
        case "touchB":
        	System.out.println("Trigou touchB");
        	break;
        case "touchF":
        	System.out.println("Trigou touchF");
        	break;
            
    }
		
	}
	




}
