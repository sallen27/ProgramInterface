/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import java.util.ArrayList;

import SimpleOpenNI.SimpleOpenNI;
import controller.InterfaceController;
import processing.core.PVector;

public class Kinect implements Trigger{
	
	InterfaceController myController;
	private float lastPosition;
	
	public Kinect(InterfaceController c){
		myController = c;

	}
	
	public void receiveKinectEvent(SimpleOpenNI s, ArrayList<Integer> users, PVector pos){
		lastPosition = pos.x;

		for(int user : users){
			s.getCoM(user, pos);
			
			if (Math.abs(pos.x - lastPosition) > 1) {
				lastPosition = pos.x;
				myController.callback(this);
			}
		}
	}

}
