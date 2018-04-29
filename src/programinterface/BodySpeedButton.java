/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.PFont;
import triggers.Speed;

import java.awt.Frame;

import controlP5.*;
import controller.InterfaceController;

public class BodySpeedButton extends PIWindow{
	ControlP5 cp5;
	ProgramInterface parent;
	InterfaceController myController;
	
	
	int w,h;
	
	Frame frame;
	
	Boolean fast;
	
	public BodySpeedButton(){
	}
	
	public BodySpeedButton(ProgramInterface p, Frame f, int width, int height, InterfaceController c){
		frame = f;
		parent = p;
		w = width;
		h = height;
		myController = c;
	}
	
	public void setup(){
		start(this, w, h, this.frame);
		size(w, h);
		background(124, 186, 230);
		cp5 = new ControlP5(this);
		PFont font = createFont("ariel", 20);
		
		cp5.addButton("Fast").setSize(150, 50).setPosition(50, 115);
		cp5.getController("Fast").getCaptionLabel().setFont(font).setSize(16);
		cp5.addButton("Slow").setSize(150, 50).setPosition(250, 115);
		cp5.getController("Slow").getCaptionLabel().setFont(font).setSize(16);
		
		cp5.addButton("CloseButton").setLabel("Close").setSize(50, 20).setPosition(200, 180);
		cp5.getController("CloseButton").getCaptionLabel().setFont(font).setSize(14);

	}
	
	public void draw(){
		textSize(24);
		text("What is the Child's Speed?", 75, 75);
		
	}
	
	public void Fast(ControlEvent e){
		fast = true;
		frame.setVisible(false);
	}
	
	public void Slow(ControlEvent e){
		fast = false;
		frame.setVisible(false);
	}
	
	public Speed getSpeed(){
		
		//check my controller thing
		return new Speed(myController);
	}
	
	public void CloseButton(ControlEvent e){
		frame.setVisible(false);
	}
}
