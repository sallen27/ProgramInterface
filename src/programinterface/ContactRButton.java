/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.PFont;
import responses.Contact;

import java.awt.Frame;

import controlP5.*;

public class ContactRButton extends PIWindow{

	ControlP5 cp5;
	ProgramInterface parent;

	int w,h;

	Frame frame;

	String email = "";
	String number = "";
	Button OK;
	Bang c1;
	Bang c2;

	public ContactRButton(){
	}

	public ContactRButton(ProgramInterface p, Frame f, int width, int height){
		frame = f;
		parent = p;
		w = width;
		h = height;
	}


	public void setup() {
		start(this, w, h, this.frame);
		size(w, h);
		background(124, 186, 230);
		cp5 = new ControlP5(this);
		PFont font = createFont("ariel", 20);

		Textfield ea = cp5.addTextfield("email address")
				.setPosition(120, 55)
				.setSize(120, 30)
				.setFocus(true)
				.setColor(color(255, 255, 255))
				;

		Textfield pn = cp5.addTextfield("phone number")
				.setPosition(110, 130)
				.setSize(120, 30)
				.setFocus(false)
				.setColor(color(255, 255, 255))
				;
		
		Bang c1 = cp5.addBang("Click1").setLabel("Click!").setPosition(250, 55).setSize(20,20);
		
		Bang c2 = cp5.addBang("Click2").setLabel("Click!").setPosition(240 ,130).setSize(20,20);

		OK = cp5.addButton("OK").setLabel("OK!").setSize(50, 50).setPosition(180, 180);
		cp5.getController("OK").getCaptionLabel().setFont(font).setSize(14);

		cp5.addButton("CloseButton").setLabel("Close").setSize(50, 20).setPosition(180, 250);
		cp5.getController("CloseButton").getCaptionLabel().setFont(font).setSize(14);

		c1.addCallback(new CallbackListener(){
			String e;
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					e = cp5.get(Textfield.class, "email address").getText();
					System.out.println("email in listener: "+ e);
					//System.out.println("Message inside listener1: " + message);
					setEmail(e);
					//System.out.println("Message inside listener2: " + message);


				}
			}

		});

		c2.addCallback(new CallbackListener(){
			String n;
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					n = cp5.get(Textfield.class, "phone number").getText();
					System.out.println("number in listener: "+ n);
					//System.out.println("Message inside listener1: " + message);
					setNumber(n);
					//System.out.println("Message inside listener2: " + message);


				}
			}

		});

		OK.addCallback(new CallbackListener(){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					parent.contactHappened(theEvent);
					frame.setVisible(false);
				}
			}

		});


	}

	public void draw() {
		textSize(24);
		text("Email: ", 35, 75);
		text("Text: ", 35, 150);
		//COME BACK TO THIS

	}

	public void setEmail(String e){
		email = e;
	}

	public void setNumber(String n){
		number = n;
	}


	public void CloseButton(ControlEvent e){
		frame.setVisible(false);
	}

	public Contact getContact(){
		return new Contact(email, number);
	}
}
