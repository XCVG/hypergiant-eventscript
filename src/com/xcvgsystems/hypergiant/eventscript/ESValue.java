package com.xcvgsystems.hypergiant.eventscript;

/**
 * A literal value. Currently immutable. May separate into separate wrappers.
 * 
 * @author Chris
 *
 */
public class ESValue extends ESToken
{
	
	private final Object value; //very, very unsafe!

	public ESValue(Object value)
	{
		super();
		
		//TODO type checking?
		
		this.value = value;
	}
	
	public Object getValue()
	{
		return value;
	}

}
