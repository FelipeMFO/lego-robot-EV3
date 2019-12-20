
public class Changes 
{
	private static int color1;
	private static int color2;
	private static boolean touch1;
	private static boolean touch2;
	Action machine;
	private java.beans.PropertyChangeSupport sender;
	
	public  Changes() {
		color1 = 1;
		color2 = 1;
		touch1 = false;
		touch2 = false;
		sender = new java.beans.PropertyChangeSupport(this);
		machine = new Action();
		sender.addPropertyChangeListener(machine);
	}

	public static int getColor() {
		return color1;
	}

	public void setColor(int color) {
		int temp = getColor();
		Changes.color1 = color;
		sender.firePropertyChange("colorG", temp, color);
	}

	public static int getColor2() {
		return color2;
	}

	public void setColor2(int color2) {
		int temp = getColor2();
		Changes.color2 = color2;
		sender.firePropertyChange("colorB", temp, color2);
	}

	public static boolean isTouch1() {
		return touch1;
	}

	public void setTouch1(boolean touch1) {
		sender.firePropertyChange("touchF", touch1, isTouch1());
		Changes.touch1 = touch1;
	}

	public static boolean isTouch2() {
		return touch2;
	}

	public void setTouch2(boolean touch2) {
		sender.firePropertyChange("touchB", touch2, isTouch2());
		Changes.touch2 = touch2;
		
	}

}
