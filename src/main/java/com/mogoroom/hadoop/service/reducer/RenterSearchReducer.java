package com.mogoroom.hadoop.service.reducer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;



/**
 * @brief 租客搜索
 * @details 租客搜索
 * @author sw
 * @date 2016年6月20日
 */
public class RenterSearchReducer extends Reducer<Text, Text, Text, Text> {
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private final DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");

	
	
	//#ID|手机号|地铁线|房间出租方式|品质|价格区间|室友类别|户型|房源类型|朝向|是否独卫|是否有阳台|是否有空调|搜索关键字|排序方式|上次登录时间
    @Override
    public void reduce(Text key, Iterable<Text> values,  Context context) throws IOException, InterruptedException {
    	try{
    		String cellphone = key.toString();
    		cellphone = cellphone.replaceAll(" ","");
    		//metroLine,rentType,roomType,roomPrice,roomMate,hourseType,roomSourceType,turnWay,isToilet,isBalcony,isAirConditioner,searchWord,sortType,lastLoginTime
    		String metroLine = "";
    		String rentType = "";
    		String roomType = "";
    		String roomPrice = "";
    		String roomMate = "";
    		String hourseType = "";
    		String roomSourceType = "";
    		String turnWay = "";
    		String isToilet = "";
    		String isBalcony = "";
    		String isAirConditioner = "";
    		String searchWord = "";
    		String sortType = "";
    		String lastLoginTime = "";
    		String distinct = "";
    		String tradeArea = "";

    		LinkedHashMap<String, Long> metroLineMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> rentTypeMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> roomTypeMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> roomMateMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> hourseTypeMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> roomSourceTypeMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> turnWayMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> isToiletMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> isBalconyMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> isAirConditionerMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> searchWordMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> sortTypeMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> distinctMap = new LinkedHashMap<>();
    		LinkedHashMap<String, Long> tradeAreaMap = new LinkedHashMap<>();

    		Calendar date = null;
    		long startPrice = 0;
    		long endPrice = 0;
    		
    		long avgStartPrice = 0;
    		long avgEndPrice = 0;
    		
    		String id = "";
    		
    		Iterator ite = values.iterator();
            while (ite.hasNext()) {
            	try{
            		String record = ite.next().toString();
                    String line = record.toString();
                    String lines[] = line.split("\\|");
                    id = lines[0];
                    cellphone = lines[1];
                    distinct = lines[2];
                    tradeArea = lines[3];
                    metroLine = lines[4];
            		rentType = lines[5];
            		roomType = lines[6];
            		roomPrice = lines[7];
            		roomMate = lines[8];
            		hourseType = lines[9];
            		roomSourceType = lines[10];
            		turnWay = lines[11];
            		isToilet = lines[12];
            		isBalcony = lines[13];
            		isAirConditioner = lines[14];
            		searchWord = lines[15];
            		sortType = lines[16];
            		lastLoginTime = lines[17];

                    context.getCounter("Renter", "count_"+cellphone).increment(1);
                     
                    metroLineMap = this.sortMap(this.increment(metroLineMap, metroLine));
                    metroLineMap = this.sortMap(this.increment(rentTypeMap, rentType));
                    roomTypeMap = this.sortMap(this.increment(roomTypeMap, roomType));
                    roomMateMap = this.sortMap(this.increment(roomMateMap, roomMate));
                    hourseTypeMap = this.sortMap(this.increment(hourseTypeMap, hourseType));
                    roomSourceTypeMap = this.sortMap(this.increment(roomSourceTypeMap, roomSourceType));
                    turnWayMap = this.sortMap(this.increment(turnWayMap, turnWay));
                    isToiletMap = this.sortMap(this.increment(isToiletMap, isToilet));
                    isBalconyMap = this.sortMap(this.increment(isBalconyMap, isBalcony));
                    isAirConditionerMap = this.sortMap(this.increment(isAirConditionerMap, isAirConditioner));
                    sortTypeMap = this.sortMap(this.increment(sortTypeMap, sortType));
                    searchWordMap = this.sortMap(this.increment(searchWordMap, searchWord));
                    distinctMap = this.sortMap(this.increment(distinctMap, distinct));
                    tradeAreaMap = this.sortMap(this.increment(tradeAreaMap, tradeArea));

                    if(date==null){
                    	date = Calendar.getInstance();
                        date.setTime(dateFormat.parse(lastLoginTime));
                    }else{
                    	Calendar date2 = Calendar.getInstance();
                    	date2.setTime(dateFormat.parse(lastLoginTime));
                    	
                    	date = date.compareTo(date2)<0 ? date2 : date;
                    }
                    String prices[] = roomPrice.split(",");
                    startPrice += Integer.valueOf(prices[0]);
                    endPrice += Integer.valueOf(prices[1]);
                    
                    avgStartPrice = startPrice/context.getCounter("Renter", "count_"+cellphone).getValue();
                    avgEndPrice = endPrice/context.getCounter("Renter", "count_"+cellphone).getValue();

            	}catch(Exception e){
            		e.getStackTrace();
            		context.getCounter("Renter", "Exception_"+cellphone).increment(1);
            	}
            }
            
            //createTime,userId,cellphone,district,districtCount,tradeArea,tradeAreaCount,metroLine,metroLineCount,rentType,rentTypeCount,
            //roomType,roomTypeCount,roomPrice,roomMate,roomMateCount,hourseType,hourseTypeCount,roomSourceType,roomSourceTypeCount,
            //turnWay,turnWayCount,isToilet,isToiletCount,isBalcony,isBalconyCount,isAirConditioner,isAirConditionerCount,searchWord,searchWordCount,sortType,sortTypeCount,lastLoginTime
            String result = 
            		cellphone + dateFormat2.format(new Date()) + ","
            		+ cellphone + ","  
            		+ dateFormat.format(new Date()) + ","
            		+ id + "," 
            		//+ context.getCounter("Renter", "count_"+cellphone).getValue() + ","
                    + this.getTopMapKey(distinctMap) + "," + this.getTopMapValue(distinctMap) + ","
                    + this.getTopMapKey(tradeAreaMap) + "," + this.getTopMapValue(tradeAreaMap) + ","
                    + this.getTopMapKey(metroLineMap) + "," + this.getTopMapValue(metroLineMap) + ","
                    + this.getTopMapKey(rentTypeMap) + "," + this.getTopMapValue(rentTypeMap) + ","
                    + this.getTopMapKey(roomTypeMap) + "," + this.getTopMapValue(roomTypeMap) + ","
                    + avgStartPrice + "," + avgEndPrice + ","
                    + this.getTopMapKey(roomMateMap) + "," + this.getTopMapValue(roomMateMap) + ","
                    + this.getTopMapKey(hourseTypeMap) + "," + this.getTopMapValue(hourseTypeMap) + ","
                    + this.getTopMapKey(roomSourceTypeMap) + "," + this.getTopMapValue(roomSourceTypeMap) + ","
                    + this.getTopMapKey(turnWayMap) + "," + this.getTopMapValue(turnWayMap) + ","
                    + this.getTopMapKey(isToiletMap) + "," + this.getTopMapValue(isToiletMap) + ","             
                    + this.getTopMapKey(isBalconyMap) + "," + this.getTopMapValue(isBalconyMap) + ","  
                    + this.getTopMapKey(isAirConditionerMap) + "," + this.getTopMapValue(isAirConditionerMap) + ","  
                    + this.getTopMapKey(searchWordMap) + "," + this.getTopMapValue(searchWordMap) + ","
                    + this.getTopMapKey(sortTypeMap) + "," + this.getTopMapValue(sortTypeMap) + "," 
                    + dateFormat.format(date.getTime());

           context.write(null, new Text(result));
      
    	}catch(Exception e){
    		context.getCounter("Renter", "Exception").increment(1);
    	}
    }
    
