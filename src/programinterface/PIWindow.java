/*
 * Copyright © 2018 Sarah Allen
 */
package programinterface;

import java.awt.Frame;

import processing.core.PApplet;

public class PIWindow extends PApplet{
	
	public void start(PApplet p, int w, int h, Frame f){
		f.add(p);
		//frame.setTitle(theName);
		f.setSize(w, h);
		f.setLocation(100, 100);
		f.setResizable(false);
		f.setVisible(true);
	}

}
