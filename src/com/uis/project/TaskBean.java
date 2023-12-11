package com.uis.project;

import java.util.Date;
import java.util.Objects;

//model
public class TaskBean {

	private String taskName;
	private String desc;
	private String tags;
	private Date plannedDate;
	private int priority;
	
	
	public String getTaskName() {
		return taskName;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public String getTags() {
		return tags;
	}
	public Date getPlannedDate() {
		return plannedDate;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public void setPlannedDate(Date plannedDate) {
		this.plannedDate = plannedDate;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(desc, plannedDate, priority, tags, taskName);
	}
	
	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskBean other = (TaskBean) obj;
		return Objects.equals(desc, other.desc) && Objects.equals(plannedDate, other.plannedDate)
				&& priority == other.priority && Objects.equals(tags, other.tags)
				&& Objects.equals(taskName, other.taskName);
	}
	
	
	@Override
	public String toString() 
	{
		return "TaskBean [taskName=" + taskName + ", desc=" + desc + ", tags=" + tags + ", plannedDate=" + plannedDate
				+ ", priority=" + priority + "]";
	}
	
	public TaskBean() {
		// TODO Auto-generated constructor stub
	}
	
	//*****************not completed**********************
	private void setTaskName1(String tname)
	{
		if(tname == null)
			throw new IllegalArgumentException("TaskName should not be null");
		else if(tname.trim().equals(""))
			throw new IllegalArgumentException("TaskName should not be empty");
		
		
	}
	
	public TaskBean(String taskName, String desc, String tags, Date plannedDate, int priority) 
	{
		super();
		this.taskName = taskName;
		this.desc = desc;
		this.tags = tags;
		this.plannedDate = plannedDate;
		this.priority = priority;
	}
}	
	
