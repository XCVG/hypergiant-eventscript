package com.xcvgsystems.hypergiant.eventscript;

public abstract class ESOperator extends ESToken
{

	public ESOperator()
	{
		// TODO Auto-generated constructor stub
	}

	public ESOperator(String symbol)
	{
		super(symbol);
		// TODO Auto-generated constructor stub
	}
	
	public abstract ESPrecedence getPrecedence();

}
