package com.mogoroom;

import java.util.List;

import org.apache.sqoop.client.SqoopClient;
import org.apache.sqoop.model.MConfig;
import org.apache.sqoop.model.MDriverConfig;
import org.apache.sqoop.model.MFromConfig;
import org.apache.sqoop.model.MInput;
import org.apache.sqoop.model.MJob;
import org.apache.sqoop.model.MLink;
import org.apache.sqoop.model.MLinkConfig;
import org.apache.sqoop.model.MSubmission;
import org.apache.sqoop.model.MToConfig;
import org.apache.sqoop.submission.counter.Counter;
import org.apache.sqoop.submission.counter.CounterGroup;
import org.apache.sqoop.submission.counter.Counters;
import org.apache.sqoop.validation.Message;
import org.apache.sqoop.validation.Status;


public class MysqlToHDFS {
	public static void main(String[] args) {
        sqoopTransfer();
    }
    public static void sqoopTransfer() {
        //初始化
        String url = "http://Master:12001/sqoop/";
        SqoopClient client = new SqoopClient(url);
        
        client.deleteLink(17);
        client.deleteLink(18);
        
        //创建一个源链接 JDBC
        long fromConnectorId = 4;
        MLink fromLink = client.createLink(fromConnectorId);
        fromLink.setName("JDBC connector4");
        fromLink.setCreationUser("test");
        
        MLinkConfig fromLinkConfig = fromLink.getConnectorLinkConfig();
        fromLinkConfig.getStringInput("linkConfig.connectionString").setValue("jdbc:mysql://121.40.121.133:3306/mogo_da");
        fromLinkConfig.getStringInput("linkConfig.jdbcDriver").setValue("com.mysql.jdbc.Driver");
        fromLinkConfig.getStringInput("linkConfig.username").setValue("root");
        fromLinkConfig.getStringInput("linkConfig.password").setValue("cde3xsw2zaq1");
        Status fromStatus = client.saveLink(fromLink);
        if(fromStatus.canProceed()) {
           System.out.println("创建JDBC Link成功，ID为: " + fromLink.getPersistenceId());
        } else {
           System.out.println("创建JDBC Link失败");
        }
        //创建一个目的地链接HDFS
        long toConnectorId = 3;
        MLink toLink = client.createLink(toConnectorId);
        toLink.setName("HDFS connector4");
        toLink.setCreationUser("test");
        MLinkConfig toLinkConfig = toLink.getConnectorLinkConfig();
        toLinkConfig.getStringInput("linkConfig.uri").setValue("hdfs://Master:9000/");
        Status toStatus = client.saveLink(toLink);
        if(toStatus.canProceed()) {
           System.out.println("创建HDFS Link成功，ID为: " + toLink.getPersistenceId());
        } else {
           System.out.println("创建HDFS Link失败");
        }
        
        //创建一个任务
        long fromLinkId = fromLink.getPersistenceId();
        long toLinkId = toLink.getPersistenceId();
        MJob job = client.createJob(fromLinkId, toLinkId);
        job.setName("MySQL to HDFS job2");
        job.setCreationUser("test");
        
        //设置源链接任务配置信息
        MFromConfig fromJobConfig = job.getFromJobConfig();
        fromJobConfig.getStringInput("fromJobConfig.schemaName").setValue("mogo_da");
        fromJobConfig.getStringInput("fromJobConfig.tableName").setValue("user_renter_portrait");
        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("id");
        MToConfig toJobConfig = job.getToJobConfig();
        toJobConfig.getStringInput("toJobConfig.outputDirectory").setValue("/tmp/sw/sqoop/sqoop1");
        MDriverConfig driverConfig = job.getDriverConfig();
        //driverConfig.getStringInput("throttlingConfig.numExtractors").setValue("3");

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
        System.out.println("Hadoop任务ID为 :" + submission.getExternalJobId());
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
        System.out.println("MySQL通过sqoop传输数据到HDFS统计执行完毕");
    }
    
    private static void printMessage(List<MConfig> configs) {
      for(MConfig config : configs) {
        List<MInput<?>> inputlist = config.getInputs();
        if (config.getValidationMessages() != null) {
         // print every validation message
         for(Message message : config.getValidationMessages()) {
          System.out.println("Config validation message: " + message.getMessage());
         }
        }
        for (MInput minput : inputlist) {
          if (minput.getValidationStatus() == Status.WARNING) {
           for(Message message : config.getValidationMessages()) {
            System.out.println("  Input Validation Warning: " + message.getMessage());
          }
        }
        else if (minput.getValidationStatus() == Status.ERROR) {
          for(Message message : config.getValidationMessages()) {
           System.out.println("Config Input Validation Error: " + message.getMessage());
          }
         }
        }
       }
    }
}
