/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.PFont;
import responses.Response;
import triggers.Trigger;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controlP5.*;
import javafx.util.Pair;

public class DeleteButton extends PIWindow{
	
	ControlP5 cp5;
	ProgramInterface parent;

	int w,h;

	Frame frame;
	
	String delTrigS;
	String delRespS;
	
	Trigger delTrig;
	Response delResp;
	
	List<String> sTrigs = new ArrayList<>();
	List<String> sResps = new ArrayList<>();
	
	List<Trigger> trigs = new ArrayList<>();
	List<Response> resps = new ArrayList<>();
	
	String sel1 = null;
	String sel2 = null;
	ScrollableList t; 
	ScrollableList r; 
	
	int num1;
	int num2;

	Button d;
	
	Pair<Trigger, Response> p;

	public DeleteButton(){
	}

	public DeleteButton(ProgramInterface p, Frame f, int width, int height){
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
		
		HashMap<Trigger, Response> map = parent.getTRMap();

		for(Map.Entry<Trigger, Response> pair : map.entrySet()){
			Trigger trig = pair.getKey();
			if (!sTrigs.contains(trig.getClass().getName().substring(9))){
				sTrigs.add(trig.getClass().getName().substring(9));
				trigs.add(trig);
				
			}
			
			Response resp = pair.getValue();
			if (!sResps.contains(resp.getClass().getName().substring(10))){
				sResps.add(resp.getClass().getName().substring(10));
				resps.add(resp);
				
			}
		}
		
		t = cp5.addScrollableList("triggers").setLabel("Triggers")
				.setPosition(30, 50).setSize(150,100)
				.setBarHeight(50)
				.setItemHeight(50)
				.addItems(sTrigs)
				.setType(ScrollableList.DROPDOWN).close();
		cp5.getController("triggers").getCaptionLabel().setFont(font).setSize(16);
		
		 t.addCallback(new CallbackListener(){
				
			 @Override
			 public void controlEvent(CallbackEvent theEvent) {
				 if(theEvent.getAction() == ControlP5.ACTION_CLICK){
					String type = sel1;
					Trigger t = trigs.get(num1);
					setDelTrig(t);
					

				 }
			 }
		 });
		 
			r = cp5.addScrollableList("responses").setLabel("Responses")
					.setPosition(210, 50).setSize(150,100)
					.setBarHeight(50)
					.setItemHeight(50)
					.addItems(sResps)
					.setType(ScrollableList.DROPDOWN).close();
			cp5.getController("responses").getCaptionLabel().setFont(font).setSize(16);
			
			 r.addCallback(new CallbackListener(){
					
				 @Override
				 public void controlEvent(CallbackEvent theEvent) {
					 if(theEvent.getAction() == ControlP5.ACTION_CLICK){
						String type = sel2;
						Response r = resps.get(num2);
						setDelResp(r);

					 }
				 }
			 });

		d = cp5.addButton("Delete").setSize(150, 50).setPosition(100, 175);
		cp5.getController("Delete").getCaptionLabel().setFont(font).setSize(14);
		

		cp5.addButton("CloseButton").setLabel("Close").setSize(50, 20).setPosition(180, 250);
		cp5.getController("CloseButton").getCaptionLabel().setFont(font).setSize(14);

	}

	public void draw() {

	}
	
	public void triggers(int n) {
		  sel1 = cp5.get(ScrollableList.class, "triggers").getItem(n).get("name").toString();
		  num1 = n;
		  t.close();
		}
	
	public void responses(int n) {
		  sel2 = cp5.get(ScrollableList.class, "responses").getItem(n).get("name").toString();
		  num2 = n;
		  r.close();
		}
	
	public void setDelTrig(Trigger dt){
		delTrig = dt;
	}
	
	public void setDelResp(Response dr){
		delResp = dr;
	}
	

	
	public void Delete(){

		p = new Pair<Trigger, Response>(delTrig, delResp);
		parent.deletedPair(p);
		frame.setVisible(false);
	}
	

	public void CloseButton(ControlEvent e){
		frame.setVisible(false);
	}
	

}
