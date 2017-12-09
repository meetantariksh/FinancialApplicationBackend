package com.maass.finance.application.dao.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maass.finance.application.beans.news.NewsHeadLines;
import com.maass.finance.application.dao.LandingPageDAO;

@Service
public class LandingPageDAOImpl implements LandingPageDAO{
	
	private static final String apiKey="d96de1b2807b4c118aca85ecdbe7d4da";
	
	
	public NewsHeadLines getNewsHeadLines(){
		NewsHeadLines headLines = null;
		String apiUrI = null;
		HttpURLConnection conn=null;
	    BufferedReader reader=null;
	    StringBuilder strBuf =  null;
	    String output = null;
	    Gson gson = null;
		try{
			apiUrI = "https://newsapi.org/v2/top-headlines?sources=google-news-in";
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
}
