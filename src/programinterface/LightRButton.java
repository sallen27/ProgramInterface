/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.PFont;
import responses.Light;

import java.awt.Frame;
import java.util.Arrays;
import java.util.List;

import controlP5.*;

public class LightRButton extends PIWindow{

	ControlP5 cp5;
	ProgramInterface parent;
	
	int w,h;
	
	Frame frame;
	
	boolean on = true;
	String color = "white";
	int duration = 60;
	
	Bang b;
	Button OK;
	ScrollableList sl;
	String selected = null;
	
	PFont font = createFont("ariel", 20);
	
	public LightRButton(){
	}
	
	public LightRButton(ProgramInterface p, Frame f, int width, int height){
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

		
		//light on or off buttons
		cp5.addButton("ON").setSize(150, 50).setPosition(50, 45);
		cp5.getController("ON").getCaptionLabel().setFont(font).setSize(16);
		cp5.addButton("OFF").setSize(150, 50).setPosition(225, 45);
		cp5.getController("OFF").getCaptionLabel().setFont(font).setSize(16);
		
		// light color drop down list
		List<String> lightColors = Arrays.asList("white", "pink", "red", "light blue", "blue", "orange", "green", "light yellow", "yellow", 
				"light purple", "purple", "magenta");
		sl = cp5.addScrollableList("colorOptions").setLabel("Color Options")
			.setPosition(200, 115).setSize(200,100)
			.setBarHeight(50)
			.setItemHeight(50)
			.addItems(lightColors)
			.setType(ScrollableList.DROPDOWN)
			.close();
		
		cp5.getController("colorOptions").getCaptionLabel().setFont(font).setSize(16);
		
		 sl.addCallback(new CallbackListener(){
			
			 @Override
			 public void controlEvent(CallbackEvent theEvent) {
				 if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					String color = selected;
					setColor(color);

				 }
			 }
		 });
		 
		 cp5.addTextfield("duration")
		 .setPosition(185, 220)
		 .setSize(200, 40)
		 .setFocus(true)
		 .setColor(color(255, 255, 255))
		 ;

		 b = cp5.addBang("Click!").setPosition(395 ,230).setSize(20,20);

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
		 

		 OK = cp5.addButton("OK").setLabel("OK!").setSize(50,50).setPosition(175, 288);
		 cp5.getController("OK").getCaptionLabel().setFont(font).setSize(16);

		 OK.addCallback(new CallbackListener(){
			 @Override
			 public void controlEvent(CallbackEvent theEvent) {
				 if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					 parent.lightsHappened(theEvent);
					 frame.setVisible(false);
				 }
			 }

		 });

		  cp5.addButton("ExitingButton").setLabel("Close").setPosition(350,295).setSize(70, 40);
		  cp5.getController("ExitingButton").getCaptionLabel().setFont(font).setSize(16);
	}
	
	public void colorOptions(int n) {
		  selected = cp5.get(ScrollableList.class, "colorOptions").getItem(n).get("name").toString();
		  sl.close();
		}
	
	public void draw(){
		textSize(24);
		text("Turn light on or off?", 100, 35);
		text("Light color: ", 50, 140);
		text("Duration?", 60, 250);
	}
	
	//popup if not a number
	public void setDuration(String d){
		if(d.equals("")){
			duration = 0;
		}
		else {
			try{
				duration = Integer.parseInt(d);
			}
			catch (Exception e){
				duration = 60;
			}
		}
	}
	
	public void setColor(String c){
		color = c;
	}
	
	public void ON(){
		on = true; 
	}
	
	public void OFF(){
		on = false;
	}
	
	public Light getLights(){
		return new Light(on, color, duration);
	}
	
	public void ExitingButton(ControlEvent e){
		frame.setVisible(false);
	}
}