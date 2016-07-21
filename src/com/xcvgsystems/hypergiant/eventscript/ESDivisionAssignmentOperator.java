package com.xcvgsystems.hypergiant.eventscript;

public class ESDivisionAssignmentOperator extends ESOperator implements ESVariableOperator
{

	public ESDivisionAssignmentOperator()
	{
		super("/=");
	}

	@Override
	public void operate(ESVariable var, ESValue val)
	{
		throw new RuntimeException();
	}

}
