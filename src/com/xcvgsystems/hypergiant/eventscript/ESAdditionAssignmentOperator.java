package com.xcvgsystems.hypergiant.eventscript;

public class ESAdditionAssignmentOperator extends ESOperator implements ESVariableOperator
{

	public ESAdditionAssignmentOperator()
	{
		super("+=");
	}

	@Override
	public void operate(ESVariable var, ESValue val)
	{
		
		throw new RuntimeException(); //fuck it, I'll do this one later
		
	}


}
