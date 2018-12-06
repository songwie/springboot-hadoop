package com.songwie.hadoop.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.songwie.hadoop.service.dataTrans.DataTransUtil;
import com.songwie.hadoop.service.dataTrans.HDFSUtil;
import com.songwie.hadoop.service.dataTrans.RenterSearchSaveToMysql;
import com.songwie.hadoop.service.mapper.RenterSearchMapper;
import com.songwie.hadoop.service.reducer.RenterSearchReducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.songwie.hadoop.base.MogoContants;
import com.songwie.hadoop.service.RenterPortraintService;

@Service("ScheduledTasks")
public class ScheduledTasks {
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat dateFormat2 = new SimpleDateFormat("MMddHHmmss");

	private static final Logger logger = Logger.getLogger(DataTransUtil.class);
	
	@Autowired
	@Qualifier("initJob")
	private Job initJob;
	
	@Autowired
    RenterSearchSaveToMysql renterSearchSaveToMysql;
	
	@Autowired
	private RenterPortraintService service;
	
    //@Scheduled(fixedRate = 5000)
    public void renterSearchTask() {
    	logger.info("task renterSearchTask start:" );
        try {
        	Job job = Job.getInstance(initJob.getConfiguration());
            job.setJobName("hadoop-renterSearch-" + dateFormat2.format(new Date()));
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -1);
            String dayPath = dateFormat.format(calendar.getTime());
            //String dayPath2 = dateFormat2.format(new Date());
            
            String inputPath = MogoContants.getHADOOP_INPUT_PATH() + "renterSearch/" + dayPath;
            List<String> inpuPaths = HDFSUtil.listFile(initJob.getConfiguration(),inputPath);
            
            String outPath = MogoContants.getHADOOP_OUTPUT_PATH() + "renterSearch/" + dayPath;
            HDFSUtil.rmdirs(initJob.getConfiguration(),outPath );
            
            for(String path : inpuPaths){
            	//job执行作业时输入和输出文件的路径
                FileInputFormat.addInputPath(job, new Path(path));
            	logger.info("task renterSearchTask job addInputPath ：" + path );
            }

            FileOutputFormat.setOutputPath(job, new Path(MogoContants.getHADOOP_OUTPUT_PATH() + "renterSearch/" + dayPath ));
        	logger.info("task renterSearchTask job setOutputPath ：" + outPath );


            //指定自定义的Mapper和Reducer作为两个阶段的任务处理类
            job.setMapperClass(RenterSearchMapper.class);
            job.setReducerClass(RenterSearchReducer.class);
            
            //设置最后输出结果的Key和Value的类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);
            
            
            //执行job，直到完成
            job.waitForCompletion(true);
        	
        	logger.info("task renterSearchTask start success" );
    	
			
		} catch (Exception e) {
			logger.error("renterSearchTask error ",e);
		}
    }
    
    public void synHdfsToMysql() {
    	logger.info("task synHdfsToMysql start:" );
        try {
        	renterSearchSaveToMysql.synHdfsDataToMysql();
        	
        	logger.info("task synHdfsToMysql start success" );
			
		} catch (Exception e) {
			logger.error("synHdfsToMysql error ",e);
		}
    }
    
    public void SumDayRenterSearchData() {
    	logger.info("task SumDayRenterSearchData start:" );
        try {
        	service.initRenterPortraintDayData();
    		
    		service.sumRenterPortraintDayDatas();
        	
        	logger.info("task SumDayRenterSearchData success" );
			
		} catch (Exception e) {
			logger.error("SumDayRenterSearchData error ",e);
		}
    }
    
    
}
