package com.xcvgsystems.hypergiant.eventscript;

/**
 * An EventScript function call.
 * 
 * @author Chris
 *
 */
public class ESCall extends ESToken
{

	public ESCall(String call)
	{
		super(call);
	}
	
	//TODO lookup known functions and translate
	
	public ESValue execute()
	{
		return null;
	}

}
