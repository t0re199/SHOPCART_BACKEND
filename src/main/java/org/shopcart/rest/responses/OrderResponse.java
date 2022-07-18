package org.shopcart.rest.responses;

public class OrderResponse
{
	private final int code;
	
	private final String status;
	
	private OrderResponse(int code, String status)
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
	
	private static OrderResponse SUCCESS,
							     GENERIC_ERROR,
							     PRODUCT_NOT_FOUND,
							     PRODUCT_PRICE_CHANGED,
							     PRODUCT_AVAILABILITY_NOT_ENOUGH;
	
	public static final OrderResponse SUCCESS()
	{
		if(SUCCESS == null)
		{
			SUCCESS = new OrderResponse(0x0, "SUCCESS");
		}
		return SUCCESS;
	}
	
	public static final OrderResponse GENERIC_ERROR()
	{
		if(GENERIC_ERROR == null)
		{
			GENERIC_ERROR = new OrderResponse(0x10, "GENERIC_ERROR");
		}
		return GENERIC_ERROR;
	}
	
	public static final OrderResponse PRODUCT_NOT_FOUND()
	{
		if(PRODUCT_NOT_FOUND == null)
		{
			PRODUCT_NOT_FOUND = new OrderResponse(0x11, "PRODUCT_NOT_FOUND");
		}
		return PRODUCT_NOT_FOUND;
	}
	
	public static final OrderResponse PRODUCT_PRICE_CHANGED()
	{
		if(PRODUCT_PRICE_CHANGED == null)
		{
			PRODUCT_PRICE_CHANGED = new OrderResponse(0x12, "PRODUCT_PRICE_CHANGED");
		}
		return PRODUCT_PRICE_CHANGED;
	}
	
	public static final OrderResponse PRODUCT_AVAILABILITY_NOT_ENOUGH()
	{
		if(PRODUCT_AVAILABILITY_NOT_ENOUGH == null)
		{
			PRODUCT_AVAILABILITY_NOT_ENOUGH = new OrderResponse(0x13, "PRODUCT_AVAILABILITY_NOT_ENOUGH");
		}
		return PRODUCT_AVAILABILITY_NOT_ENOUGH;
	}
}