/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.PFont;
import triggers.Position;

import java.awt.Frame;

import controlP5.*;
import controller.InterfaceController;

public class PositionButton extends PIWindow{

	ProgramInterface parent;
	ControlP5 cp5;
	int h,w;
	InterfaceController myController;

	Frame frame;
	
	boolean close;
	
	public PositionButton(){
	}
	
	public PositionButton(ProgramInterface p, Frame f, int width, int height, InterfaceController c){
		frame = f;
		parent = p;
		h = height;
		w = width;
		myController = c;
		//size(w,h);
	}
	
	public void setup() {
//		start(this, w, h, this.frame);
//		size(w,h);
		size(400,300);
		background(124, 186, 230);
		cp5 = new ControlP5(this);
		PFont font = createFont("ariel", 20);
		
		cp5.addButton("Close").setSize(150,50).setPosition(35, 125);
		cp5.getController("Close").getCaptionLabel().setFont(font).setSize(16);
		cp5.addButton("Far").setSize(150,50).setPosition(215, 125);
		cp5.getController("Far").getCaptionLabel().setFont(font).setSize(16);
		
		cp5.addButton("ExitingButton").setLabel("Close").setSize(150,50).setPosition(125, 225);
		cp5.getController("ExitingButton").getCaptionLabel().setFont(font).setSize(16);
	}

	public void draw() {
		textSize(24);
		text("Where is the child standing", 35, 75);
		text("from the computer?", 85, 100);


	}
	
	public void Close(ControlEvent e){
		close = true;
		frame.setVisible(false);
		parent.positionHappened();
	}
	 
	public void Far(ControlEvent e){
		close = false;
		frame.setVisible(false);
		parent.positionHappened();
	}
	
	public void ExitingButton(ControlEvent e){		
		frame.setVisible(false);

	}
	
	public Position getPositionInfo(){
		return new Position(myController, close);
	}

}
