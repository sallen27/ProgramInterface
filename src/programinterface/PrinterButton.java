/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.PApplet;
import processing.core.PFont;
import responses.Printer;

import java.awt.Frame;

import controlP5.*;
import javafx.util.Callback;

public class PrinterButton extends PIWindow {
	ControlP5 cp5;
	ProgramInterface parent;

	String message = "";
	Textfield text;
	int h, w;
	Frame frame;
	Bang OK;
	
	public PrinterButton(){
	}
	
	public PrinterButton(ProgramInterface p, Frame f, int width, int height){
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
		
		text = cp5.addTextfield("text")
		    .setPosition(110, 75)
		      .setSize(120, 30)
		          .setFocus(true)
		            .setColor(color(255, 255, 255))
		              ;
		
		cp5.addButton("ExitButton").setLabel("Close").setSize(70,30).setPosition(300, 160);
		cp5.getController("ExitButton").getCaptionLabel().setFont(font).setSize(12);
		
		cp5.addButton("DoneButton").setLabel("Done").setSize(85,40).setPosition(150, 150);
		cp5.getController("DoneButton").getCaptionLabel().setFont(font).setSize(16);
		
		
		OK = cp5.addBang("Click").setPosition(235, 75).setSize(30, 30);
		
		OK.addCallback(new CallbackListener(){
			String textBox;
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					textBox = cp5.get(Textfield.class, "text").getText();
					setMessage(textBox);
					parent.printerHappened(theEvent);
					
				}
			}
			
		});
	}

	public void draw() {
		textSize(24);
		text("What would you like to say?", 35, 50);
	}
	
	
	public Printer getPrinter(){
		return new Printer(message);
	}
	
	public void setMessage(String m){
		message = m;
	}
	
	public void ExitButton(ControlEvent e){
		frame.setVisible(false);
	}
	
	public void DoneButton(ControlEvent e){
		frame.setVisible(false);
	}
}
