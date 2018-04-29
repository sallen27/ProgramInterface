/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.PFont;
import triggers.Gesture;

import java.awt.Frame;
import java.util.Arrays;
import java.util.List;

import controlP5.*;
import controller.InterfaceController;

public class GestureButton extends PIWindow{

	ControlP5 cp5;
	ProgramInterface parent;
	InterfaceController myController;
	com.leapmotion.leap.Controller controller;
	
	int w, h;
	
	Frame frame;
	
	String gesture;
	ScrollableList sl;
	String selected;
	
	public GestureButton(){
	}
	
	public GestureButton(ProgramInterface p, Frame f, int width, int height, InterfaceController c, com.leapmotion.leap.Controller con){
		frame = f;
		parent = p;
		w = width;
		h = height;
		myController = c;
		controller = con;
	}
	
	public void setup() {
		start(this, w, h, this.frame);
		size(w, h);
		background(124, 186, 230);
		cp5 = new ControlP5(this);
		PFont font = createFont("ariel", 20);

		List<String> gestures = Arrays.asList("slouching","hand biting", "head banging", "sitting", "pacing");
		sl = cp5.addScrollableList("gestureOptions").setLabel("Gesture Options")
			.setPosition(100, 100).setSize(200,100)
			.setBarHeight(50)
			.setItemHeight(50)
			.addItems(gestures)
			.setType(ScrollableList.DROPDOWN);
		cp5.getController("gestureOptions").getCaptionLabel().setFont(font).setSize(16);

		sl.addCallback(new CallbackListener(){

			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					String gest = selected;
					setGesture(gest);
					parent.gestureHappened();

				}
			}
		});

		cp5.addButton("ExitingButton").setLabel("Close").setSize(150,50).setPosition(125, 210);
		cp5.getController("ExitingButton").getCaptionLabel().setFont(font).setSize(16);
		
	}

	public void draw() {
		textSize(24);
		text("What is the Gesture?", 75, 75);
		
	}
	
	public void gestureOptions(int n) {
		  selected = cp5.get(ScrollableList.class, "gestureOptions").getItem(n).get("name").toString();
		  System.out.println("selected: "+ selected);
		  sl.close();
		}
	
	public void setGesture(String g){
		gesture = g;
	}
	
	public Gesture getGestureInfo(){
		return new Gesture(myController, gesture, controller);
	}
	
	public void ExitingButton(ControlEvent e){
		frame.setVisible(false);
	}
}