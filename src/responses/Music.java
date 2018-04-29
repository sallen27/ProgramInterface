/*
 * Copyright © 2018 Sarah Allen
 */
package responses;

public class Music implements Response{
	
	String musicType;
	Boolean soundEffects;
	int duration;
	
	public Music(String m, boolean se, int d){
		musicType = m;
		soundEffects = se;
		duration = d;
	}

	@Override
	public void go() {
		//Set up music
		
	}

}
