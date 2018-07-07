package com.maass.finance.application.controller.registration;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maass.finance.application.beans.registration.GeneralUserRegistrationBean;

@RestController
@CrossOrigin("*")
@RequestMapping("/registration/*")
public class GeneralUserRegistrationController {
	@RequestMapping(
			value = "/verifyGeneralUserRegistration",
			method = {RequestMethod.POST, RequestMethod.GET}
			)
	public GeneralUserRegistrationBean getUserRegistrationData(@RequestBody Map<String, Object> payload){
		GeneralUserRegistrationBean userRegistrationBean = null;
		try{
			System.out.println(payload);
			userRegistrationBean = new GeneralUserRegistrationBean();
			if(payload != null){
				if(payload.get("email") != null && payload.get("email").toString() != ""){
					System.out.println(payload.get("email").toString());
					if(payload.get("email").toString().equalsIgnoreCase("meetantariksh@gmail.com")){
						userRegistrationBean.setUserRegistered(true);
						userRegistrationBean.setAddress("6/1 A Naskar, Para Lane, Selimpur Road, Kolkata-700031");
						userRegistrationBean.setAlternatePhone("+91 9874842035");
						userRegistrationBean.setDateOfBirth("08/10/1992");
						userRegistrationBean.setEmail(payload.get("email").toString());
						userRegistrationBean.setPrimaryPhone("+91 8961452831");
						userRegistrationBean.setRegistrationId("ABCD1234REG0001");
						userRegistrationBean.setFirstName("Antariksh");
						userRegistrationBean.setLastName("Roy");
					}else{
						userRegistrationBean.setUserRegistered(false);
					}
				}else{
					throw (new Exception("Email not found."));
				}
			}else{
				throw (new Exception("Payload was not recieved."));
			}
		}catch(Exception exp){
			exp.printStackTrace();
		}
		return userRegistrationBean;
	}
}
