/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import controller.InterfaceController;

public class Emotion implements Trigger {
	
	InterfaceController myController;
	String emotion;

	public Emotion(InterfaceController c, String e){
		myController = c;
		emotion = e;
		
	}
	
	public void receiveEmotionEvent(String em){
		if (emotion.equals(em)){
			myController.callback(this);
		}
	}
}
