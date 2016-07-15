package com.xcvgsystems.hypergiant.eventscript;

/**
 * Data types allowed under the EventScript type system.
 * 
 * Boolean : A true/false value.
 * Integer : A 32-bit signed int.
 * Float   : A 32-bit floating point number.
 * String  : A string of any length.
 * All data types are nullable.
 * 
 * @author Chris
 *
 */
enum ESDataType
{

	BOOLEAN, INTEGER, FLOAT, STRING;
	
	/**
	 * Check if an object is an allowable type.
	 * @param input the object to check
	 * @return if the type is allowable under EventScript's type system
	 */
	static boolean isTypeAllowable(Object input)
	{
		return (input instanceof Boolean || input instanceof Integer || input instanceof Float || input instanceof String);
	}
	
	/**
	 * Check if an object can be cast to the destination type.
	 * @param source the source object
	 * @param dest the destination type
	 * @return whether source can be cast to destination
	 */
	static boolean isTypeCastable(Object source, ESDataType dest)
	{
		//if it's not a recognized type, toss it
		if(!isTypeAllowable(source))
			return false;
		
		throw new RuntimeException(); //TODO not implemented
	}
	
	/**
	 * Check if an object exactly matches the destination type.
	 * @param object the object to check
	 * @param type the desired data type
	 * @return whether the object matches the data type
	 */
	static boolean isTypeMatching(Object object, ESDataType type)
	{		
		return getTypeForObject(object) == type;
	}
	
	/**
	 * Get the EventScript type that corresponds to an object.
	 * @param object an object to check
	 * @return the matching ES data type
	 */
	static ESDataType getTypeForObject(Object object)
	{
		if(object instanceof Integer)
		{
			return ESDataType.INTEGER;
		}
		else if (object instanceof Float)
		{
			return ESDataType.FLOAT;
		}
		else if (object instanceof String)
		{
			return ESDataType.STRING;
		}
		else if(object instanceof Boolean)
		{
			return ESDataType.BOOLEAN;
		}
		else throw new ESDataTypeException();
	}
	
	//hey, could we put casting code here?

}
