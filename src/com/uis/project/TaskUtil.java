package com.uis.project;

import java.io.File;
import java.util.List;
public class TaskUtil
{
	
	public static boolean fileCheck(String catName)
	{
		File file = new File("C:\\Users\\Nagarjun T S\\eclipse-workspace\\TaskManager\\"+catName+".todo");
		if(file.exists())
		{
			return true;
		}
		return false;
	}
	
	public static boolean validateName(String str)
	{
		if(str == null)
			return false;
		
		if(str.trim().equals(""))
			return false;
		
		if(!Character.isLetter(str.charAt(0)))
			return false;
		
		if(str.split(" ").length > 1)// Category name should be one WORD
			return false;
		
		for(int i=1; i<str.length(); i++)
		{
			char c = str.charAt(i);
			
			if(!(Character.isDigit(c) || Character.isLetter(c)))
				return false;
				
		}
		
		return true;
	}
}
