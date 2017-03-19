package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.domain.UserTask;
import com.example.services.UserService;
import com.example.services.UserTaskService;
import com.example.utilities.UtilBsn;
import com.google.gson.Gson;


@RestController
public class MyRestController {
	@Autowired
	private UserService userSer;
	
	@Autowired
	private UserTaskService userTaskSer;
	
	SimpleDateFormat chFD = new SimpleDateFormat("yyyy/MM/dd");
	SimpleDateFormat chFT = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
	@RequestMapping(value="/hellorest", method = RequestMethod.POST)
	public String helloRestPost(@RequestBody User user){
		String password = user.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		user.setPassword(hashedPassword);		
		userSer.insertUser(user);
		return "{\"hellorest\":\"salam\"}";
	}
	
	@RequestMapping(value="/newtask", method = RequestMethod.POST)
	public String newTaskPost(@RequestBody UserTask userTask){
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		response.put("RESULT", "OK");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userTask.setUsername(auth.getName());
		Date notifTime = UtilBsn.addMinutesToDate(-userTask.getNotif(), userTask.getStartTime());
		userTask.setNotifTime(notifTime);
		String sStart = chFT.format(userTask.getStartTime());
		String sEnd = chFT.format(userTask.getEndTime());
		String sTarix = chFD.format(userTask.getEndTime());
		
		List<UserTask> taskList = userTaskSer.getFindBusyTime(sStart, sEnd, auth.getName(), sTarix);
		if(userTask.getTaskId() == null){
		   if(taskList.isEmpty()){
	           userTaskSer.insertUserTask(userTask);
	        } else {
	           response.put("RESULT", "ERROR");
	           response.put("MESSAGE", "Təyin edilən aralıqda başqa tapşırıq var");
	        }
		}else if(taskList.isEmpty()){
           userTaskSer.insertUserTask(userTask);
        }else if(taskList.size()==1){
		   if(taskList.get(0).getTaskId() == userTask.getTaskId()){
		      userTaskSer.insertUserTask(userTask);
		   } else{
		      response.put("RESULT", "ERROR");
              response.put("MESSAGE", "Təyin edilən aralıqda baqa tapşırıq var");
		   }
		}else{
		   response.put("RESULT", "ERROR");
           response.put("MESSAGE", "Təyin edilən aralıqda baqa tapşırıq var");
		}
		
		for(UserTask task:taskList){
			Gson gs = new Gson();
			arr.put(new JSONObject(gs.toJson(task, UserTask.class)));
		}
		response.put("USERTASKS", arr);
		return response.toString();
	}
	
	@RequestMapping(value="/getusertaskbydate", method = RequestMethod.POST)
	public List<UserTask> getUserTaskByDate(@RequestParam("tarix") String tarix){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
	    List<UserTask> userTasks = new ArrayList<UserTask>();
		userTasks = userTaskSer.getUserTasksByDate(tarix, username);
		return userTasks;
	}
	
	@RequestMapping(value="/getusertasksnotifbydate", method = RequestMethod.POST)
	public List<UserTask> getUserTasksNotifByDate(@RequestParam("tarix") String tarix){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
	    List<UserTask> userTasks = new ArrayList<UserTask>();
		userTasks = userTaskSer.getUserTasksNotifByDate(tarix, username);
		return userTasks;
	}
	
	@RequestMapping(value="/deleteusertask/{taskId}", method = RequestMethod.DELETE)
	public String deleteUserTask(@PathVariable("taskId") long taskId){
		JSONObject response = new JSONObject();
		response.put("RESULT", "OK");
		userTaskSer.deleteUserTask(taskId);
		return response.toString();
	}
	
	@RequestMapping(value="/getusertaskbycurrentdate", method = RequestMethod.POST)
	public List<UserTask> getUserTaskByCurrentDate(){
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
	    List<UserTask> userTasks = new ArrayList<UserTask>();
	    Date tarix = new Date();
		userTasks = userTaskSer.getUserTasksByDate(chFD.format(tarix), username);
		for(UserTask userTask:userTasks){
			
			
			
			Gson gs = new Gson();
			arr.put(new JSONObject(gs.toJson(userTask, UserTask.class)));
		}
		response.put("USERTASKS", arr);
		/*if(utList.isEmpty()){
			userTaskSer.insertUserTask(userTask);
		}else{
			response.put("RESULT", "ERROR");
		}*/
		
		
		return userTasks;
	}
	
	@RequestMapping(value="/hellorest", method = RequestMethod.GET)
	public String helloRestGet( @RequestParam("username") String username,
								@RequestParam("password") String pass,
								@RequestParam("email") String email,
								@RequestParam("enabled") int enabled){
		User user = new User();
		String password = pass;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		user.setUsername(username);
		user.setPassword(hashedPassword);	
		user.setEmail(email);
		user.setEnabled(enabled);
		userSer.insertUser(user);
		return "{\"hellorest\":\"salam\"}";
	}
	
	@RequestMapping(value="/loginsuccessful", method = RequestMethod.GET)
	public User loginsuccessful(){
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String username = auth.getName();
       User user = userSer.findUserByUsername(username);
       user.setPassword("");
	   return user;
	}
	
	@RequestMapping(value="/logoutsuccessful", method = RequestMethod.GET)
	public String logoutsuccessful(){
		JSONObject response = new JSONObject();
		response.put("RESULT", "OK");
		response.put("200", "Sistemdən ayrıldınız.");
	   return response.toString();
	}
	
	@RequestMapping(value="/status403", method = RequestMethod.GET)
	public String status403(){
		JSONObject response = new JSONObject();
		response.put("RESULT", "OK");
		response.put("403", "Bura daxil olmağa icazəniz yoxdur.");
	   return response.toString();
	}
}
