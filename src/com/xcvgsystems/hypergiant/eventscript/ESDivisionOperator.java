package com.xcvgsystems.hypergiant.eventscript;

public class ESDivisionOperator extends ESOperator implements ESBinaryOperator
{

	public ESDivisionOperator()
	{
		super("/");
	}

	@Override
	public ESValue operate(ESValue arg0, ESValue arg1)
	{
		ESDataType arg0type = arg0.getType();
		ESDataType arg1type = arg1.getType();
		Object arg0value = arg0.getValue();
		Object arg1value = arg1.getValue();
		
		//if both are booleans, cast to Integer, divide, return Integer
		if(arg0type == ESDataType.BOOLEAN && arg1type == ESDataType.BOOLEAN)
		{
			return new ESValue(new Integer((Integer)ESDataType.castValue(arg0value, ESDataType.INTEGER) / (Integer)ESDataType.castValue(arg1value, ESDataType.INTEGER)));
		}
		
		//cast strings if necessary
		if(arg0type == ESDataType.STRING)
		{
			if(((String)arg0value).contains("."))
			{
				arg0type = ESDataType.FLOAT;				
			}
			else
			{
				arg0type = ESDataType.INTEGER;
			}
			arg0value = ESDataType.castValue(arg0value, arg0type);
		}
		
		if(arg1type == ESDataType.STRING)
		{
			if(((String)arg1value).contains("."))
			{
				arg1type = ESDataType.FLOAT;				
			}
			else
			{
				arg1type = ESDataType.INTEGER;
			}
			arg1value = ESDataType.castValue(arg1value, arg1type);
		}
		
		//at this point, both args are either float or integer
		if(arg0type == ESDataType.INTEGER && arg1type == ESDataType.INTEGER)
		{
			return new ESValue(new Integer((Integer)arg0value / (Integer)arg1value));
		}
		else if(arg0type == ESDataType.FLOAT && arg1type == ESDataType.FLOAT)
		{
			return new ESValue(new Float((Float)arg0value / (Float)arg1value));
		}
		else
		{
			//one is a float, one is an integer, but which one
			if(arg0type == ESDataType.FLOAT)
			{
				return new ESValue(new Float((Float)arg0value / (Integer)arg1value));
			}
			else
			{
				return new ESValue(new Float((Integer)arg0value / (Float)arg1value));
			}
		}
	}

}
