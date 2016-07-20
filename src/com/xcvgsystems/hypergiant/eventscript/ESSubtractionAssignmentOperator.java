package com.xcvgsystems.hypergiant.eventscript;

public class ESSubtractionAssignmentOperator extends ESOperator implements ESVariableOperator
{

	public ESSubtractionAssignmentOperator()
	{
		super("-=");
	}

	@Override
	public void operate(ESVariable var, ESValue val)
	{
		throw new RuntimeException();

	}

}
