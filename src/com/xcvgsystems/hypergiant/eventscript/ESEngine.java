package com.xcvgsystems.hypergiant.eventscript;

import java.util.*;

/**
 * The main EventScript interpreter class.
 * 
 * @author Chris
 *
 */
public class ESEngine
{
	
	/**
	 * Evaluates a single line of EventScript code and returns the value.
	 *   
	 * @param line The raw line of code.
	 * @return A return value object with the result of the evaluation.
	 */
	public ESReturnValue evalLine(String line)
	{
		//break string into pieces
		
		//tokenize string list
		
		//execute code
		//blocks, calls, div/mul, add/sub
		
		return null;
	}
	
	/**
	 * Breaks a line of EventScript code into tokens.
	 * 
	 * @param line The raw line of code.
	 * @return A list of tokens representing the line of code.
	 */
	public List<ESToken> tokenizeLine (String line)
	{
		List<ESToken> tokens = new LinkedList<>();
		
		int pointer = 0;
		while(pointer < line.length())
		{
			if(Character.isWhitespace(line.charAt(pointer)))
			{
				//character is whitespace, without context it's meaningless so just keep going
				pointer++;
			}
			else if(line.charAt(pointer) == '=') //does that work in Java
			{
				//character is a dollar sign, must be a variable name, continue until we hit something that's not a letter or number
				
			}
			else if(Character.isDigit(line.charAt(pointer)))
			{
				//character is a digit, continue until we hit something that's not a digit or decimal, then save the token
				
				//if we don't hit a decimal, it's an int, if we do, it's a float
			}
			else if(Character.isAlphabetic(line.charAt(pointer)))
			{
				//character is a letter, must be a function call, continue until we hit whitespace or a bracket
			}
			else if(line.charAt(pointer) == '(')
			{
				//character is a bracket, must be the beginning of a sub-expression or explicit cast
				//count brackets and make it to the closing brace, then try to figure out what it is
			}
			else
			{
				//character is not recognizable, we broke something
				throw new ESInternalException();
			}
			//curlybraces shouldn't happen because the script tokenizer should handle those
		}
		
		
		return tokens;
	}

}
