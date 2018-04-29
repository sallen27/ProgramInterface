/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import java.util.ArrayList;

import SimpleOpenNI.SimpleOpenNI;
import controller.InterfaceController;
import processing.core.PVector;

public class BodyPoint implements Trigger{

	InterfaceController myController;

	String hand;

	public BodyPoint(InterfaceController c, String h){
		myController = c;
		hand = h;
	}

	public void receiveBodyPointEvent(SimpleOpenNI s, ArrayList<Integer> users, PVector pos){
		for(int user : users){
			s.getCoM(user,pos);
			if(hand.equals("right")){
				float rhand = s.getJointPositionSkeleton(user, SimpleOpenNI.SKEL_RIGHT_HAND, pos);
				float ftipRight = s.getJointPositionSkeleton(user, SimpleOpenNI.SKEL_RIGHT_FINGERTIP, pos);
				if(ftipRight > pos.x){
					myController.callback(this);
				}
			}

			else {
				PVector leftHand = new PVector();
				float lhand = s.getJointPositionSkeleton(user, SimpleOpenNI.SKEL_LEFT_HAND, leftHand);
				float ftipLeft = s.getJointPositionSkeleton(user, SimpleOpenNI.SKEL_LEFT_FINGERTIP, leftHand);
				if(ftipLeft > 0){
					myController.callback(this);
				}
			}

		}

	}

}
