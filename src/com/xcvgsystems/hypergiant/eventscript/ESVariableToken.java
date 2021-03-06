package com.xcvgsystems.hypergiant.eventscript;

/**
 * A variable token. Stores the variable's name which will be looked up in a dictionary/hashtable/map.
 * May change the way variables work once we get to executing statements. Should this reference a variable directly?
 * 
 * @author Chris
 *
 */
public class ESVariableToken extends ESToken
{

	private final String varname;
	
	/**
	 * Create a new token referencing an EventScript variable.
	 * @param varname the name of this variable
	 */
	public ESVariableToken(String varname)
	{
		this.varname = varname;
	}

	/**
	 * @return this variable's name
	 */
	public String getVarname()
	{
		return varname;
	}
	
	@Override
	public String toString()
	{
		return "$" + varname;
	}

}
