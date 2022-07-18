package org.shopcart.jwt;

public class JwtWrapper
{
	private String token;
	
	private boolean needsUpdate = false,
					isValid = true;

	public JwtWrapper(String token)
	{
		this.token = token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public boolean isValid()
	{
		return isValid;
	}

	public boolean needsUpdate()
	{
		return needsUpdate;
	}

	public String getToken()
	{
		return token;
	}

	public void markAsUpdated()
	{
		needsUpdate = true;
	}

	public void markAsInvalid()
	{
		isValid = false;
	}
}
