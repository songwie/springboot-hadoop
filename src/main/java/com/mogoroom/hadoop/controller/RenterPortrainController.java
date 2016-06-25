package com.mogoroom.hadoop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mogoroom.hadoop.base.JsonUtil;
import com.mogoroom.hadoop.dao.entity.UserRenterPortrait;
import com.mogoroom.hadoop.service.RenterPortraintService;


/**
 * @brief 租客画像查询
 * @details 租客画像查询
 * @author songwie
 * @date 2016年6月25日
 */
@Controller
@RequestMapping("/")
public class RenterPortrainController {
	
	@Autowired
	private RenterPortraintService service;
	
	@RequestMapping(value = "/" )
	@ResponseBody
    public String home(Model model,HttpServletRequest request ){
        return "hello mogoroom";
    }

	/**
	 * @brief 按租客画像查询
	 * @details 按租客画像查询
	 * @param page
	 * @param rentId
	 * @return List<UserRenterPortrait>
	 * @throws Exception
	 * @exception
     * @author songwie
     * @date 2016年6月25日
	 * @note 
	 */
	@RequestMapping(value = "/renter/portraitDetail/{renterId}" ,produces = {"text/json;charset=UTF-8"} )
	@ResponseBody
    public String renterPortrait(Model model,HttpServletRequest request,@PathVariable String renterId ){
		
		UserRenterPortrait renter = service.getOneRenter(renterId);

		String renterInfo = JsonUtil.getInstance().object2JSON(renter);
		
		
        return renterInfo;
    }
	
}
