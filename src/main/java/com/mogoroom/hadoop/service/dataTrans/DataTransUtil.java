package com.mogoroom.hadoop.service.dataTrans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.sqoop.client.SqoopClient;
import org.apache.sqoop.model.MFromConfig;
import org.apache.sqoop.model.MJob;
import org.apache.sqoop.model.MLink;
import org.apache.sqoop.model.MLinkConfig;
import org.apache.sqoop.model.MSubmission;
import org.apache.sqoop.model.MToConfig;
import org.apache.sqoop.submission.counter.Counter;
import org.apache.sqoop.submission.counter.CounterGroup;
import org.apache.sqoop.submission.counter.Counters;
import org.apache.sqoop.validation.Status;
import org.springframework.stereotype.Service;

import com.mogoroom.hadoop.base.MogoContants;

@Service
public class DataTransUtil {
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat dateFormat2 = new SimpleDateFormat("MMddHHmmss");

	private static SqoopClient client = null;
	private static final Logger logger = Logger.getLogger(DataTransUtil.class);
	
	private static final String HDFS_LINK_NAME = "HdfsLinkConnector";
	private static final String MYSQL_LINK_NAME = "MysqlLinkConnector";
	
	public static synchronized SqoopClient initSqoopClient(){
		String sqoopAdress = MogoContants.getSQOOP_HOST();
		if(client==null){
			client = new SqoopClient(sqoopAdress);
		}
		
		return client;
	}
	
	public synchronized MLink instanceHdfsConnector(){
		long connectorId = Long.valueOf(MogoContants.getSQOOP_HDFS_COONECTOR_ID());
		
        List<MLink> links = initSqoopClient().getLinks();
        
        MLink link = null;
        boolean exisFromLink = false;
        for(MLink link2 : links){
        	//logger.info(link2.getName()+","+link2.getPersistenceId());
       	    if(HDFS_LINK_NAME.equals(link2.getName())){
       	    	link = link2;
       	    	exisFromLink = true;
       	    	break;
       	    }
        }
        Status status = null;
        if(exisFromLink==false){
        	link = initSqoopClient().createLink(connectorId);
        	link.setName(HDFS_LINK_NAME);
        	link.setCreationUser("mogoroom");
            MLinkConfig toLinkConfig = link.getConnectorLinkConfig();
            toLinkConfig.getStringInput("linkConfig.uri").setValue(MogoContants.getHADOOP_HOST());
            
            status = initSqoopClient().saveLink(link);
        }else{
        	status = initSqoopClient().updateLink(link);
        }
        
        if(status.canProceed()) {
        	logger.info("创建HDFS Link成功，ID为: " + link.getPersistenceId());
        } else {
        	logger.error("创建HDFS Link失败");
        	return null;
        }
        
        return link;
	}
	
