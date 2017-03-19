package com.example;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.UserTask;
import com.example.services.UserTaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalendarTaskApplicationTests {

	@Test
	public void contextLoads() {
		UserTaskService uts = new UserTaskService();
		List<UserTask> utList =  uts.getFindBusyTime("2017/03/08 01:25 AM", "2017/03/08 01:25 AM", "celal", "2017/03/08");
		System.out.println("------------- "+ utList.size());
	}

}
