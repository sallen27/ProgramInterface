/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import java.util.ArrayList;

import SimpleOpenNI.SimpleOpenNI;
import controller.InterfaceController;
import processing.core.PVector;

public class Speed implements Trigger{
	
	InterfaceController myController;

	
	public Speed(InterfaceController c){
		myController = c;

	}
	
	public void receiveSpeedEvent(SimpleOpenNI s, ArrayList<Integer> users, PVector pos){
		
		for(int user : users){
			s.getCoM(user,pos);
			
			//capture running 
		}
			

		
	}

}
