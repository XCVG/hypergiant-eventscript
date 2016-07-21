package com.xcvgsystems.hypergiant.eventscript;

public class ESMultiplicationAssignmentOperator extends ESOperator implements ESVariableOperator
{

	public ESMultiplicationAssignmentOperator()
	{
		super("*=");
	}

	@Override
	public void operate(ESVariable var, ESValue val)
	{
		throw new RuntimeException();
	}

}
