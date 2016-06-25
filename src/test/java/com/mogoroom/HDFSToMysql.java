package com.mogoroom;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

public class HDFSToMysql {
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args)throws Exception {
        sqoopTransfer();
    }
    public static void sqoopTransfer() throws Exception {
    	//初始化
        String url = "http://Master:12001/sqoop/";
        SqoopClient client = new SqoopClient(url);
    
        List<MLink> links = client.getLinks();
        
        //创建一个源链接HDFS
        long fromConnectorId = 3;
        MLink fromLink = null;
        boolean exisFromLink = false;
        for(MLink link : links){
        	System.err.println(link.getName()+","+link.getPersistenceId());
        	String name = ("HDFS connector test" + dateFormat.format(new Date()));
       	    if(name.equals(link.getName())){
       	    	fromLink = link;
       	    	exisFromLink = true;
       	    	break;
       	    }
        }
        Status fromStatus = null;
        if(exisFromLink==false){
            fromLink = client.createLink(fromConnectorId);
            fromLink.setName("HDFS connector test" + dateFormat.format(new Date()));
            fromLink.setCreationUser("test");
            MLinkConfig fromLinkConfig = fromLink.getConnectorLinkConfig();
            fromLinkConfig.getStringInput("linkConfig.uri").setValue("hdfs://Master:9000/");
            
            fromStatus = client.saveLink(fromLink);
        }else{
        	fromStatus = client.updateLink(fromLink);
        }
        
        if(fromStatus.canProceed()) {
           System.out.println("创建HDFS Link成功，ID为: " + fromLink.getPersistenceId());
        } else {
           System.out.println("创建HDFS Link失败");
        }
        
        //创建一个目标链接 JDBC
        long toConnectorId = 4;
        MLink toLink = client.createLink(toConnectorId);
        toLink.setName("JDBC connector15" + UUID.randomUUID().toString().toString().substring(0, 10));
        toLink.setCreationUser("test");
        
        MLinkConfig toLinkConfig = toLink.getConnectorLinkConfig();
        toLinkConfig.getStringInput("linkConfig.connectionString").setValue("jdbc:mysql://121.40.121.133:3306/mogo_da");
        toLinkConfig.getStringInput("linkConfig.jdbcDriver").setValue("com.mysql.jdbc.Driver");
        toLinkConfig.getStringInput("linkConfig.username").setValue("root");
        toLinkConfig.getStringInput("linkConfig.password").setValue("cde3xsw2zaq1");

        Status toStatus = client.saveLink(toLink);
        if(toStatus.canProceed()) {
           System.out.println("创建JDBC Link成功，ID为: " + fromLink.getPersistenceId());
        } else {
           System.out.println("创建JDBC Link失败");
        }
        
        //创建一个任务
        long fromLinkId = fromLink.getPersistenceId();
        long toLinkId = toLink.getPersistenceId();
        MJob job = client.createJob(fromLinkId, toLinkId);
        job.setName("HDFS to MySQL job15" + UUID.randomUUID().toString().substring(0, 10));
        job.setCreationUser("admln");
        
        //设置源链接任务配置信息
        MFromConfig fromJobConfig = job.getFromJobConfig();
        //fromJobConfig.getStringInput("fromJobConfig.inputDirectory").setValue("/tmp/sw/input/1.txt");
        //fromJobConfig.getStringInput("fromJobConfig.inputDirectory").setValue("/tmp/sw/input/sqooptodb.txt");
        fromJobConfig.getStringInput("fromJobConfig.inputDirectory").setValue("/tmp/sw/output/renterSearch/part-r-00000");
        
        
        //创建目的地链接任务配置信息
        MToConfig toJobConfig = job.getToJobConfig();
        //toJobConfig.getStringInput("toJobConfig.schemaName").setValue("mogo_da");
        toJobConfig.getStringInput("toJobConfig.tableName").setValue("user_renter_portrait_day");
        toJobConfig.getStringInput("toJobConfig.columns").setValue("id,cellphone,createTime,userId,district,districtCount,tradeArea,tradeAreaCount,metroLine,metroLineCount,rentType,rentTypeCount,roomType,roomTypeCount,roomPrice,roomMate,roomMateCount,hourseType,hourseTypeCount,roomSourceType,roomSourceTypeCount,turnWay,turnWayCount,isToilet,isToiletCount,isBalcony,isBalconyCount,isAirConditioner,isAirConditionerCount,searchWord,searchWordCount,sortType,sortTypeCount,lastLoginTime");
        //toJobConfig.getStringInput("toJobConfig.tableName").setValue("test");
        //toJobConfig.getStringInput("toJobConfig.columns").setValue("id,address,name");
        //toJobConfig.getStringInput("toJobConfig.sql").setValue("insert into test(name,address) values(?,?)");
        
        // set the driver config values
        //MDriverConfig driverConfig = job.getDriverConfig();
        //driverConfig.getStringInput("throttlingConfig.numExtractors").setValue("3");//这句还没弄明白
        Status status = client.saveJob(job);
        if(status.canProceed()) {
         System.out.println("JOB创建成功，ID为: "+ job.getPersistenceId());
        } else {
         System.out.println("JOB创建失败。");
        }
        
        //启动任务
        long jobId = job.getPersistenceId();
        MSubmission submission = client.startJob(jobId);
        System.out.println("JOB提交状态为 : " + submission.getStatus());
        while(submission.getStatus().isRunning() && submission.getProgress() != -1) {
          System.out.println("进度 : " + String.format("%.2f %%", submission.getProgress() * 100));
          //三秒报告一次进度
          try {
            Thread.sleep(3000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("JOB执行结束... ...");
        System.out.println("Hadoop任务ID为 :" + submission.getJobId());
        Counters counters = submission.getCounters();
        if(counters != null) {
          System.out.println("计数器:");
          for(CounterGroup group : counters) {
            System.out.print("\t");
            System.out.println(group.getName());
            for(Counter counter : group) {
              System.out.print("\t\t");
              System.out.print(counter.getName());
              System.out.print(": ");
              System.out.println(counter.getValue());
            }
          }
        }
        if(submission.getError() != null) {
          System.out.println("JOB执行异常，异常信息为 : " +submission.getError());
        }
        System.out.println("HDFS通过sqoop传输数据到MySQL统计执行完毕");
        
        Thread.sleep(100000000);
    }
    
}