	public synchronized MLink instanceMysqlConnector(){
        long connectorId = Long.valueOf(MogoContants.getSQOOP_MYSQL_COONECTOR_ID());
		
        List<MLink> links = initSqoopClient().getLinks();
        
        MLink link = null;
        boolean exisFromLink = false;
        for(MLink link2 : links){
        	//logger.info(link2.getName()+","+link2.getPersistenceId());
       	    if(MYSQL_LINK_NAME.equals(link2.getName())){
       	    	link = link2;
       	    	exisFromLink = true;
       	    	break;
       	    }
        }
        Status status = null;
        if(exisFromLink==false){
        	link = initSqoopClient().createLink(connectorId); 
        	link.setName(MYSQL_LINK_NAME);
        	link.setCreationUser("mogoroom");
        	
        	MLinkConfig toLinkConfig = link.getConnectorLinkConfig();
            toLinkConfig.getStringInput("linkConfig.connectionString").setValue(MogoContants.getSQOOP_MYSQL_URL());
            toLinkConfig.getStringInput("linkConfig.jdbcDriver").setValue(MogoContants.getSQOOP_MYSQL_DRIVER_NAME());
            toLinkConfig.getStringInput("linkConfig.username").setValue(MogoContants.getSQOOP_MYSQL_USERNAME());
            toLinkConfig.getStringInput("linkConfig.password").setValue(MogoContants.getSQOOP_MYSQL_PASSWORD());
            
            status = initSqoopClient().saveLink(link);
        }else{
        	MLinkConfig toLinkConfig = link.getConnectorLinkConfig();
        	toLinkConfig.getStringInput("linkConfig.connectionString").setValue(MogoContants.getSQOOP_MYSQL_URL());
            toLinkConfig.getStringInput("linkConfig.jdbcDriver").setValue(MogoContants.getSQOOP_MYSQL_DRIVER_NAME());
            toLinkConfig.getStringInput("linkConfig.username").setValue(MogoContants.getSQOOP_MYSQL_USERNAME());
            toLinkConfig.getStringInput("linkConfig.password").setValue(MogoContants.getSQOOP_MYSQL_PASSWORD());
        	status = initSqoopClient().updateLink(link);
        }
        
        if(status.canProceed()) {
        	logger.info("创建Mysql Link成功，ID为: " + link.getPersistenceId());
        } else {
        	logger.error("创建Mysql Link失败");
        	return null;
        }
        
        return link;
	}
	
	public synchronized MJob newJob(MLink fromLink,MLink toLink,String jobName){
		 //创建一个任务
        long fromLinkId = fromLink.getPersistenceId();
        long toLinkId = toLink.getPersistenceId();
        
        MJob job = client.createJob(fromLinkId, toLinkId);
        job.setName(jobName + dateFormat2.format(new Date()));
        job.setCreationUser("mogoroom");
        
        return job;
	}
	
	public synchronized MJob saveJob(MJob job,List<String> jobPaths,String tableName,String columns){
		//设置源链接任务配置信息
        MFromConfig fromJobConfig = job.getFromJobConfig();
        for(String jobPath : jobPaths){
            fromJobConfig.getStringInput("fromJobConfig.inputDirectory").setValue(jobPath);
        	logger.info("JOB inputDirectory: " + jobPath);
        }
        
        //创建目的地链接任务配置信息
        MToConfig toJobConfig = job.getToJobConfig();
        //toJobConfig.getStringInput("toJobConfig.schemaName").setValue("mogo_da");
        toJobConfig.getStringInput("toJobConfig.tableName").setValue(tableName);
        toJobConfig.getStringInput("toJobConfig.columns").setValue(columns);
		
		Status status = client.saveJob(job);
        if(status.canProceed()) {
        	logger.info("JOB tableName: " + tableName);
        	logger.info("JOB columns: " + columns);

        	logger.info("JOB创建成功，ID为: " + job.getPersistenceId());
        } else {
        	logger.info("JOB创建失败。");
        }
        return job;
	}
	
	public void startJob(MJob job){
		//启动任务
        long jobId = job.getPersistenceId();
        MSubmission submission = client.startJob(jobId);
        logger.info("JOB提交状态为 : " + submission.getStatus());
        
        while(submission.getStatus().isRunning() && submission.getProgress() != -1) {
          logger.info("进度 : " + String.format("%.2f %%", submission.getProgress() * 100));
          //三秒报告一次进度
          try {
            Thread.sleep(3000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        logger.info("JOB执行结束... ...");
        logger.info("Hadoop任务ID为 :" + submission.getJobId());
        Counters counters = submission.getCounters();
        if(counters != null) {
          logger.info("计数器:");
          for(CounterGroup group : counters) {
            logger.info("\t");
            logger.info(group.getName());
            for(Counter counter : group) {
              logger.info("\t\t");
              logger.info(counter.getName());
              logger.info(": ");
              logger.info(counter.getValue());
            }
          }
        }
        if(submission.getError() != null) {
          logger.info("JOB执行异常，异常信息为 : " +submission.getError());
        }
        logger.info("HDFS通过sqoop传输数据统计执行完毕");
	}
	
	
}
