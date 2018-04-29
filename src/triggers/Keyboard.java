/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import controller.InterfaceController;

public class Keyboard implements Trigger{
	
	InterfaceController myController;
	char listenFor;
	
	public Keyboard(char listen, InterfaceController controller){
		myController = controller;
		listenFor = listen;
	}

	public void receiveEvent(char key){
		if (listenFor == key){
			myController.callback(this);
		}
	}
}
