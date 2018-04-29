/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.PFont;
import responses.Music;

import java.awt.Frame;
import java.util.*;
import controlP5.*;

public class MusicRButton extends PIWindow{

	ControlP5 cp5;
	ProgramInterface parent;
	
	int w,h;
	
	Frame frame;

	String musicType = "none";
	boolean soundEffects = false;
	int duration = 60;

	Bang b;
	Button OK;
	
	String selected = null;
	ScrollableList sl;

	public MusicRButton(){
	}

	public MusicRButton(ProgramInterface p, Frame f, int width, int height){
		frame = f;
		parent = p;
		w = width;
		h = height;
	}

	public void setup(){
		start(this, w, h, this.frame);
		size(w, h);
		background(124, 186, 230);
		cp5 = new ControlP5(this);
		PFont font = createFont("ariel", 20);

		//OPTIONS IN HERE
		List<String> music = Arrays.asList("pop", "rock", "country", "classical", "lyrical", "none");

		sl = cp5.addScrollableList("musicOptions").setLabel("Music Options")
				.setPosition(125, 50).setSize(200,100)
				.setBarHeight(50)
				.setItemHeight(50)
				.addItems(music)
				.setType(ScrollableList.DROPDOWN).close();
		cp5.getController("musicOptions").getCaptionLabel().setFont(font).setSize(16);
		
		 sl.addCallback(new CallbackListener(){
				
			 @Override
			 public void controlEvent(CallbackEvent theEvent) {
				 if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					String type = selected;
					setMusicType(type);

				 }
			 }
		 });

		cp5.addButton("Yes").setSize(150, 50).setPosition(50, 185);
		cp5.getController("Yes").getCaptionLabel().setFont(font).setSize(16);
		cp5.addButton("No").setSize(150, 50).setPosition(225, 185);
		cp5.getController("No").getCaptionLabel().setFont(font).setSize(16);
		

		cp5.addTextfield("duration").setLabel("")
		.setPosition(205, 285)
		.setSize(100, 30)
		.setFocus(true)
		.setColor(color(255, 255, 255));

		b = cp5.addBang("Click!").setPosition(310 ,285).setSize(20,20);

		b.addCallback(new CallbackListener(){
			String textBox;
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					textBox = cp5.get(Textfield.class, "duration").getText();
					setDuration(textBox);
				}
			}

		});

		OK = cp5.addButton("OK").setLabel("OK!").setSize(50,50).setPosition(190, 335);
		cp5.getController("OK").getCaptionLabel().setFont(font).setSize(16);

		OK.addCallback(new CallbackListener(){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					parent.musicHappened(theEvent);
					frame.setVisible(false);
				}
			}

		});

		cp5.addButton("ExitingButton").setLabel("Close").setSize(60,30).setPosition(370, 355);
		cp5.getController("ExitingButton").getCaptionLabel().setFont(font).setSize(16);
	}

	public void draw(){
		textSize(24);
		text("What type of music?", 100, 35);
		text("Sounds effects?", 150, 175);
		//text("No music", 140, 268);
		text("Duration: ", 90, 310);
	}
	
	public void musicOptions(int n) {
		  selected = cp5.get(ScrollableList.class, "musicOptions").getItem(n).get("name").toString();
		  sl.close();
		}
	
	public void setMusicType(String mt){
		musicType = mt;
	}
	
	public void setDuration(String d){
		if(d.equals("")){
			duration = 0;
		}
		else{
			duration = Integer.parseInt(d);
		}
	}
	
	public Music getMusic(){
		System.out.println("musicType: " + musicType);
		return new Music(musicType, soundEffects, duration);
	}
	
	public void ExitingButton(ControlEvent e){
		frame.setVisible(false);
	}
}
