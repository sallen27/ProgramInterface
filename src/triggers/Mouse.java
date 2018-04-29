/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import controller.InterfaceController;

public class Mouse implements Trigger{

	InterfaceController myController;
	boolean isMouseClicked;
	
	public Mouse(InterfaceController m){
		myController = m;
		isMouseClicked = false;
	}
	
	public void receiveMouseEvent(boolean isClicked){
		isMouseClicked = true;
		if (isMouseClicked == isClicked){
			myController.callback(this);
		}
	}
}
