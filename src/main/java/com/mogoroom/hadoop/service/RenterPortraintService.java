package com.mogoroom.hadoop.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mogoroom.hadoop.dao.entity.UserRenterPortrait;
import com.mogoroom.hadoop.dao.interfaces.UserRenterPortraintDao;
import com.mogoroom.hadoop.dao.interfaces.UserRenterPortraintDayDao;
import com.mogoroom.hadoop.service.dataTrans.DataTransUtil;

@Service
public class RenterPortraintService {
	private static final Logger logger = Logger.getLogger(DataTransUtil.class);

	@Autowired
	private UserRenterPortraintDao renterPortraintDao;
	
	@Autowired 
	private UserRenterPortraintDayDao renterPortraintDayDao;
	
	
	public void sumRenterPortraintDayDatas(){
		logger.info("2. insert 汇总每日明细");
		long start = 0;
		long limit = 500;
		
		long total = renterPortraintDao.queryTotalSumUserRenterPortrait();
		
		List<UserRenterPortrait> list = renterPortraintDao.querySumUserRenterPortrait(start,limit);
		while(start<total){
			//2. insert 汇总每日明细
			try {
				sumRenterPortraintDayData(list);
			} catch (Exception e) {
				logger.info("2. insert 汇总每日明细 error:",e);
			}finally {
				start = start + limit;
			}
		}
		
		
	}
	
	@Transactional
	public void initRenterPortraintDayData(){
		logger.info("1. insert 用户画像");
		//1. insert 用户画像
		renterPortraintDao.updateUserRenterPortrait();
	}
	
	@Transactional
	public void sumRenterPortraintDayData(List<UserRenterPortrait> list){
		renterPortraintDao.save(list);
	}

	public UserRenterPortrait getOneRenter(String renterId) {
		return renterPortraintDao.findOne(Integer.valueOf(renterId));
	}
	
}
