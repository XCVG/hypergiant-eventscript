package com.xcvgsystems.hypergiant.eventscript;

public class ESAndOperator extends ESOperator implements ESBinaryOperator
{

	public ESAndOperator()
	{
		super("&&");
	}

	@Override
	public ESValue operate(ESValue arg0, ESValue arg1)
	{
		//performance optimization
		if(arg0.getType() == ESDataType.BOOLEAN && arg1.getType() == ESDataType.BOOLEAN)
		{
			return new ESValue(new Boolean((Boolean)arg0.getValue() && (Boolean)arg1.getValue()));
		}
		
		Boolean arg0cast = (Boolean)ESDataType.castValue(arg0.getValue(), ESDataType.BOOLEAN);
		Boolean arg1cast = (Boolean)ESDataType.castValue(arg1.getValue(), ESDataType.BOOLEAN);
		return new ESValue(new Boolean(arg0cast && arg1cast));
	}

}
