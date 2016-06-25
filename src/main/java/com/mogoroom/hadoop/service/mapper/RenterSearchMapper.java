package com.mogoroom.hadoop.service.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;


/**
 * @brief 租客搜索
 * @details 租客搜索
 * @author sw
 * @date 2016年6月20日
 */
public class RenterSearchMapper extends  Mapper<LongWritable, Text, Text, Text> {
	public static final String path = "renterSearch";
	/**
	 * param: #ID|手机号|地铁线|房间出租方式|品质|价格区间|室友类别|户型|房源类型|朝向|是否独卫|是否有阳台|是否有空调|搜索关键字|排序方式|上次登录时间
	 * @author sw
	 *
	 */
	@Override
    public void map(LongWritable key, Text value, Context context)  throws IOException, InterruptedException {
    	try{
            String line = value.toString();
            //StringTokenizer tokenizerArticle = new StringTokenizer(line, "\\|");
            
            String lines[] = line.split("\\|");
            String cellphone = lines[1];
            String id = lines[0];
            
            if(!id.equals("#ID")){
            	//LOGGER.info(lines);
                context.write(new Text(cellphone),value);
                
                Counter count = context.getCounter("Renter", "all_Count");
                count.increment(1);
                
            }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        
    }
}
