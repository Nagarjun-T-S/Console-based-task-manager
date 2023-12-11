package com.uis.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class TaskModel {

	
	public List<String> listTaskName(String catName)
	{
		List<String> list = new ArrayList<>();
		
		File file = new File("C:\\Users\\Nagarjun T S\\eclipse-workspace\\TaskManager\\"+catName+".todo");
		if(file.exists())
		{
			BufferedReader br = null;
			String line;
			try
			{
				br = new BufferedReader(new FileReader(file));
				
				while((line = br.readLine()) != null)
				{
					int firstIndexOf = line.indexOf(":");
					
					String firstWord = line.substring(0,firstIndexOf);
					list.add(firstWord);
				}
				return list;
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
		
	public List<String> listCategory()
	{
		File f = new File("C:\\Users\\Nagarjun T S\\eclipse-workspace\\TaskManager");
		
		List<String> list = new ArrayList<>();
		
		if(f.exists())
		{
			String[] files = f.list();
			
			for(String file : files)
			{
				if(file.endsWith(".todo")) {
					list.add(file);
				}
			}
			return list;
		}
		else {
			return null;
		}
	}
		
	
	public List<TaskBean> getTasks(String catName)
	{
		BufferedReader br = null;
		String line;
		
		try
		{
			List<TaskBean> tasks = new ArrayList<TaskBean>();
			TaskBean task;
	//		br = new BufferedReader(new FileReader(catName+".todo"));
			
			br = new BufferedReader(new FileReader("C:\\Users\\Nagarjun T S\\eclipse-workspace\\TaskManager\\"+catName+".todo"));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			
			while((line = br.readLine())!=null)
			{
				String[] sa = line.split(" : ");
				//String taskName, String desc, String tags, Date plannedDate, int priority
				
				task = new TaskBean(sa[0], sa[1], sa[4], sdf.parse(sa[3]),Integer.parseInt(sa[2]));
				
				tasks.add(task);
			}
			return tasks;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			if(br != null) {
				try {
					br.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String addTask(TaskBean task,String catName)
	{
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter("C:\\Users\\Nagarjun T S\\eclipse-workspace\\TaskManager\\"+catName+".todo",true));
			
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String plDt = sdf.format(d);
		  
	//	  	bw.write(task.getTaskName()+" : "+task.getDesc()+" : "+task.getTags()+" : "+ plDt +" : "+task.getPriority()+" : "+d.getTime());  
			bw.write(task.getTaskName()+" : "+task.getDesc()+" : "+task.getPriority()+" : "+ plDt +" : "+task.getTags()+" : "+d.getTime());
			bw.newLine();
			
			return Constants.SUCCESS;
		}
		catch(IOException e) {
			e.printStackTrace();
			return "Oops something bad happened msg = "+e.getMessage();
		}
		finally {
			if(bw != null) 
			{
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//checking category name is unique or not
	public boolean checkCategoryExists(String catName)
	{
		return new File(catName+".todo").exists();
	}
	
}
