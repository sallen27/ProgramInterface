/*
 * Copyright © 2018 Sarah Allen
 */
package responses;

public class Printer implements Response{

	private String message;

	public Printer(String toPrint){
		message = toPrint;
	}
	
	public void go(){
		System.out.println(message);
	}
}
