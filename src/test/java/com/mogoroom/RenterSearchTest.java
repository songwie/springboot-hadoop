package com.mogoroom;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RenterSearchTest {
	public static void main(String[] args) throws Exception {

        //输入路径
        String dst = "hdfs://Master:9000//tmp/sw/input/renterSearch/renter_search.txt";
        //输出路径，必须是不存在的，空文件加也不行。
        String dstOut = "hdfs://Master:9000/tmp/sw/output/search8";
        Configuration hadoopConfig = new Configuration();
        
        hadoopConfig.set("mapreduce.framework.name", "yarn");
        hadoopConfig.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName() );
        //hadoopConfig.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName() );
        hadoopConfig.set("mapreduce.app-submission.cross-platform", "true");
        hadoopConfig.set("mapreduce.remote.os", "Linux");
        hadoopConfig.set("yarn.resourcemanager.address",  "Master:8032" );
        hadoopConfig.set("hadoop.tmp.dir", "/data/hadoop-2.7.2/tmp");
        hadoopConfig.set("mapreduce.jobtracker.staging.root.dir", "/data/hadoop-2.7.2/tmp");

        Job job = Job.getInstance(hadoopConfig);
        job.setJobName("test");

        //如果需要打成jar运行，需要下面这句
        job.setJarByClass(MapReduceDemo.class);

        //job执行作业时输入和输出文件的路径
        FileInputFormat.addInputPath(job, new Path(dst));
        FileOutputFormat.setOutputPath(job, new Path(dstOut));

        //指定自定义的Mapper和Reducer作为两个阶段的任务处理类
        job.setMapperClass(com.mogoroom.hadoop.service.mapper.RenterSearchMapper.class);
        job.setReducerClass(com.mogoroom.hadoop.service.reducer.RenterSearchReducer.class);
        
        //设置最后输出结果的Key和Value的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        //执行job，直到完成
        job.waitForCompletion(true);
        System.out.println("Finished");
    }
}
