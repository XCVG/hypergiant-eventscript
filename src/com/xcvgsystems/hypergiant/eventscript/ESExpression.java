package com.xcvgsystems.hypergiant.eventscript;

/**
 * ESExpression class that holds a subexpression inside of an expression. Very complicated, I know.
 * Eventually this will store a tokenized and parsed list of tokens and everything will be nicely recursive.
 * But right now... placeholders.
 * @author Chris
 *
 */
public class ESExpression extends ESToken
{
	String expression;

	public ESExpression(String expression)
	{
		this.expression = expression;
	}
	
	@Override
	public String toString()
	{
		return expression;
	}

}
