package com.maass.finance.application.helpers;

public class StringHandlers {
	
	public static boolean isStringNullOrEmpty(String inputString){
		boolean result = false;
		if(inputString != null) {
			if((inputString.trim()).equals("")) {
				result = true;
			}else {
				result = false;
			}
		}else {
			result = true;
		}
		return result;
	}
	
}
