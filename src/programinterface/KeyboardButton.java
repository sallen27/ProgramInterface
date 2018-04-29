/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import java.awt.Frame;

import controlP5.Bang;
import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.Textfield;
import controller.InterfaceController;
import processing.core.PFont;
import responses.Printer;
import triggers.Keyboard;

public class KeyboardButton extends PIWindow{
	
	ControlP5 cp5;
	ProgramInterface parent;
	InterfaceController myController;
	
	int w,h;
	
	Frame frame;
	
	char message = ' ';
	Textfield text;
	Bang OK;
	
	public KeyboardButton(){
	}
	
	public KeyboardButton(ProgramInterface p, Frame f, int width, int height, InterfaceController c){
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
		
		text = cp5.addTextfield("text")
			    .setPosition(75, 100)
			      .setSize(120, 30)
			          .setFocus(true)
			            .setColor(color(255, 255, 255))
			              ;
		
		OK = cp5.addBang("Click").setPosition(200, 100).setSize(30, 30);
		
		OK.addCallback(new CallbackListener(){
			char textBox;
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					textBox = cp5.get(Textfield.class, "text").getText().charAt(0);
					setMessage(textBox);
					parent.keyboardHappened(theEvent);
					
				}
			}
			
		});
		
		cp5.addButton("CloseButton").setLabel("Close").setSize(50, 20).setPosition(200, 180);
		cp5.getController("CloseButton").getCaptionLabel().setFont(font).setSize(14);
	}
	
	public void draw(){
		textSize(24);
		text("What key is the trigger?", 25, 75);
		
	}
	
	public void setMessage(char m){
		message = m;
	}
	
	public Keyboard getKeyboardInfo(){
		return new Keyboard(message, myController);
	}
	
	public void CloseButton(ControlEvent e){
		frame.setVisible(false);
	}

}
