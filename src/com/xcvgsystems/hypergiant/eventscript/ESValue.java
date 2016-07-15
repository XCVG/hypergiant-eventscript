package com.xcvgsystems.hypergiant.eventscript;

import com.xcvgsystems.hypergiant.eventscript.ESVariable.ESVariableType;

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
	public ESVariableType getType()
	{
		if(value instanceof Integer)
		{
			return ESVariableType.INTEGER;
		}
		else if (value instanceof Float)
		{
			return ESVariableType.FLOAT;
		}
		else if (value instanceof String)
		{
			return ESVariableType.STRING;
		}
		else if(value instanceof Boolean)
		{
			return ESVariableType.BOOLEAN;
		}
		else throw new ESCastException(); //TODO change to ESDataTypeException
	}

}
