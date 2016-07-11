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
			//always advance the pointer after getting the token
			if(Character.isWhitespace(line.charAt(pointer)))
			{
				//character is whitespace, without context it's meaningless so just keep going
				pointer++;
			}
			else if(line.charAt(pointer) == '$') //does that work in Java
			{
				//character is a dollar sign, must be a variable name, continue until we hit something that's not a letter or number
				
			}
			else if("+-*/%=!><&|".indexOf(line.charAt(pointer)) >= 0) //hack suggested by Marc on stackoverflow
			{
				//character is an operator or first character of an operator
				//depending on what it is, we may need to check the next character
				//
				char firstChar = line.charAt(pointer);
				switch(firstChar)
				{
				case '+': //+, ++, or +=
					pointer++;
					if(line.charAt(pointer) == '+')
					{
						tokens.add(new ESToken("++"));
						pointer++;
					}
					else if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESToken("+="));
						pointer++;
					}
					else tokens.add(new ESToken("+"));
					
					break;
				case '-': //-, --, or -=
					pointer++;
					if(line.charAt(pointer) == '-')
					{
						tokens.add(new ESToken("--"));
						pointer++;
					}
					else if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESToken("-="));
						pointer++;
					}
					else tokens.add(new ESToken("-"));
					
					break;
				case '*': //* or *=
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESToken("*="));
						pointer++;
					}
					else tokens.add(new ESToken("*"));
					
					break;
				case '/': // / or /=
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESToken("/="));
						pointer++;
					}
					else tokens.add(new ESToken("/"));
					
					break;
				case '=': //= or ==
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESToken("=="));
						pointer++;
					}
					else tokens.add(new ESToken("="));
					
					break;
				case '!': //! or !=
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESToken("!="));
						pointer++;
					}
					else tokens.add(new ESToken("!"));
					
					break;
				case '>': //> or >=
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESToken(">="));
						pointer++;
					}
					else tokens.add(new ESToken(">"));
					
					break;
				case '<': //< or <=
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESToken("<="));
						pointer++;
					}
					else tokens.add(new ESToken("<"));
					
					break;
				case '&': //&&
					pointer++;
					if(line.charAt(pointer) == '&')
					{
						tokens.add(new ESToken("&&"));
						pointer++;
					}
					else throw new ESUnrecognizedTokenException();
					
					break;
				case '|': //||
					pointer++;
					if(line.charAt(pointer) == '|')
					{
						tokens.add(new ESToken("||"));
						pointer++;
					}
					else throw new ESUnrecognizedTokenException();
					
					break;
				}
			}
			else if(Character.isDigit(line.charAt(pointer)))
			{
				//character is a digit, continue until we hit something that's not a digit or decimal, then save the token
				int firstPos = pointer;
				
				do
				{
					pointer++;
				} while(Character.isDigit(line.charAt(pointer)) || line.charAt(pointer) == '.');
				
				String number = line.substring(firstPos, pointer + 1); //be careful of off-by-one
				
				//if we hit a decimal, it's a float, otherwise, it's an int
				if(number.contains("."))
				{
					//TODO datatypes (well all token types really
					tokens.add(new ESToken(Float.toString(Float.parseFloat(number))));
				}
				else
				{
					//TODO datatypes (well all token types really
					tokens.add(new ESToken(Integer.toString(Integer.parseInt(number))));
				}
				
				//do we need this? I think so
				pointer++;
			}
			else if(line.charAt(pointer) == '"')
			{
				//character is a quotation mark, must be beginning of a string, continue until we hit a non-escaped quotation mark
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
				throw new ESUnrecognizedTokenException();
			}
			//curlybraces shouldn't happen because the script tokenizer should handle those
		}
		
		
		return tokens;
	}

}
