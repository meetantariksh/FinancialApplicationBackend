package com.maass.medApp.backEnd.dao.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maass.medApp.backEnd.Beans.OurDoctorsBean;
import com.maass.medApp.backEnd.Beans.MedicalNews.NewsHeadLines;
import com.maass.medApp.backEnd.Collections.MedApp_client_ourDoctors;
import com.maass.medApp.backEnd.Repositories.MedApp_client_ourDoctors_Repository;
import com.maass.medApp.backEnd.dao.LandingPage_dao;

@Service
public class LandingPage_daoImpl implements LandingPage_dao {
	
	private static final String apiKey="d96de1b2807b4c118aca85ecdbe7d4da";
	
	@Autowired
	private MedApp_client_ourDoctors_Repository medApp_client_ourDoctors_Repository;
	
	public List<OurDoctorsBean> getDoctorReccomendations(){
		List<OurDoctorsBean> doctorsBeans = null;
		List<MedApp_client_ourDoctors> ourDoctorsBeans = null;
		try{
			ourDoctorsBeans = medApp_client_ourDoctors_Repository.findAll();
			doctorsBeans = new ArrayList<OurDoctorsBean>();
			for(MedApp_client_ourDoctors doc: ourDoctorsBeans){
				OurDoctorsBean tempBean = new OurDoctorsBean();
				tempBean.setName(doc.name);
				tempBean.setQualification(doc.qualification);
				tempBean.setRecomendationQuote(doc.recomendationQuote);
				tempBean.setImageUrl(doc.imageUrl);
				doctorsBeans.add(tempBean);
			}
		}catch(Exception exp){
			exp.printStackTrace();
		}
		return doctorsBeans;
	}
	
	public NewsHeadLines getNewsHeadLines(){
		NewsHeadLines headLines = null;
		String apiUrI = null;
		HttpURLConnection conn=null;
	    BufferedReader reader=null;
	    StringBuilder strBuf =  null;
	    String output = null;
	    Gson gson = null;
		try{
			//apiUrI = "https://newsapi.org/v2/everything?sources=medical-news-today";
			apiUrI = "https://newsapi.org/v2/top-headlines?sources=national-geographic";
			strBuf = new StringBuilder();  
			URL url = new URL(apiUrI);  
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("X-Api-Key", apiKey);
            
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                              + conn.getResponseCode());
            }
            
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
              
            while ((output = reader.readLine()) != null){
                strBuf.append(output);
            }
            
            gson = new GsonBuilder().create();
            headLines = gson.fromJson(strBuf.toString(), NewsHeadLines.class);
            
		}catch (Exception exp){
			exp.printStackTrace();
		}finally {
			if(reader!=null)
			{
				try {
					reader.close();
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
			if(conn!=null)
			{
				conn.disconnect();
			}
		}

		return headLines;
	}
	
	public boolean sendEmail(){
		boolean isSuccess = false;
		try{
			
		}catch (Exception exp){
			exp.printStackTrace();
		}
		return isSuccess;
	}
	
}
