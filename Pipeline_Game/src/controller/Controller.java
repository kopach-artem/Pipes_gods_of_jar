//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : STHF
//  @ File Name : Controller.java
//  @ Date : 2023. 04. 15.
//  @ Author : 
//
//
package controller;
import container.*;
import map.Map;

public class Controller {

	public void damagePump(){
		for(Container c : Map.getContainers()){
			c.lifeCycle();
		}
	}

	
	/** 
	 * @param p1
	 * @param pu
	 * @param p2
	 */
	public void waterFlow(Pipe p1, Pump pu, Pipe p2)
	{
		p1.eval();
		p2.eval();
		p1.makeHistory();
		p2.makeHistory();
	}
}