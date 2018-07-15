package com.maass.finance.application.helpers;

import com.maass.finance.application.beans.BaseResponse;

public class ExceptionResolver {
	public static BaseResponse MANDATORY_DATA_NOT_RECIEVED = new BaseResponse(502, "Mandatory Data were not found in the request. Not Found Data:: ", "BUS_FLT_NT_FND_REQ_01", "We are unable to process the request currently. Please try after some time.");
	public static BaseResponse DATABASE_ERROR = new BaseResponse(501, "Unable to fetch data from Database.", "DAO_DB_ERR_01", "We are unable to process the request currently. Please try after some time.");
	public static BaseResponse USER_PROFILE_NOT_FOUND = new BaseResponse(502, "User Profile was not found for the recieved email.", "BUS_FLT_USR_PRF_NT_FD", "We were unable to find an user associated with this email. If you believe this is a mistake, please let us know.");
	
}
