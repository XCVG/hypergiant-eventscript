package com.xcvgsystems.hypergiant.eventscript;

enum ESDataType
{
	BOOLEAN, INTEGER, FLOAT, STRING;
	
	/**
	 * @param input the object to check
	 * @return if the type is allowable under EventScript's type system
	 */
	static boolean isTypeAllowable(Object input)
	{
		return (input instanceof Boolean || input instanceof Integer || input instanceof Float || input instanceof String);
	}
	
	static boolean isTypeCastable(Object source, ESDataType dest)
	{
		throw new RuntimeException(); //TODO not implemented
	}
	
	static boolean isTypeMatching(Object object, ESDataType type)
	{		
		return getTypeForObject(object) == type;
	}
	
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

}
