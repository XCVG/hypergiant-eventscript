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

	/**
	 * Create a new ESValue.
	 * @param value the value contained by the ESValue
	 */
	public ESValue(Object value)
	{
		super();
		
		this.value = value;
		
		//type checking
		getType(); //will throw exception if type unacceptable
	}
	
	/**
	 * @return this value
	 */
	public Object getValue()
	{
		return value;
	}
	
	/**
	 * @return this value's data type
	 */
	public ESDataType getType()
	{
		return ESDataType.getTypeForObject(value);
	}
	
	@Override
	public String toString()
	{
		return value != null ? value.toString() : "ESNULL";
	}

}
