package com.xcvgsystems.hypergiant.eventscript;

public class ESIncrementOperator extends ESOperator implements ESUnaryOperator
{

	public ESIncrementOperator()
	{
		super("++");
		// TODO Auto-generated constructor stub
	}

	@Override
	public ESValue operate(ESValue arg0)
	{
		if(arg0.getType() == ESDataType.BOOLEAN)
		{
			throw new ESOperationException();
		}
		else if(arg0.getType() == ESDataType.FLOAT)
		{
			return new ESValue(new Float(((Float)arg0.getValue()).floatValue() + 1.0)); //whoa			
		}
		else if(arg0.getType() == ESDataType.INTEGER)
		{
			return new ESValue(new Integer(((Integer)arg0.getValue()).intValue() + 1));
		}
		else if(arg0.getType() == ESDataType.STRING)
		{
			String argstr = (String)arg0.getValue();
			if(argstr.contains("."))
			{
				Float flt = (Float)ESDataType.castValue(argstr, ESDataType.FLOAT);
				return new ESValue(new Float(flt+1.0));
			}
			else
			{
				Integer it = (Integer)ESDataType.castValue(argstr, ESDataType.INTEGER);
				return new ESValue(new Integer(++it));
			}
		}
		else throw new ESOperationException();
	}
	
	@Override
	public ESPrecedence getPrecedence()
	{
		return ESPrecedence.UNARY;
	}


}
