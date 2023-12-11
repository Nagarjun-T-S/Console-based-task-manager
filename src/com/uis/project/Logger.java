package com.uis.project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {

public static final boolean LOGTOMONITOR = true;
	
	//1. mark constructor private 
	
	private Logger() {
		
	}
	
	//2. create a single copy ref var
	private static Logger obj = null;
	
	//3.expose static getInstance() method
	public static Logger getInstance()
	{
		//4. do null check on single copy reference and then create object
		//only once
		if(obj == null)
		{
			obj = new Logger();
		}
		
		return obj;
	}
	
	public void log(String data)
	{
		new Thread(  new Runnable() //Anonymous inner class
		{
			
			public void run()
			{
				Date dt = new Date();
				BufferedWriter bw = null;
				
				try
				{
					String msg = dt+":"+data;
					bw = new BufferedWriter(new FileWriter("C:\\Users\\Nagarjun T S\\eclipse-workspace\\TaskManager\\log.txt",true));
					bw.write(msg);
					bw.newLine();
					
					if(Logger.LOGTOMONITOR == true)
						System.out.println("Logger: "+msg);
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				finally
				{
					if(bw != null) {
						try {
							bw.close();
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
}
