package com.mogoroom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mogoroom.hadoop.RootApplication;
import com.mogoroom.hadoop.service.RenterPortraintService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RootApplication.class)
@WebAppConfiguration
public class RenterPortraintServiceTest {

	@Autowired
	private RenterPortraintService service;
	
	@Test
	public void testRenterPortraint(){
		service.initRenterPortraintDayData();
		
		service.sumRenterPortraintDayDatas();
		
	}
	
}
