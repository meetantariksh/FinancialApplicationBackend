package com.maass.finance.application.controller.landingPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maass.finance.application.beans.news.NewsHeadLines;
import com.maass.finance.application.dao.UserAuthenticationDAO;


@RestController
@CrossOrigin("*")
public class LandingPage {
	@Autowired
	UserAuthenticationDAO userAuthenticationDao;
	
	@RequestMapping("/getNewsHeadlines")
	public NewsHeadLines getNewsHeadlines(){
		NewsHeadLines newsHeadLines = null;
		try{
			newsHeadLines = userAuthenticationDao.getNewsHeadLines();
		}catch(Exception exp){
			exp.printStackTrace();
		}
		return newsHeadLines;
	}
	
}
