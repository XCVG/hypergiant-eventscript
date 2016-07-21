package com.xcvgsystems.hypergiant.eventscript;

public class ESModulusAssignmentOperator extends ESOperator implements ESVariableOperator
{

	public ESModulusAssignmentOperator()
	{
		super("%=");
	}

	@Override
	public void operate(ESVariable var, ESValue val)
	{
		throw new RuntimeException();

	}

}
