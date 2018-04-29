/*
 * Copyright © 2018 Sarah Allen
 */
package responses;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.sun.media.jfxmedia.Media;

import javafx.scene.media.MediaPlayer;

public class Sound implements Response{

	String file; 
	
	public Sound(String fileName){
		file = fileName;
	}
	
	@Override
	public void go() {
	       try{
	    	      AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource(file));
	    	     Clip clip = AudioSystem.getClip();
	    	     clip.open(audioInputStream);
	    	     clip.start( );
	    	    }
	    	   catch(Exception ex)
	    	   { 
	    		   ex.printStackTrace();
	    		   System.out.println("Cant find song");
	    	   }
	}

}