    private Map<String, Long> increment(Map<String, Long> map,String key){
    	map.put(key,map.containsKey(key) ? map.get(key)+1 : 1);
    	return map;
    }
    
    private LinkedHashMap<String, Long> sortMap(Map<String, Long> oldMap) {  
        ArrayList<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String, Long>>(oldMap.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {  
  
            @Override  
            public int compare(Entry<java.lang.String, Long> arg0,  Entry<java.lang.String, Long> arg1) {  
            	int v = 0;
            	if(arg0.getValue() - arg1.getValue() < 0){
            		v = -1;
            	}else if(arg0.getValue() - arg1.getValue() > 0){
            		v =  1;
            	}
            	return v;
            }  
        });  
        LinkedHashMap<String, Long> newMap = new LinkedHashMap<String, Long>();  
        for (int i = 0; i < list.size(); i++) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        return newMap;  
    }  
    
    private String getTopMapKey(LinkedHashMap<String, Long> map){
    	String key = "";
    	for(java.util.Map.Entry<String, Long> entry : map.entrySet()) {
    		key =  entry.getKey();
    		break;
    	}
    	return key;
    }
    private Long getTopMapValue(LinkedHashMap<String, Long> map){
    	Long value = 1 * 1L;
    	for(java.util.Map.Entry<String, Long> entry : map.entrySet()) {
    		value =  entry.getValue();
    		break;
    	}
    	return value;
    }
    
}
