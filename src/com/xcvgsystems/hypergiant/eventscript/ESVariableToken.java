package com.xcvgsystems.hypergiant.eventscript;

/**
 * A variable token. Stores the variable's name which will be looked up in a dictionary/hashtable/map.
 * May change the way variables work once we get to executing statements.
 * 
 * @author Chris
 *
 */
public class ESVariableToken extends ESToken
{

	private final String varname;
	
	public ESVariableToken(String varname)
	{
		this.varname = varname;
	}

	public String getVarname()
	{
		return varname;
	}

}
