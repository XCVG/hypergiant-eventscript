package com.xcvgsystems.hypergiant.eventscript;

import java.util.*;

/**
 * An instance of an EventScript script that tracks the state of this instance.
 * @author Chris
 *
 */
public class ESScriptInstance
{
	private ESScript script;
	private Map<String,ESVariable> variables;
	private int currentLine; //may change this to a pointer instead

	public ESScriptInstance(ESScript script)
	{
		this.script = script;
		this.variables = new HashMap<>();
		this.currentLine = 0;
	}

}
