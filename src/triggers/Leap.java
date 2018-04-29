/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import com.leapmotion.leap.*;

import controller.InterfaceController;

public class Leap implements Trigger {

	InterfaceController myController;
	Finger finger;
	Bone bone;
	Frame frame;
	private Controller controller;
	String body;
	int fingerNums;
	boolean inFrame = true;
	
	
	public Leap(InterfaceController icontroller){
		myController = icontroller;
		controller = new Controller();
	}
	
	public void receiveLeapEvent(boolean inFrame){
		if (inFrame = true){
			myController.callback(this);
		}
	}
	
	public void receiveLeapEvent(Frame f){
		Finger frontmost = f.fingers().frontmost();
		float xpos = frontmost.stabilizedTipPosition().getX();
		float ypos = frontmost.stabilizedTipPosition().getY();
		
		if (xpos != 0.0  || ypos != 0.0){
			myController.callback(this);
		}
	}
	
	
	public boolean areFingersInFrame(){
		if (fingerNums > 0){
			return inFrame;
		}
		return !inFrame;
	}
}