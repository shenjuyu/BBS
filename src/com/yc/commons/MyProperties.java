package com.yc.commons;

import java.io.IOException;
import java.util.Properties;

public class MyProperties extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2610367391702754255L;
	private static MyProperties instance=null;
	
	private MyProperties(){
		try {
			this.load(MyProperties.class.getClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
	
	public static MyProperties getInstance(){
		if(null==instance){
			instance=new MyProperties();
		}
		return instance;
	}
}
