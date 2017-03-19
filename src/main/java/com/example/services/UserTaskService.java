package com.example.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.domain.UserTask;
import com.example.domain.UserTaskRepository;

@Component
public class UserTaskService{
	
	@Autowired
	private UserTaskRepository rep;
	
	private Logger log = Logger.getLogger(UserTaskService.class);

	public void insertUserTask(UserTask userTask) {
		rep.save(userTask);
		log.info("Melumat elave eidildi");
	}
	
	public List<UserTask> getUserTasksByDate(String tarix, String userName) {
		List<UserTask> userTasks = rep.findUserTasksByDate(tarix, userName);
		return userTasks;
	}
	
	public List<UserTask> getUserTasksNotifByDate(String tarix, String userName) {
		List<UserTask> userTasks = rep.findUserTasksNotifByDate(tarix, userName);
		return userTasks;
	}
	
	public List<UserTask> getFindBusyTime(String sZaman, String eZaman, String userName, String tarix){
		List<UserTask> userTasks = rep.findBusyTime(sZaman, eZaman, userName, tarix);
		return userTasks;
	}
	
	public void deleteUserTask(Long taskId) {
		rep.delete(taskId);
		log.info("Tapşırıq silindi");
	}
}
