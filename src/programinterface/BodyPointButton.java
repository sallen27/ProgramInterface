/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import java.awt.Frame;

import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controller.InterfaceController;
import processing.core.PFont;
import triggers.BodyPoint;

public class BodyPointButton extends PIWindow {

	ControlP5 cp5;
	ProgramInterface parent;
	InterfaceController myController;
	
	int h, w;
	
	Frame frame;
	String hand;
	
	Button l, r;
	
	public BodyPointButton(){
	}
	
	public BodyPointButton(ProgramInterface p, Frame f, int width, int height, InterfaceController c){
		frame = f;
		parent = p;
		h = height;
		w = width;
		myController = c;
	}
	public void setup() {
		start(this, w, h, this.frame);
		size(w, h);
		background(124, 186, 230);
		cp5 = new ControlP5(this);
		PFont font = createFont("ariel", 20);

		l = cp5.addButton("Left").setLabel("Left Hand").setSize(200,50).setPosition(20, 75);
		cp5.getController("Left").getCaptionLabel().setFont(font).setSize(16);
		r = cp5.addButton("Right").setLabel("Right Hand").setSize(200,50).setPosition(235, 75);
		cp5.getController("Right").getCaptionLabel().setFont(font).setSize(16);
		
		cp5.addButton("ExitButton").setLabel("Close").setSize(150,50).setPosition(160, 150);
		cp5.getController("ExitButton").getCaptionLabel().setFont(font).setSize(16);
	}

	public void draw() {
		textSize(24);
		text("Which hand would you like to use?", 35, 50);
		
	}
	
	public void Left(ControlEvent e){
		hand = "left";
		frame.setVisible(false);
		parent.bodyPointHappened();
	}
	
	public void Right(ControlEvent e){
		hand = "right";
		frame.setVisible(false);
		parent.bodyPointHappened();
	}
	
	public void setHand(String h){
		hand = h;
	}
	
	public void ExitButton(ControlEvent e){
		frame.setVisible(false);
	}
	
	public BodyPoint getBodyPointInfo(){
		return new BodyPoint(myController, hand);
	}
}

