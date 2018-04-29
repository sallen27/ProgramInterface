package edu.elon.lights;

import java.awt.Color;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHLight;

import edu.elon.lights.LightController.LightColors;
import edu.elon.lights.data.LightsProperties;

public class LightCommand {

	private PHHueSDK sdk;
	private LightController controller;
	private LightConnection connection;
	private List<PHLight> lights;
	private PHLight leftLight;
	private PHLight rightLight1;
	private PHLight rightLight2;
	
	public LightCommand() {
		
		sdk = PHHueSDK.create();
		LightsProperties.loadProperties();
		
		connection = new LightConnection();
		sdk.getNotificationManager().registerSDKListener(connection.getListener());
		connection.findBridges();
		connection.connect();

		controller = connection.getLightController();
	}
	
	public boolean isConnected () {
		List<PHLight> lights = null;
		try {
			lights = connection.getLights();
		} catch (NullPointerException e) {
			return false;
		}
		return connection.getLights() != null;
	}
	
	public void turnOffAllLights() {
        List<PHLight> lights = connection.getLights();
		
		controller.turnOffAllLights(lights);
	}
	
	public void turnOnAllRandom() {
		ArrayList<PHLight> lights = (ArrayList<PHLight>) connection.getLights();
		controller.turnOnAllLights(lights);
	}
	
	
	public void turnOnMainLamp(Color c) {
		List<PHLight> lights = connection.getLights();
		
		controller.turnLightRGB(lights.get(0), c.getRed(), c.getGreen(), c.getBlue());
	}
	
	public void lightDuration(int t){
		int time = t * 60000;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Color getColor(String c){
		Map<String, Color> colorTable = controller.getColorOptions();
		return colorTable.get(c);
	}
	

}
