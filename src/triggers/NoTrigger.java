/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import controller.InterfaceController;

public class NoTrigger implements Trigger{
	
	InterfaceController myController;
	
	public NoTrigger(InterfaceController c){
		myController = c;
	}
	
	public void receiveNoTrigEvent(){
		myController.callback(this);
	}

}
