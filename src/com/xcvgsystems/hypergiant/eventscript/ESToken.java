package com.xcvgsystems.hypergiant.eventscript;

/**
 *  A token within a line of EventScript code.
 *  Will not look anything like this when we move further.
 * 
 * @author Chris
 *
 */
public class ESToken
{
	private String symbol;

	public ESToken()
	{
		symbol = null;
	}
	
	public ESToken(String symbol)
	{
		this.symbol = symbol;
	}

	public String getSymbol()
	{
		return symbol;
	}

	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}
	
	@Override
	public String toString()
	{
		return symbol;
	}
	
}
