package org.shopcart.rest.responses;

public class RegistrationResponse
{
	private final int code;
	
	private final String status;
	
	private RegistrationResponse(int code, String status)
	{
		this.code = code;
		this.status = status;
	}

	public int getCode()
	{
		return code;
	}

	public String getStatus()
	{
		return status;
	}
	
	private static RegistrationResponse SUCCESS,
										ALREADY_USED_UNAME,
										NOT_ALLOWED_CHARS,
										INVALID_DATA;
	
	public static final RegistrationResponse SUCCESS()
	{
		if(SUCCESS == null)
		{
			SUCCESS = new RegistrationResponse(0x0, "SUCCESS");
		}
		return SUCCESS;
	}
	
	public static final RegistrationResponse ALREADY_USED_UNAME()
	{
		if(ALREADY_USED_UNAME == null)
		{
			ALREADY_USED_UNAME = new RegistrationResponse(0x10, "ALREADY_USED_UNAME");
		}
		return ALREADY_USED_UNAME;
	}
	
	public static final RegistrationResponse NOT_ALLOWED_CHARS()
	{
		if(NOT_ALLOWED_CHARS == null)
		{
			NOT_ALLOWED_CHARS = new RegistrationResponse(0x11, "NOT_ALLOWED_CHARS");
		}
		return NOT_ALLOWED_CHARS;
	}
	
	public static final RegistrationResponse INVALID_DATA()
	{
		if(INVALID_DATA == null)
		{
			INVALID_DATA = new RegistrationResponse(0x12, "INVALID_DATA");
		}
		return INVALID_DATA;
	}
}