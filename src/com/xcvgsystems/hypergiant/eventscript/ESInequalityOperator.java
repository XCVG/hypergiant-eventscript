package com.xcvgsystems.hypergiant.eventscript;

public class ESInequalityOperator extends ESOperator implements ESBinaryOperator
{

	public ESInequalityOperator()
	{
		super("!=");
	}

	@Override
	public ESValue operate(ESValue arg0, ESValue arg1)
	{
		ESDataType arg0type = arg0.getType();
		ESDataType arg1type = arg1.getType();
		Object arg0value = arg0.getValue();
		Object arg1value = arg1.getValue();
		
		//in a semi-loosely-typed language, equality is a nebulous notion
		//so we have to be VERY careful about this
		
		//if they're both the same type, we can defer to Java's comparisons
		if(arg0type == arg1type)
		{
			return new ESValue(new Boolean(!(arg0value).equals((arg1value))));
		}
		
		//TODO a better implementation of boolean-integer comparison?
		
		//we now know that at least one is numeric
		//so let's cast both to float
		if(arg0type != ESDataType.FLOAT)
		{
			arg0value = ESDataType.castValue(arg0value, ESDataType.FLOAT);
		}
		if(arg1type != ESDataType.FLOAT)
		{
			arg1value = ESDataType.castValue(arg1value, ESDataType.FLOAT);
		}
		
		//now we can compare floats!
		return new ESValue(new Boolean(Float.compare((Float)arg0value, (Float)arg1value) != 0));
	}
	
	@Override
	public ESPrecedence getPrecedence()
	{
		return ESPrecedence.COMPARISON;
	}

}
