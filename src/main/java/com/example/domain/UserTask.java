package com.example.domain;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "USER_TASKS")
public class UserTask {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="TASK_ID")
    private Long taskId;

	@Column(name = "USERNAME")
    private String username;   

	@Column(name = "TASK_NAME")
    private String taskName;   

	@Column(name = "TASK_CONTENT")
    private String taskContent;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	@Column(name ="START_TIME")
	private Date startTime;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")    
	@Column(name ="END_TIME")
	private Date endTime;
	
	@Column(name ="NOTIF")
	private int notif;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")    
	@Column(name ="NOTIF_TIME")
	private Date notifTime;
	
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getNotif() {
		return notif;
	}

	public void setNotif(int notif) {
		this.notif = notif;
	}

	public Date getNotifTime() {
		return notifTime;
	}

	public void setNotifTime(Date notifTime) {
		this.notifTime = notifTime;
	}

	public UserTask(){
	}

	@Override
	public String toString() {
		return "UserTask [taskId=" + taskId + ", username=" + username + ", taskName=" + taskName + ", taskContent="
				+ taskContent + ", startTime=" + startTime + ", endTime=" + endTime + ", notif=" + notif + ", notifTime=" + notifTime + "]";
	}

	public UserTask(Long taskId, String username, String taskName, String taskContent, Date startTime, Date endTime,
			int notif, Date notifTime) {
		super();
		this.taskId = taskId;
		this.username = username;
		this.taskName = taskName;
		this.taskContent = taskContent;
		this.startTime = startTime;
		this.endTime = endTime;
		this.notif = notif;
		this.notifTime = notifTime;
	}

	public UserTask(UserTask userTask) {
		super();
		this.taskId = userTask.taskId;
		this.username = userTask.username;
		this.taskName = userTask.taskName;
		this.taskContent = userTask.taskContent;
		this.startTime = userTask.startTime;
		this.endTime = userTask.endTime;
		this.notif = userTask.notif;
		this.notifTime = userTask.notifTime;
	}
}
