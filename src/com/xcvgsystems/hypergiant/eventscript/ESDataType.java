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
	/*
	static boolean isTypeCastable(Object source, ESDataType dest)
	{
		//if it's not a recognized type, toss it
		if(!isTypeAllowable(source))
			return false;
		
		throw new RuntimeException(); //TODO not implemented
	}
	*/
	//pointless but may rework into "is variable castable" ie checking if a cast will be successful
	
	/**
	 * Cast from one data type to another using EventScript rules
	 * @param source the source object
	 * @param type the type to cast to
	 * @return the cast value in the intended data type
	 */
	static Object castValue(Object source, ESDataType type)
	{
		//is this the behaviour we want?
		if(source == null)
			throw new ESCastException();
		
		ESDataType sourcetype = getTypeForObject(source);
		
		//easy
		if(sourcetype == type)
			return source;
		
		switch(sourcetype) //switch on the source datatype
		{
		case BOOLEAN:
			Boolean boolsrc = (Boolean)source;			
			switch(type)
			{
			case FLOAT:
				return (boolsrc ? new Float(1) : new Float(0));
			case INTEGER:
				return (boolsrc ? new Integer(1) : new Integer(0));
			case STRING:
				return (boolsrc ? new String("true") : new String("false"));
			default:
				break;			
			}
			break;
		case FLOAT:
			Float floatsrc = (Float)source;
			switch(type)
			{
			case BOOLEAN:
				return new Boolean((int)floatsrc.floatValue() == 0 ? false : true);
			case INTEGER:
				return new Integer((int)floatsrc.floatValue());
			case STRING:
				return floatsrc.toString();
			default:
				break;			
			}
			break;
		case INTEGER:
			Integer intsrc = (Integer)source;
			switch(type)
			{
			case BOOLEAN:
				return new Boolean(intsrc == 0 ? false : true);
			case FLOAT:
				return new Float(intsrc);
			case STRING:
				return intsrc.toString();
			default:
				break;			
			}
			break;
		case STRING:
			String stringsrc = (String)source;
			switch(type)
			{
			case BOOLEAN:
				if(stringsrc.trim().equalsIgnoreCase("true") || stringsrc.trim().equals("1"))
					return new Boolean(true);
				if(stringsrc.trim().equalsIgnoreCase("false") || stringsrc.trim().equals("0"))
					return new Boolean(false);
				throw new ESCastException();
			case FLOAT:
				try
				{
					return new Float(Float.parseFloat(stringsrc)); //is this safe?
				}
				catch(NumberFormatException e)
				{
					throw new ESCastException();
				}
			case INTEGER:
				try
				{
					return new Integer(Integer.parseInt(stringsrc)); //is this safe?
				}
				catch(NumberFormatException e)
				{
					throw new ESCastException();
				}
			default:
				break;			
			}
			break;
		default:
			break;
		}
		throw new ESCastException();
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
		if(object == null)
		{
			return null;
		}
		else if(object instanceof Integer)
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
