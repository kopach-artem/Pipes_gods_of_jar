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

	private Map map;
	private int turnCount;

	public void damagePump(){
		for(Container c : map.getContainers()){
			c.lifeCycle(turnCount);
		}
	}

	public void increaseTurnCount(){
		turnCount++;
	}

	
	/** 
	 * @param p1
	 * @param pu
	 * @param p2
	 */
	public void waterFlow()
	{
		for(Container c : map.getContainers()){
			c.eval();
			c.makeHistory();
		}
	}
	public void setMap(Map map){
		this.map = map;
	}
}