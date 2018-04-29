/*
 * Copyright © 2018 Sarah Allen
 */
package triggers;

import controller.InterfaceController;

public class SoundLevel implements Trigger{
	
	InterfaceController myController;
	boolean quiet;

	public SoundLevel(InterfaceController c, boolean q){
		myController = c;
		quiet = q;
	}
	
	public void receiveSoundLevelEvent(){
		if (quiet){
			
		}
		
		else if (!quiet){
			
		}
	}
}
