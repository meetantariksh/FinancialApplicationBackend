package com.maass.medApp.backEnd.dao;

import java.util.List;

import com.maass.medApp.backEnd.Beans.OurDoctorsBean;
import com.maass.medApp.backEnd.Beans.MedicalNews.NewsHeadLines;

public interface LandingPage_dao {
	public List<OurDoctorsBean> getDoctorReccomendations();
	public NewsHeadLines getNewsHeadLines();
}
