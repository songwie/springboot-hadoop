package com.songwie.hadoop.service.dataTrans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.sqoop.model.MJob;
import org.apache.sqoop.model.MLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songwie.hadoop.base.MogoContants;
import com.songwie.hadoop.service.mapper.RenterSearchMapper;

@Service
public class RenterSearchSaveToMysql {
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private Configuration configuration;

	@Autowired
	private DataTransUtil dataTransUtil;
	
	public void synHdfsDataToMysql(){
		MLink fromLink = dataTransUtil.instanceHdfsConnector();
		MLink toLink = dataTransUtil.instanceMysqlConnector();
		
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        String dayPath = dateFormat.format(calendar.getTime());
		
		//创建一个任务
		MJob job = dataTransUtil.newJob(fromLink, toLink, "synHdfsDataToMysql");
        
		String table = MogoContants.getSQOOP_MYSQL_RENTER_SEARCH_TABLE();
		String columns = MogoContants.getSQOOP_MYSQL_RENTER_SEARCH_COLUMN();
		
		String inputDirectory = MogoContants.getHADOOP_OUTPUT_PATH();
		String inputPath = inputDirectory + RenterSearchMapper.path + "/" + dayPath;
        List<String> inpuPaths = HDFSUtil.listFile(configuration,inputPath);
        
        List<String> inputs = new LinkedList<>();
        for(String jobPath : inpuPaths){
        	if(jobPath.indexOf("part-")!=-1){
        		inputs.add(jobPath);
        	}
        }
        dataTransUtil.saveJob(job,inputs,table,columns);
        
		dataTransUtil.startJob(job);
	}
	
	
}
