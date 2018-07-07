package com.maass.finance.application.helpers;

import com.maass.finance.application.beans.BaseResponse;

public class ExceptionResolver {
	public static BaseResponse MANDATORY_DATA_NOT_RECIEVED = new BaseResponse(502, "Mandatory Data were not found in the request. Not Found Data:: ", "BUS_FLT_NT_FND_REQ_01", "We are unable to process the request currently. Please try after some time.");
	
}
