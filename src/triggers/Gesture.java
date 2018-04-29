/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import java.util.ArrayList;

import com.leapmotion.leap.Controller;

import SimpleOpenNI.SimpleOpenNI;
import controller.InterfaceController;
import processing.core.PVector;

public class Gesture implements Trigger{

	InterfaceController myController;
	String gesture;
	Controller controller;
	
	public Gesture(InterfaceController c, String g, Controller con){
		myController = c;
		gesture = g;
		controller = con;
	}
	
	public void receiveGestureEvent(SimpleOpenNI s, ArrayList<Integer> users, PVector pos){
		// code for picking up gesture
		
		if(gesture.equals("slouching" )){
		//if(gesture.equals("slouching") && picked up gesture == slouching){
			myController.callback(this);
		}
		
		else if (gesture.equals("hand biting")){
		//else if (gesture.equals("hand biting") && picked up gesture == hand biting){
			myController.callback(this);

		}
		
		else if (gesture.equals("head banging")){
		//else if (gesture.equals("head banging") && picked up gesture == head banging){
			myController.callback(this);
			
		}
		
		else if (gesture.equals("sitting")){
		//else if (gesture.equals("sitting") && picked up gesture == sitting){
			myController.callback(this);
			

		}
		
		else if (gesture.equals("pacing")){
		//else if (gesture.equals("pacing") && picked up gesture == pacing){
			myController.callback(this);

		}
	}
}
