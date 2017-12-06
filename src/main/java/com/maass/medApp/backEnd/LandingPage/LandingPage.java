package com.maass.medApp.backEnd.LandingPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maass.medApp.backEnd.Beans.OurDoctorsBean;
import com.maass.medApp.backEnd.Beans.MedicalNews.NewsHeadLines;
import com.maass.medApp.backEnd.dao.LandingPage_dao;

@RestController
@CrossOrigin("*")
public class LandingPage {
	
	@Autowired
	private LandingPage_dao landingPage_dao;
	
	@RequestMapping("/getRecommendations")
	public List<OurDoctorsBean> getAllRecommendations(){
		List<OurDoctorsBean> ourDoctorsBeans = null;
		try{
			ourDoctorsBeans = landingPage_dao.getDoctorReccomendations();
		}catch(Exception exp){
			exp.printStackTrace();
		}
		return ourDoctorsBeans;
	}
	
	@RequestMapping("/getNewsHeadlines")
	public NewsHeadLines getNewsHeadlines(){
		NewsHeadLines newsHeadLines = null;
		try{
			newsHeadLines = landingPage_dao.getNewsHeadLines();
		}catch(Exception exp){
			exp.printStackTrace();
		}
		return newsHeadLines;
	}

}
