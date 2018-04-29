/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.PFont;
import triggers.SoundLevel;

import java.awt.Frame;

import controlP5.*;
import controller.InterfaceController;

public class SoundLevelButton extends PIWindow {
	ControlP5 cp5;
	ProgramInterface parent;
	
	InterfaceController myController;
	
	Frame frame;
	int w,h;
	
	boolean quiet;
	
	
	public SoundLevelButton(){
	}
	
	public SoundLevelButton(ProgramInterface p,Frame f, int width, int height, InterfaceController c){
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

		cp5.addButton("Loud").setSize(150, 50).setPosition(50, 115);
		cp5.getController("Loud").getCaptionLabel().setFont(font).setSize(16);
		cp5.addButton("Quiet").setSize(150, 50).setPosition(250, 115);
		cp5.getController("Quiet").getCaptionLabel().setFont(font).setSize(16);
		
		cp5.addButton("ExitingButton").setLabel("Close").setSize(150,50).setPosition(150, 185);
		cp5.getController("ExitingButton").getCaptionLabel().setFont(font).setSize(16);
	}

	public void draw() {
		textSize(24);
		text("What is the sound level?", 75, 75);
		//OPTIONS IN HERE
		
	}
	
	public void Loud(ControlEvent e){
		quiet = false;
		frame.setVisible(false);
		parent.soundLevelHappened();
	}
	
	public void Quiet(ControlEvent e){
		quiet = true;
		frame.setVisible(false);
		parent.soundLevelHappened();
	}
	
	public void ExitingButton(ControlEvent e){
		frame.setVisible(false);
	}
	
	public SoundLevel getSoundLevelInfo(){
		return new SoundLevel(myController, quiet);
	}
}
