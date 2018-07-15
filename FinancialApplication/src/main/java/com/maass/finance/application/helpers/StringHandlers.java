package com.maass.finance.application.helpers;

public class StringHandlers {
	
	public static boolean isStringNullOrEmpty(String inputString){
		boolean result = false;
		if(inputString != null) {
			if((inputString.trim()).equals("")) {
				result = false;
			}else {
				result = true;
			}
		}else {
			result = false;
		}
		return result;
	}
	
}
