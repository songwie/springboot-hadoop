package com.mogoroom.hadoop.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class JsonUtil implements java.io.Serializable {

	private static final long serialVersionUID = -8872078079583695100L;
	private volatile static JsonUtil jsonUtil = null;

	private JsonUtil() {
	}

	public static JsonUtil getInstance() {
		if (jsonUtil == null) {
			synchronized (JsonUtil.class) {
				if (jsonUtil == null) {
					jsonUtil = new JsonUtil();
				}
			}
		}
		return jsonUtil;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> object2Map(Object obj,SerializerFeature... serializerFeature) {
		if(obj == null){
			return new HashMap<>();
		}
		String json = JsonUtil.getInstance().object2JSON(obj,SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteMapNullValue);
	    Map<String, Object>  map =  (Map<String, Object>) JSONObject.parse(json);
		
		return map;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> Objects2List(Object obj,SerializerFeature... serializerFeature) {
		if(obj == null){
			return new ArrayList<Map<String, Object>>();
		}
		String json = JsonUtil.getInstance().object2JSON(obj);
		List<Map<String, Object>> rows =  (List<Map<String, Object>>) JSONObject.parse(json);
		
		return rows;
	}

	public String object2JSON(Object obj,SerializerFeature... serializerFeature) {
		if(obj == null){
			return "{}";
		}
		return JSON.toJSONString(obj,serializerFeature);
	}
	
	public String object2JSON(Object obj) {
		if(obj == null){
			return "{}";
		}
		return JSON.toJSONString(obj,SerializerFeature.WriteDateUseDateFormat,SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteMapNullValue);
	}
	

	public <T>  T json2Object(String json,Class<T> clazz) {
		if(json == null || json.isEmpty()){
			return null;
		}
		return JSON.parseObject(json, clazz);
	}
	
	public <T> T json2Reference(String json, TypeReference<T> reference){
		if(json == null || json.isEmpty()){
			return null;
		}
		return JSON.parseObject(json, reference);
	}
	
	public <T> T json2Reference(String json, TypeReference<T> reference, Feature... features){
		if(json == null || json.isEmpty()){
			return null;
		}
		return JSON.parseObject(json, reference, features);
	}
}