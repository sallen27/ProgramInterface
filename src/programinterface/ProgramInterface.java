/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import processing.core.*;
import responses.*;
import triggers.*;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controlP5.*;
import controller.InterfaceController;
import javafx.util.Pair;

import com.leapmotion.leap.Controller;

import SimpleOpenNI.SimpleOpenNI;

public class ProgramInterface extends PApplet {

	InterfaceController myController = new InterfaceController();
	ControlP5 cp5;
	protected SimpleOpenNI context;

	Trigger trig;
	Response resp;
	HashMap<Trigger, Response> trigRespMap;

	Textarea trigText;
	Textarea responseText;

	Textarea currentRules;

	private Controller controller = new Controller();
	com.leapmotion.leap.Frame frame;

	Frame bigFrame;

	protected ArrayList<Integer> users = new ArrayList<>();
	protected PVector pos = new PVector();

	HashMap<Trigger, Response> currentTrigResp;

	PApplet currentWindow = new PApplet();

	public void settings(){
		size(1280, 705);
	}

	public void setup() {
		size(1280, 705);
		background(124, 186, 230);
		cp5 = new ControlP5(this);
		PFont font = createFont("ariel", 20);

		//Kinect setup
		context = new SimpleOpenNI(this);

		context.setMirror(true);
		context.enableDepth();
		context.enableUser();

		fill(0,0,0);

		trigRespMap = new HashMap<Trigger, Response>();

		//Trigger Buttons
		cp5.addButton("PositionButton").setLabel("Postion").setSize(150,50).setPosition(75, 90);
		cp5.getController("PositionButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("PointPositionButton").setLabel("Point Position").setSize(150,50).setPosition(235, 90);
		cp5.getController("PointPositionButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("BodyPointButton").setLabel("Body and Point").setSize(150,50).setPosition(395, 90);
		cp5.getController("BodyPointButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("EmotionButton").setLabel("Emotion").setSize(150,50).setPosition(75, 150);
		cp5.getController("EmotionButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("SoundLevelButton").setLabel("Sound Level").setSize(150,50).setPosition(235, 150);
		cp5.getController("SoundLevelButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("NoTriggerButton").setLabel("No Trigger").setSize(150,50).setPosition(395, 150);
		cp5.getController("NoTriggerButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("GestureButton").setLabel("Gesture").setSize(150,50).setPosition(75, 210);
		cp5.getController("GestureButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("BodySpeedButton").setLabel("Body Speed").setSize(150,50).setPosition(235, 210);
		cp5.getController("BodySpeedButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("KeyboardButton").setLabel("Keyboard").setSize(150, 50).setPosition(395, 210);
		cp5.getController("KeyboardButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("LeapButton").setLabel("Leap").setSize(150, 50).setPosition(75, 270);
		cp5.getController("LeapButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("KinectButton").setLabel("Kinect").setSize(150, 50).setPosition(235, 270);
		cp5.getController("KinectButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("MouseButton").setLabel("Mouse Click").setSize(150, 50).setPosition(395, 270);
		cp5.getController("MouseButton").getCaptionLabel().setFont(font).setSize(16);

		//Response Buttons
		cp5.addButton("LightRButton").setLabel("Lights").setSize(150,50).setPosition(775, 90);
		cp5.getController("LightRButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("MusicRButton").setLabel("Music").setSize(150,50).setPosition(935, 90);
		cp5.getController("MusicRButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("GameRButton").setLabel("Games").setSize(150,50).setPosition(935, 150);
		cp5.getController("GameRButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("ContactRButton").setLabel("Contact").setSize(150,50).setPosition(775, 150);
		cp5.getController("ContactRButton").getCaptionLabel().setFont(font).setSize(14);

		cp5.addButton("SoundButton").setLabel("Sounds").setSize(150,50).setPosition(775, 210);
		cp5.getController("SoundButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("PrinterButton").setLabel("Write to Screen").setSize(150,50).setPosition(935, 210);
		cp5.getController("PrinterButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("Go1").setLabel("GO!").setSize(50, 50).setPosition(1075, 320);
		cp5.getController("Go1").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("DeleteButton").setLabel("Delete").setSize(150, 50).setPosition(1075, 600);
		cp5.getController("DeleteButton").getCaptionLabel().setFont(font).setSize(16);

		cp5.addButton("ExitButton").setLabel("Exit").setSize(150, 50).setPosition(50, 600);
		cp5.getController("ExitButton").getCaptionLabel().setFont(font).setSize(16);

		trigText = cp5.addTextarea("tText").setPosition(248, 338).setSize(305, 50).setFont(createFont("ariel", 30))
				.setLineHeight(1)
				.setColor(color(0))
				.setColorBackground(color(124, 186, 230))
				.setColorForeground(color(124, 186, 230));


		responseText = cp5.addTextarea("rtext").setPosition(748, 338).setSize(305, 50).setFont(createFont("ariel", 30))
				.setLineHeight(1)
				.setColor(color(0))
				.setColorBackground(color(124, 186, 230))
				.setColorForeground(color(124, 186, 230));

		currentRules = cp5.addTextarea("rules").setPosition(454, 425).setSize(550, 260).setFont(createFont("ariel", 40))
				.setLineHeight(40)
				.setColor(color(0))
				.setColorBackground(color(124, 186, 230))
				.setColorForeground(color(124, 186, 230))
				.showScrollbar();
	}

	public void draw() {
		//text
		textSize(50);
		text("Pick a Trigger:", 150, 65);
		text("Pick a Response:", 725, 65);

		//arrow
		strokeWeight(4);
		line(250, 390, 550, 390);
		line(560, 355, 700, 355);
		triangle(700, 330, 700, 380, 725, 355);
		line(750, 390, 1050, 390);

		//sets current rules text on screen
		textSize(30);
		text("Current Rules: ", 250, 425);

		String rules = "";
		HashMap<Trigger, Response> TRMap = myController.sendMap();
		for (Map.Entry<Trigger, Response> pair : TRMap.entrySet()){
			rules = rules + pair.getKey().getClass().getName().substring(9) + " → " +
					pair.getValue().getClass().getName().substring(10)+ "\n";

		}

		currentRules.setText(rules);

		//sending Leap information
		frame = controller.frame();
		sendLeapFrame(frame);

		//send no trigger
		sendNoTrigger();

		//send Kinect information
		context.update();
		sendKinectTrigger(context, users, pos);	

	}

	public void keyReleased(){
		sendKeyTrigger(key);		
	}

	public void mouseReleased(){
		sendMouseTrigger();
	}

	public void ExitButton(ControlEvent theEvent){
		System.exit(-1);
	}

	//Trigger Creations
	public void PositionButton(ControlEvent theEvent){
		PApplet p = new PositionButton(this, new Frame(), 400, 400, myController);
		p.init();
		p.setup();
		currentWindow = p;
		trigText.setText("Position");

	}

	public void PointPositionButton(ControlEvent theEvent){
		trig = new Point(myController);
		trigText.setText("Point Position");

	}

	public void BodyPointButton(ControlEvent theEvent){
		PApplet p = new BodyPointButton(this, new Frame(), 450, 250, myController);
		p.init();
		p.setup();
		currentWindow = p;
		trigText.setText("Body and Point");
	}

	public void EmotionButton(ControlEvent theEvent){
		PApplet p = new EmotionButton(this, new Frame(), 450, 310, myController);
		p.init();
		p.setup();	
		currentWindow = p;
		trigText.setText("Emotion");
	}

	public void SoundLevelButton(ControlEvent theEvent){
		PApplet p = new SoundLevelButton(this, new Frame(), 450, 300, myController);
		p.init();
		p.setup();	
		currentWindow = p;
		trigText.setText("Sound Level");
	}

	public void GestureButton(ControlEvent theEvent){
		PApplet p = new GestureButton(this, new Frame(), 400, 300, myController, controller);
		p.init();
		p.setup();	
		currentWindow = p;
		trigText.setText("Gesture");
	}

	public void BodySpeedButton(ControlEvent theEvent){
		trig = new Speed(myController);	
		trigText.setText("Body Speed");
	}

	public void NoTriggerButton(ControlEvent theEvent){
		trig = new NoTrigger(myController);
		trigText.setText("No Trigger");
	}

	public void LeapButton(ControlEvent theEvent){
		trig = new triggers.Leap(myController);
		trigText.setText("Leap");
	}

	public void KeyboardButton(ControlEvent e){
		PApplet p = new KeyboardButton(this, new Frame(), 350, 250, myController);
		p.init();
		p.setup();
		currentWindow = p;
		trigText.setText("Keyboard");
	}

	public void MouseButton(ControlEvent theEvent){
		trig = new Mouse(myController);
		trigText.setText("Mouse");
	}

	public void KinectButton(ControlEvent theEvent){
		trig = new Kinect(myController);
		trigText.setText("Kinect");
	}

	//Response Creations
	public void LightRButton(ControlEvent theEvent){
		PApplet p = new LightRButton(this, new Frame(), 450, 370);
		p.init();
		p.setup();		
		currentWindow = p;
		responseText.setText("Lights");
	}

	public void MusicRButton(ControlEvent theEvent){
		PApplet p = new MusicRButton(this, new Frame(), 450, 425);
		p.init();
		p.setup();	
		currentWindow = p;
		responseText.setText("Music");
	}

	public void GameRButton(ControlEvent theEvent){
		GameRButton p = new GameRButton(this, new Frame(), 450, 250);
		p.init();
		p.setup();	
		currentWindow = p;
		responseText.setText("Game");
	}

	public void ContactRButton(ControlEvent theEvent){
		PApplet p = new ContactRButton(this, new Frame(), 400, 300);
		p.init();
		p.setup();	
		currentWindow = p;
		responseText.setText("Contact");
	}

	public void SoundButton(ControlEvent theEvent){
		resp = new Sound("/beep-05.wav");
		responseText.setText("Sound");
	}

	public void PrinterButton(ControlEvent theEvent){
		PrinterButton p = new PrinterButton(this, new Frame(), 400, 250);
		p.init();
		p.setup();
		currentWindow = p;
		responseText.setText("Write to screen");
	}

	//sends trigger and response pair to the Controller
	public void Go1(ControlEvent e){

		if(trig != null && resp != null){
			trigRespMap.put(trig, resp);
			myController.gatherDetails(trig, resp);
		}
		trigText.setText("");
		responseText.setText("");
	}

	//deletes a trigger and response pair
	public void DeleteButton(ControlEvent theEvent){
		PApplet p = new DeleteButton(this, new Frame(), 400, 400);
		p.init();
		p.setup();
		currentWindow = p;
	}

	//getting Trigger/Response HashMap from Controller
	public HashMap<Trigger, Response> getTRMap(){

		return myController.sendMap();
	}

	//sending pair to be deleted back to the Controller
	public void deletedPair(Pair<Trigger, Response> p){
		myController.deletePair(p);
	}

	//sending inputs for Triggers
	public void sendLeapFrame(com.leapmotion.leap.Frame f){
		myController.leapFrameEvent(f);
	}

	public void sendKeyTrigger(char k){
		myController.keyBoardEvent(k);
	}

	public void sendMouseTrigger(){
		myController.mouseEvent();
	}

	public void sendNoTrigger(){
		myController.noTrigEvent();
	}

	public void sendKinectTrigger(SimpleOpenNI s, ArrayList<Integer> users, PVector pos){
		myController.kinectEvent(s, users, pos);
	}

	//Callbacks for windows with extra information
	public void printerHappened(CallbackEvent e){
		PrinterButton printerWindow = (PrinterButton) currentWindow;
		resp = printerWindow.getPrinter();
	}

	public void emotionHappened(CallbackEvent e){
		EmotionButton eb = (EmotionButton) currentWindow;
		trig = eb.getEmotion();
	}

	public void emotionHappened(){
		EmotionButton eb = (EmotionButton) currentWindow;
		trig = eb.getEmotion();
	}

	public void bodyPointHappened(){
		BodyPointButton bp = (BodyPointButton) currentWindow;
		trig = bp.getBodyPointInfo();
	}

	public void positionHappened(){
		PositionButton p = (PositionButton) currentWindow;
		trig = p.getPositionInfo();
	}

	public void gestureHappened(){
		GestureButton g = (GestureButton) currentWindow;
		trig = g.getGestureInfo();
	}

	public void soundLevelHappened(){
		SoundLevelButton g = (SoundLevelButton) currentWindow;
		trig = g.getSoundLevelInfo();
	}

	public void keyboardHappened(CallbackEvent e){
		KeyboardButton g = (KeyboardButton) currentWindow;
		trig = g.getKeyboardInfo();
	}

	public void gamesHappened(){
		GameRButton grb = (GameRButton) currentWindow;
		resp = grb.getGame();
	}

	public void contactHappened(CallbackEvent e){
		ContactRButton crb = (ContactRButton) currentWindow;
		resp = crb.getContact();
	}

	public void lightsHappened(CallbackEvent e){
		LightRButton lrb = (LightRButton) currentWindow;
		resp = lrb.getLights();
	}

	public void musicHappened(CallbackEvent e){
		MusicRButton srb = (MusicRButton) currentWindow;
		resp = srb.getMusic();
	}

	public void speedHappened(CallbackEvent e){
		BodySpeedButton bsb = (BodySpeedButton) currentWindow;
		trig = bsb.getSpeed();
	}

	//adding new users for Kinect
	public void onNewUser(SimpleOpenNI curContext, int userId) {
		users.add(userId);
		curContext.startTrackingSkeleton(userId);
	}

}