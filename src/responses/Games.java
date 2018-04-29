/*
 * Copyright © 2018 Sarah Allen
 */
package responses;

public class Games implements Response{
	
	String app;
	
	public Games(String a){
		app = a;
	}
	
	public void go(){
		if (app.equals("paintbrush")){
			//set up code for lights/paintbrush
		}
		
		else if (app.equals("instrument")){
			//set up code for virtual instrument
		}
	}

}
