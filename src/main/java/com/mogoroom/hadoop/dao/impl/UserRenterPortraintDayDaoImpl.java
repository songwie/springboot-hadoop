package com.mogoroom.hadoop.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.hadoop.base.dao.CommonDAO;
import com.mogoroom.hadoop.base.dao.EntityMap;

@Component
public class UserRenterPortraintDayDaoImpl {
	
	@Autowired
	private CommonDAO commonDAO;
	
	public List<EntityMap> queryInsureAccountByUserId(Long userID) {
		String strSql = "select SC.SERV_COMPANY_NAME AS servCompanyName,SC.ID AS servCompanyId,  "
					    +"      SP.ID AS servProdId ,SP.PROD_NAME AS servProdName,IA.INSURE_ACCT_ID AS insureAcctId, "
						+"      IA.TOTAL_AMOUNT AS totalAmount,IA.INSURE_CERT_NO AS insureCertNo, "
						+" 		IA.CREATE_TIME AS createTime,IA.`STATUS` AS status,IA.IS_DEFAULT  AS isDefault "
						+" from INSURE_ACCT IA  "
						+" INNER JOIN SERV_COMPANY SC ON SC.ID=IA.SERV_COMPANY_ID "
						+" INNER JOIN SERV_PROD SP ON SP.ID=IA.SERV_PROD_ID  "
						+" WHERE IA.USER_ID=?";
		
		String []fieldList = {"servCompanyName","servCompanyId","servProdId","servProdName","insureAcctId",
				"totalAmount","insureCertNo","createTime","status","isDefault" };
		Object []paras = {userID};
		return commonDAO.getList(strSql, fieldList, paras);
	}
    
}
