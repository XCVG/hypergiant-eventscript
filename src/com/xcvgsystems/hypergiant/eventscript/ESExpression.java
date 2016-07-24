package com.xcvgsystems.hypergiant.eventscript;

import java.util.List;

/**
 * ESExpression class that holds an expression as a list of tokens.
 * I may move the tokenizer in here or in a separate class out of ESEngine.
 * @author Chris
 *
 */
public class ESExpression extends ESToken
{
	List<ESToken> expression;
	
	public ESExpression()
	{
		super();
	}
	
	public ESExpression(List<ESToken> tokens)
	{
		super();
		this.expression = tokens;
	}

	@Override
	public String getSymbol()
	{
		return expression.toString();
	}
	
	@Override
	public String toString()
	{
		return expression.toString();
	}

}
