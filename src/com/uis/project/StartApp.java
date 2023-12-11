package com.uis.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StartApp {

	public static void main(String[] args) {
		try {
			Scanner sc1 = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);

			int choice1 = 0, choice2 = 0;
			String catName, taskName, desc, tags, splDt;
			int priority;

			Logger.getInstance().log("Starting Task Manager...");
			TaskModel model = new TaskModel();
			List<String> list = null;

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			while (choice1 != 7) {
				System.out.println("");

				System.out.println("Press 1 to create Category");
				System.out.println("Press 2 to load Category");
				System.out.println("Press 3 to remove Category");
				System.out.println("Press 4 to list Cateogry");
				System.out.println("Press 5 to search Category");
				System.out.println("Press 6 to export");
				System.out.println("Press 7 exit");

				choice1 = sc1.nextInt();

				switch (choice1) {
				case 1:
					System.out.println("Creating a Category...");

					System.out.println("Enter Category name");
					catName = sc2.nextLine();

					// 1st perform name validation
					while (!TaskUtil.validateName(catName)) {
						System.out.println("Category name must be single word, start with letter, only aplhanumeric.. enter another Category name");
						catName = sc2.nextLine();

					}
					// then perform business validations
					// check if Category name is unique
					/*
					 * File f = new File(catName); f.exists();
					 */
					if (model.checkCategoryExists(catName)) {
						System.out.println("catName name is already exists");
					} else {

						while (choice2 != 6) 
						{
							System.out.println("Press 1 create task");
							System.out.println("Press 2 update task");
							System.out.println("Press 3 remove task");
							System.out.println("Press 4 list task");
							System.out.println("Press 5 search task");
							System.out.println("Press 6 to back");

							choice2 = sc1.nextInt();

							switch (choice2) 
							{
							case 1:
								Logger.getInstance().log("creating task..");

								System.out.println("Enter task name");
								taskName = sc2.nextLine();

								System.out.println("Enter description");
								desc = sc2.nextLine();

								System.out.println("Enter tags(comma separated tags)");
								tags = sc2.nextLine();

								System.out.println("Enter planned end date (dd/mm/yyyy)");
								splDt = sc2.nextLine();

								System.out.println("Enter priority(1- very low, 10- very high)");
								priority = sc1.nextInt();

								Date dt = sdf.parse(splDt);

								// String taskName, String desc, String tags, Date plannedDate, int priority

								TaskBean bean = new TaskBean(taskName, desc, tags, dt, priority);

								String result = model.addTask(bean, catName);

								if (result.equals(Constants.SUCCESS)) {
									System.out.println("Task " + taskName + " got added successfully");
								} else {
									System.out.println("Task addition failed. Msg is " + result);
								}

								break;


							case 2:
								
								break;
								
							case 3:
								System.out.println("Enter the Task Name");
								
								break;

							case 4:
								List<TaskBean> tasks = model.getTasks(catName);

								if (tasks != null) {
									for (TaskBean task : tasks) {
										System.out.println("Name:" + task.getTaskName() + " Desc:" + task.getDesc()
												+ " priority:" + task.getPriority() + " Tags:" + task.getTags());// +"// PlannedDate// :"+task.getPlannedDate()
									}
								} else {
									System.out.println("TaskBean is null");
								}

								break;
								
							case 5:
								System.out.println();
								
							case 6:
								System.out.println("going back");
								break;
							}
						}
					}

					break;
					
				case 2:
					System.out.println("Enter the Category");
					catName = sc2.nextLine();

					List<TaskBean> tasks1 = model.getTasks(catName);

					if (tasks1 != null) {
						for (TaskBean task : tasks1) {
							System.out.println("Name:" + task.getTaskName() + " Desc:" + task.getDesc()
									+ " priority:" + task.getPriority() + " Tags:" + task.getTags());// +"// PlannedDate// :"+task.getPlannedDate()
						}
					} else {
						System.out.println("TaskBean is null");
					}

					break;
					
				case 3:
					list = model.listCategory();
					System.out.println("Categories : "+list);
					
					System.out.println("Enter the name");
					
					taskName = sc2.nextLine();
					
			
					

				case 4:
					
					list = model.listCategory();
					if(list != null) {
						System.out.println(list);
					}else
					{
						System.out.println("No Category, 1st create then call list");
					}

					break;
					
				case 5:
					
					System.out.println("Enter the Name");
					catName = sc2.nextLine();
					
					

					break;

				case 6:
					list = model.listCategory();
					System.out.println("Which one want to Export from these Categories : "+list);
					
					System.out.println("Enter Category Name from above list");
					catName = sc2.nextLine();
					
					TaskBean tasks = (TaskBean) model.getTasks(catName);
					
					System.out.println("Enter new Category Name");
					catName = sc2.nextLine();

					while (!TaskUtil.validateName(catName)) 
					{
						System.out.println("Category name must be single word, start with letter, only aplhanumeric.. enter another Category name");
						catName = sc2.nextLine();

					}
					
					if (model.checkCategoryExists(catName)) {
						System.out.println("catName name is already exists");
					} else {
						model.addTask(tasks, catName);
						System.out.println("Task Exported successfully");
					}
					
					break;
					
				case 7:
					System.out.println("Tata bye bye... miss you");
					sc1.close();
					sc2.close();
					break;

				default:
					System.out.println("Option not supported yet...");
					break;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
