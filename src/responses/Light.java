/*
 * Copyright © 2018 Sarah Allen
 */
package responses;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHLight;

import edu.elon.lights.LightCommand;
import edu.elon.lights.LightConnection;
import edu.elon.lights.LightController;
import edu.elon.lights.LightController.LightColors;
import edu.elon.lights.data.LightsProperties;

public class Light implements Response {
	
	private boolean on;
	private String color;
	private int duration;
	
	private PHHueSDK sdk;
	private LightController controller;
	private LightConnection connection;
	private LightCommand command;
	
	public Light(){
		on = true;
		color = "WHITE";
		duration = 10000000;
		
	}
	
	public Light(boolean o, String c, int d){
		on = o;
		color = c;
		duration = d;
	}

	@Override
	public void go() {
		command = new LightCommand();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(!on){
			command.turnOffAllLights();
			command.lightDuration(duration);
			command.turnOnMainLamp(Color.WHITE);
		}
		
		else if (on){
			Color c = command.getColor(color);
			command.turnOnMainLamp(c);
			command.lightDuration(duration);
			command.turnOnMainLamp(Color.WHITE);
			
		}
	}

}
