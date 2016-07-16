package com.xcvgsystems.hypergiant.eventscript;

/**
 * A variable that can store a single value. Does not store its own name.
 * 
 * @author Chris
 *
 */
public class ESVariable
{

	private Object value;
	private ESDataType type; 
	
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
	public ESVariable(ESDataType type)
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
		
		//should we be checking for unacceptable types here?
		this.value = value;
	}	
	
	/**
	 * Creates a variable with the specified value and type.
	 * @param value the initial value
	 * @param type the type of this variable
	 */
	public ESVariable(Object value, ESDataType type)
	{
		super();
		
		this.type = type;
		if(ESDataType.isTypeMatching(value, type))
			this.value = value;
		else throw new ESDataTypeException();
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
			this.type = ESDataType.getTypeForObject(value);
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
		//check if the input is actually acceptable
		if(!ESDataType.isTypeAllowable(value))
			throw new ESDataTypeException();
		
		//if this is a loosely typed variable, just assign it
		if(this.type == null)
			this.value = value;
		
		//if the type matches, assign it
		if(ESDataType.isTypeMatching(value, this.type))
		{
			this.value = value;
		}
		else throw new ESDataTypeException(); //otherwise give up

		
	}
		
	/**
	 * Get this variable's type
	 * @return this variable's type
	 */
	public ESDataType getType()
	{
		return type;
	}

}
