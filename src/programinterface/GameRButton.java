/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.PFont;
import responses.Games;

import java.awt.Frame;

import controlP5.*;

public class GameRButton extends PIWindow {

	ControlP5 cp5;
	ProgramInterface parent;
	
	int h, w;
	
	Frame frame;
	
	String tool;
	
	public GameRButton(){
	}
	
	public GameRButton(ProgramInterface p, Frame f, int width, int height){
		frame = f;
		parent = p;
		h = height;
		w = width;
	}
	
	public void setup() {
		start(this, w, h, this.frame);
		size(w, h);
		background(124, 186, 230);
		cp5 = new ControlP5(this);
		PFont font = createFont("ariel", 20);

		cp5.addButton("Paintbrush").setSize(200,50).setPosition(20, 100);
		cp5.getController("Paintbrush").getCaptionLabel().setFont(font).setSize(16);
		cp5.addButton("Instrument").setLabel("Virtual Instrument").setSize(200,50).setPosition(235, 100);
		cp5.getController("Instrument").getCaptionLabel().setFont(font).setSize(16);
		
		cp5.addButton("ExitButton").setLabel("Close").setSize(150,50).setPosition(160, 175);
		cp5.getController("ExitButton").getCaptionLabel().setFont(font).setSize(16);
	}

	public void draw() {
		textSize(24);
		text("Which app would you like to play?", 35, 50);
		//COME BACK TO THIS
		
	}
	
	public void Paintbrush(ControlEvent e){
		tool = "paintbrush";
		frame.setVisible(false);
		parent.gamesHappened();
	}
	
	public void Instrument(ControlEvent e){
		tool = "instrument";
		frame.setVisible(false);
		parent.gamesHappened();
	}
	
	public void ExitButton(ControlEvent e){
		frame.setVisible(false);
	}
	
	public Games getGame(){
		return new Games(tool);
	}
}
