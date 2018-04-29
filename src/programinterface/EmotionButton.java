/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.PFont;
import triggers.Emotion;

import java.awt.Frame;

import controlP5.*;
import controller.InterfaceController;

public class EmotionButton extends PIWindow{

	ControlP5 cp5;
	ProgramInterface parent;
	InterfaceController myController;
	
	int w, h;
	
	Frame frame;
	
	String emotion;
	Button hap, s, a;
	
	public EmotionButton(){
		
	}
	
	public EmotionButton(ProgramInterface p, Frame f, int width, int height, InterfaceController c){
		frame = f;
		parent = p;
		h = height;
		w = width;
		myController = c;
	}
	
	public void setup(){
		start(this, w, h, this.frame);
		size(w, h);
		background(124, 186, 230);
		cp5 = new ControlP5(this);
		PFont font = createFont("ariel", 20);
		
		hap = cp5.addButton("Happy").setSize(150, 50).setPosition(150, 70);
		cp5.getController("Happy").getCaptionLabel().setFont(font).setSize(16);
		s = cp5.addButton("Sad").setSize(150, 50).setPosition(150, 125);
		cp5.getController("Sad").getCaptionLabel().setFont(font).setSize(16);
		a = cp5.addButton("Angry").setSize(150, 50).setPosition(150, 180);
		cp5.getController("Angry").getCaptionLabel().setFont(font).setSize(16);
		
		cp5.addButton("ExitingButton").setLabel("Close").setSize(100,40).setPosition(170, 235);
		cp5.getController("ExitingButton").getCaptionLabel().setFont(font).setSize(16);
		
		hap.addCallback(new CallbackListener(){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getAction() == ControlP5.ACTION_RELEASE){
					setEmotion("happy");
					frame.setVisible(false);
					parent.emotionHappened(theEvent);
					
				}
			}
			
		});

	}
	
	public void draw(){
		textSize(24);
		text("What is the Child's Emotion?", 75, 50);
		
	}
	
//	public void Happy(ControlEvent e){
//		emotion = "happy";
//		frame.setVisible(false);
//		parent.emotionHappened();
//	}
//	
	public void Sad(ControlEvent e){
		emotion = "sad";
		frame.setVisible(false);
		parent.emotionHappened();
	}
	
	public void Angry(ControlEvent e){
		emotion = "angry";
		frame.setVisible(false);
		parent.emotionHappened();
	}
	
	public void setEmotion(String e){
		emotion = e;
	}
	
	public void ExitingButton(ControlEvent e){
		frame.setVisible(false);
	}
	
	public Emotion getEmotion(){
		System.out.println("Emotion: " + emotion);
		return new Emotion(myController, emotion);
	}
}
