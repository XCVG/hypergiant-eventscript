package com.xcvgsystems.hypergiant.eventscript;

public class ESNotOperator extends ESOperator implements ESUnaryOperator
{

	public ESNotOperator()
	{
		super("!");
	}

	@Override
	public ESValue operate(ESValue arg0)
	{
		//performance optimization
		if(arg0.getType() == ESDataType.BOOLEAN)
		{
			return new ESValue(new Boolean(!(Boolean)arg0.getValue()));
		}
		
		Boolean arg0cast = (Boolean)ESDataType.castValue(arg0.getValue(), ESDataType.BOOLEAN);
		return new ESValue(new Boolean(!arg0cast));
		
	}
	
	@Override
	public ESPrecedence getPrecedence()
	{
		return ESPrecedence.UNARY;
	}

}
