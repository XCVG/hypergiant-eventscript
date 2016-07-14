package com.xcvgsystems.hypergiant.eventscript;

/**
 * A variable that can store a single value. Does not store its own name.
 * 
 * @author Chris
 *
 */
public class ESVariable extends ESToken
{
	protected enum ESVariableType {
		BOOLEAN, INTEGER, FLOAT, STRING
	}

	private Object value;
	private ESVariableType type;
	
	/**
	 * Creates an empty variable.
	 */
	public ESVariable()
	{
		super();
		
	}
	
	/**
	 * Creates an empty variable with the specified type.
	 * @param type the type of this variable
	 */
	public ESVariable(ESVariableType type)
	{
		super();
		
		this.type = type;
	}
	
	/**
	 * Creates a variable with a specified value and no explicit type.
	 * @param value the initial value
	 */
	public ESVariable(Object value)
	{
		super();
		
		this.value = value;
	}	
	
	/**
	 * Creates a variable with the specified value and type.
	 * @param value the initial value
	 * @param type the type of this variable
	 */
	public ESVariable(Object value, ESVariableType type)
	{
		super();
		
		//do we want to allow implicit casting here?
		this.type = type;
		if(isTypeMatching(value))
			this.value = value;
		else throw new ESCastException();
	}
	
	/**
	 * Creates a value with an inferred, explicit type
	 * @param value the initial value
	 * @param inferType whether we want to infer the type or not
	 */
	public ESVariable(Object value, boolean inferType)
	{
		super();
		if(inferType)
		{
			this.type = inferType(value);
		}
		this.value = value;
	}
	
	/**
	 * Get this variable's value.
	 * @return this variable's value
	 */
	public Object getValue()
	{
		return value;
	}
	
	/**
	 * Set this variable's value.
	 * @param value the new value of this variable.
	 */
	public void setValue(Object value)
	{
		//TODO type checking
		if(!isTypeMatching(value))
		{
			if(isTypeCastable(value))
			{
				
			}
			else throw new ESCastException();
		}
			
		
		this.value = value;
	}
	
	
	/**
	 * @param input the value coming in
	 * @return if the new value can be IMPLICITLY cast
	 */
	private boolean isTypeCastable(Object input)
	{
		// TODO figure out some implicit casting rules
		return false;
	}

	/**
	 * @param input the value coming in
	 * @return if the new value MATCHES the current type
	 */
	private boolean isTypeMatching(Object input)
	{
		//this variable is not strongly type, let it store whatever
		//should we check for truly invalid types though?
		if(this.type == null)
			return true;
		
		//TODO type checking
		//we will check only for EXACT MATCHES here
		
		return false;
	}
		
	/**
	 * @param input the value to infer the type of
	 * @return the inferred type
	 */
	private ESVariableType inferType(Object input)
	{
		// TODO by infer type we really mean match the object type to the ES type
		return null;
	}

}
