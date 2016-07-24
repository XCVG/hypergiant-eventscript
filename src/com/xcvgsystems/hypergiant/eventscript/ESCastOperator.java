package com.xcvgsystems.hypergiant.eventscript;

public class ESCastOperator extends ESOperator implements ESUnaryOperator
{
	
	private final ESDataType type;

	public ESCastOperator(ESDataType type)
	{
		super("(" + type.toString() + ")");
		this.type = type;
	}
	
	@Override
	public ESValue operate(ESValue arg0)
	{
		return new ESValue(ESDataType.castValue(arg0.getValue(), type));
	}
	
	@Override
	public ESPrecedence getPrecedence()
	{
		return ESPrecedence.UNARY;
	}

}
