package systemMachine;

//5 methods for all the states. The first 4 are the methods that will be triggered in the moment the sensors
//detect a change in the valor being verified, firing a specific action which will end in a transition to another
//state.
//The last method, executer, is the one responsible to execute the function of the state, for example: the action
//of advancing inside the MovingForward State, or the action of turning in the Turning state. This method is called
//in the very moment after the transition to another state, assuring the main action of the state is being executed.


public interface SMStates {
	void changeColorBall(char color);
	void changeColorGround(char color);
	void changeTouchFront(boolean touch);
	void changeTouchBack(boolean touch);
	void executer();
}
