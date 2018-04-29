/*
 * Copyright © 2018 Sarah Allen
 */
package controller;

import java.util.*;

import SimpleOpenNI.*;
import javafx.util.Pair;
import processing.core.PVector;
import responses.*;
import triggers.*;

public class InterfaceController {

	//creating master hashmap for trigger and response pairs
	private HashMap<Trigger,Response> map = new HashMap<Trigger,Response>();

	//Collections for Triggers
	private ArrayList<Keyboard> keyboardTriggers = new ArrayList<Keyboard>();
	private ArrayList<Leap> leapTriggers = new ArrayList<Leap>();
	private ArrayList<Mouse> mouseTriggers = new ArrayList<Mouse>();
	private ArrayList<Point> pointTriggers = new ArrayList<Point>();
	private ArrayList<BodyPoint> bodyPointTriggers = new ArrayList<BodyPoint>();
	private ArrayList<Speed> speedTriggers = new ArrayList<Speed>();
	private ArrayList<NoTrigger> noTriggers = new ArrayList<NoTrigger>();
	private ArrayList<Kinect> kinectTriggers = new ArrayList<Kinect>();
	private ArrayList<Gesture> gestureTriggers = new ArrayList<Gesture>();
	private ArrayList<Position> positionTriggers = new ArrayList<Position>();
	private ArrayList<Emotion> emotionTriggers = new ArrayList<Emotion>();

	public InterfaceController(){

	}

	public void gatherDetails(Trigger trig, Response response){

		//puts pairs in master hashmap
		map.put(trig, response);

		//puts triggers in proper ArrayLists
		if(trig.getClass().getName().equals("triggers.Keyboard")){
			keyboardTriggers.add((Keyboard)trig);
		}
		else if(trig.getClass().getName().equals("triggers.Leap")){
			leapTriggers.add((Leap) trig);
		}
		else if(trig.getClass().getName().equals("triggers.Mouse")){
			mouseTriggers.add((Mouse) trig);
		}
		else if (trig.getClass().getName().equals("triggers.ChildPoint")){
			pointTriggers.add((Point)trig);
		}
		else if (trig.getClass().getName().equals("triggers.BodyPoint")){
			bodyPointTriggers.add((BodyPoint)trig);
		}
		else if (trig.getClass().getName().equals("triggers.Speed")){
			speedTriggers.add((Speed)trig);
		}
		else if (trig.getClass().getName().equals("triggers.NoTrigger")){
			noTriggers.add((NoTrigger)trig);
		}
		else if (trig.getClass().getName().equals("triggers.Kinect")){
			kinectTriggers.add((Kinect)trig);
		}
		else if (trig.getClass().getName().equals("triggers.Gesture")){
			gestureTriggers.add((Gesture)trig);
		}
		else if (trig.getClass().getName().equals("triggers.Position")){
			positionTriggers.add((Position)trig);
		}
		else if (trig.getClass().getName().equals("triggers.Emotion")){
			emotionTriggers.add((Emotion)trig);
		}
	}

	//sends trigger events to proper triggers
	public void keyBoardEvent(char key){
		for(Keyboard kTrig: keyboardTriggers){
			kTrig.receiveEvent(key);
		}
	}

	public void leapFrameEvent(com.leapmotion.leap.Frame f){
		for(Leap l: leapTriggers){
			l.receiveLeapEvent(f);
		}

		for(Point p : pointTriggers){
			p.receivePointEvent(f);
		}
	}

	public void mouseEvent(){
		for(Mouse m: mouseTriggers){
			m.receiveMouseEvent(true);
		}
	}

	public void noTrigEvent(){
		for(NoTrigger n: noTriggers){
			n.receiveNoTrigEvent();
		}
	}

	public void kinectEvent(SimpleOpenNI s, ArrayList<Integer> users, PVector pos){

		for (Kinect k: kinectTriggers){
			k.receiveKinectEvent(s, users, pos);
		}

		for (BodyPoint bp : bodyPointTriggers){
			bp.receiveBodyPointEvent(s, users, pos);
		}

		for (Position p : positionTriggers){
			p.receivePositionEvent(s, users, pos);
		}

		for(Speed sp : speedTriggers){
			sp.receiveSpeedEvent(s, users, pos);
		}

		for(Gesture g : gestureTriggers){
			g.receiveGestureEvent(s, users, pos);
		}
	}

	//uses trigger to get proper response and release response
	public void callback(Trigger t){
		map.get(t).go();
	}

	public HashMap<Trigger, Response> sendMap(){
		return map;
	}

	//delete a trigger/response pair
	public void deletePair(Pair<Trigger, Response> p){
		Trigger deltrig = p.getKey();
		Response delresp = p.getValue();

		for(Map.Entry<Trigger, Response> pair : map.entrySet()){
			String tClassName = pair.getKey().getClass().getName().substring(9);

			if (pair.getKey() == deltrig && pair.getValue() == delresp){
				map.remove(pair.getKey(), pair.getValue());

				if(tClassName.equals("Keyboard")){
					if (keyboardTriggers.contains(pair.getKey())){
					}
					keyboardTriggers.remove(pair.getKey());
					break;
				}
				else if(tClassName.equals("Leap")){
					leapTriggers.remove(pair.getKey());
					break;
				}
				else if(tClassName.equals("Mouse")){
					mouseTriggers.remove(pair.getKey());
					break;
				}
				else if (tClassName.equals("ChildPoint")){
					pointTriggers.remove(pair.getKey());
					break;
				}
				else if (tClassName.equals("BodyPoint")){
					bodyPointTriggers.remove(pair.getKey());
					break;
				}
				else if (tClassName.equals("Speed")){
					speedTriggers.remove(pair.getKey());
					break;
				}
				else if (tClassName.equals("NoTrigger")){
					noTriggers.remove(pair.getKey());
					break;
				}
				else if (tClassName.equals("Kinect")){
					kinectTriggers.remove(pair.getKey());
					break;
				}

				else if (tClassName.equals("Gesture")){
					gestureTriggers.remove(pair.getKey());
					break;
				}

				else if (tClassName.equals("Position")){
					positionTriggers.remove(pair.getKey());
					break;
				}
			}

		}

	}
}
