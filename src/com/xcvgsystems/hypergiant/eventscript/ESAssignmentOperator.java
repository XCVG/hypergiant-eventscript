package com.xcvgsystems.hypergiant.eventscript;

public class ESAssignmentOperator extends ESOperator implements ESVariableOperator
{

	public ESAssignmentOperator()
	{
		super("=");
	}

	@Override
	public void operate(ESVariable var, ESValue val)
	{
		//most of this is handled in the variable class I hope
		var.setValue(val);
	}
	
	@Override
	public ESPrecedence getPrecedence()
	{
		return ESPrecedence.ASSIGNMENT;
	}

}
