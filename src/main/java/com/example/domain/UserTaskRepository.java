package com.example.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTaskRepository extends CrudRepository<UserTask, Long> {
	public UserTask findByTaskId(Long taskId);
	public List<UserTask> findByNotif(int notif);
	public List<UserTask> findByTaskName(String taskName);
	public List<UserTask> findByTaskContent(String taskContent);
    public List<UserTask> findByUsername(String username);
   
    @Query("SELECT ut FROM UserTask ut WHERE TO_CHAR(ut.startTime, 'YYYY/MM/DD') = ?1 and userName = ?2")
    public List<UserTask> findUserTasksByDate(String tarix, String userName);
    
    @Query("SELECT ut FROM UserTask ut WHERE TO_CHAR(ut.notifTime, 'YYYY/MM/DD') = ?1 and userName = ?2")
    public List<UserTask> findUserTasksNotifByDate(String tarix, String userName);
    
    public List<UserTask> findByStartTime(Date startTime);
    public List<UserTask> findByEndTime(Date endTime);
        
    @Query("SELECT tar FROM UserTask tar WHERE tar.username = ?3 AND TO_CHAR(tar.startTime, 'YYYY/MM/DD') = ?4 AND ((TO_CHAR(tar.startTime, 'YYYY/MM/DD HH24:MI') <= ?1 AND TO_CHAR(tar.endTime, 'YYYY/MM/DD HH24:MI') > ?1 ) OR (TO_CHAR(tar.startTime, 'YYYY/MM/DD HH24:MI') < ?2 AND TO_CHAR(tar.endTime, 'YYYY/MM/DD HH24:MI') >= ?2 ) OR (TO_CHAR(tar.startTime, 'YYYY/MM/DD HH24:MI') > ?1 AND TO_CHAR(tar.endTime, 'YYYY/MM/DD HH24:MI') < ?2 ))")
    public List<UserTask> findBusyTime(String sZaman, String eZaman, String userName, String tarix);
    
    //@Query("SELECT NUT FROM (SELECT taskId, DATEADD('minute', -notif, startTime) newTime, startTime, taskName, taskContent, notif  FROM UserTask WHERE username=?2) NUT WHERE TO_CHAR(NUT.newTime, 'YYYY/MM/DD')=?1 ORDER BY NUT.newTime")
   // @Query("SELECT ut.taskId, DATEADD('minute', - ut.notif, ut.startTime) as newTime, ut.startTime, ut.taskName, ut.taskContent, ut.notif FROM UserTask ut WHERE ut.username = ?2 AND TO_CHAR(DATEADD(Minute, - ut.notif, ut.startTime ), 'YYYY/MM/DD') = ?1")
    //public List<Object[]> findNotifTask(String tarix, String userName);
}