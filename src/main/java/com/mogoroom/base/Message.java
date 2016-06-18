package com.mogoroom.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {

	public static String SUCCESS = "success";
	public static String FAILURE = "failure";

	protected String status;
	protected String message;
	protected Map<String,Object> data;
	protected List<Map<String, Object>> dataList;

	public Message( ){

	}

	public Message(String status,String message){
		this.status=status;
		this.message=message;
	}

	public Message(String status,String message, Map<String,Object> data){
		this.status=status;
		this.message=message;
		this.data=data;
	}

	public static Message genSuccessMessage(){
		return new Message(SUCCESS,"ok");
	}
	
	public static Message genSuccessMessage(String message){
		return new Message(SUCCESS,message);
	}

	public static Message genFailMessage(String message){
		return new Message(FAILURE,message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setValue(String key,String value){
		if(data==null){
			data = new HashMap<String, Object>();
			data.put(key, value);
		}
		else {
			data.put(key, value);
		}
	}

	public void setValue(String key,Object value){
		if(data==null){
			data = new HashMap<String, Object>();
			data.put(key, value);
		}
		else {
			data.put(key, value);
		}
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
	public void addData(Map<String, Object> data) {
		if(this.dataList==null){
			this.dataList = new ArrayList<>();
		}
		this.dataList.add(data);
	}


}
