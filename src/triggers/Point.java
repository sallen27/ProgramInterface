/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;

import controller.InterfaceController;

public class Point implements Trigger{
	
	InterfaceController myController;
	Frame frame;
	private Controller controller;

	float width = 300;
	float leftX = -150;
	float topY = 360;
	float height = 200;
	float rightX = 150;
	float bottomY = 160;


	public Point(InterfaceController icontroller){
		myController = icontroller;
		controller = new Controller();
	}

	public void receivePointEvent(Frame f){
		Finger pointable = f.fingers().frontmost();
		float x = 0f;
		float y = pointable.stabilizedTipPosition().getY();
		if (pointable.isExtended()) {
			// System.out.println(pointable.stabilizedTipPosition());
			x = pointable.stabilizedTipPosition().getX();
		}

		if (x >= leftX && x <= rightX && y <= topY && y >= bottomY) {
			myController.callback(this);

		}
	}

}
