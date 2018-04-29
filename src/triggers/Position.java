/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import java.util.ArrayList;

import SimpleOpenNI.SimpleOpenNI;
import controller.InterfaceController;
import processing.core.PVector;

public class Position implements Trigger{

	InterfaceController myController;

	boolean close;

	public Position(InterfaceController mc, boolean c){
		myController = mc;
		close = c;
	}

	public void receivePositionEvent(SimpleOpenNI s, ArrayList<Integer> users, PVector pos){
		for(int user : users){
			s.getCoM(user,pos);
			//code for close and far
		}
	}

}
