package com.mogoroom.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.mapred.YARNRunner;
import org.apache.hadoop.mapreduce.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Service;

@Service("ScheduledTasks")
public class ScheduledTasks {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	@Qualifier("demoJob")
	private Job job;
	
	@Autowired
	@Qualifier("runner")
	JobRunner jobRunner;
	
    //@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
        
        try {
			job.waitForCompletion(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
