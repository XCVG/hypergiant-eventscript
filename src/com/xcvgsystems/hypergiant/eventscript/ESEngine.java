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
	public static ESExpression tokenizeLine (String line)
	{
		line = line.concat(" "); //a gross hack instead of adding null checks.
		
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
			else if(line.charAt(pointer) == '$') //does that work in Java?
			{
				//character is a dollar sign, must be a variable name, continue until we hit something that's not a letter or number
				int firstPos = pointer;
				
				do
				{
					pointer++;
				} while(Character.isLetterOrDigit(line.charAt(pointer)) || line.charAt(pointer) == '_');
				
				String varname = line.substring(firstPos+1, pointer); //be careful of off-by-one, but the first one removes the $
				
				tokens.add(new ESVariableToken(varname));
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
						tokens.add(new ESIncrementOperator());
						pointer++;
					}
					else if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESAdditionAssignmentOperator());
						pointer++;
					}
					else tokens.add(new ESAdditionOperator());
					
					break;
				case '-': //-, --, or -=
					pointer++;
					if(line.charAt(pointer) == '-')
					{
						tokens.add(new ESDecrementOperator());
						pointer++;
					}
					else if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESSubtractionAssignmentOperator());
						pointer++;
					}
					else tokens.add(new ESSubtractionOperator());
					
					break;
				case '*': //* or *=
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESMultiplicationAssignmentOperator());
						pointer++;
					}
					else tokens.add(new ESMultiplicationOperator());
					
					break;
				case '/': // / or /=
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESDivisionAssignmentOperator());
						pointer++;
					}
					else tokens.add(new ESDivisionOperator());
					
					break;
				case '%': // % or %=
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESModulusAssignmentOperator());
						pointer++;
					}
					else tokens.add(new ESModulusOperator());
					
					break;
				case '=': //= or ==
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESEqualityOperator());
						pointer++;
					}
					else tokens.add(new ESAssignmentOperator());
					
					break;
				case '!': //! or !=
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESInequalityOperator());
						pointer++;
					}
					else tokens.add(new ESNotOperator());
					
					break;
				case '>': //> or >=
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESGreaterThanEqualityOperator());
						pointer++;
					}
					else tokens.add(new ESGreaterThanOperator());
					
					break;
				case '<': //< or <=
					pointer++;
					if(line.charAt(pointer) == '=')
					{
						tokens.add(new ESLesserThanEqualityOperator());
						pointer++;
					}
					else tokens.add(new ESLesserThanOperator());
					
					break;
				case '&': //&&
					pointer++;
					if(line.charAt(pointer) == '&')
					{
						tokens.add(new ESAndOperator());
						pointer++;
					}
					else throw new ESUnrecognizedTokenException();
					
					break;
				case '|': //||
					pointer++;
					if(line.charAt(pointer) == '|')
					{
						tokens.add(new ESOrOperator());
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
				
				String number = line.substring(firstPos, pointer); //be careful of off-by-one
				
				//if we hit a decimal, it's a float, otherwise, it's an int
				if(number.contains("."))
				{
					tokens.add(new ESValue(Float.parseFloat(number)));
				}
				else
				{
					tokens.add(new ESValue(Integer.parseInt(number)));
				}
				
				//DO NOT ADVANCE, we've landed after the number already
				//pointer++;
			}
			else if(line.charAt(pointer) == '"') //this might be iffy, keep an eye on it
			{
				//character is a quotation mark, must be beginning of a string, continue until we hit a non-escaped quotation mark
				int firstPos = pointer;
				
				do
				{
					pointer++;
				} while(!(line.charAt(pointer) == '"' && line.charAt(pointer-1) != '\\'));
				
				String str = line.substring(firstPos+1,pointer); //we only want the string itself
				
				tokens.add(new ESValue(new String(str)));
				
				pointer++; //again, we've landed on the ending quotation mark, not the one after
			}
			else if(Character.isAlphabetic(line.charAt(pointer)))
			{
				//character is a letter, must be a function call, continue until we hit whitespace or a bracket
				int firstPos = pointer;
				
				do
				{
					pointer++;
				} while(!(Character.isWhitespace(line.charAt(pointer)) || line.charAt(pointer) == '(' )); //I hope this is right
				
				String function = line.substring(firstPos,pointer);
				
				//handle named literals
				if(function.equalsIgnoreCase("true"))
				{
					tokens.add(new ESValue(new Boolean(true)));
				}
				else if(function.equalsIgnoreCase("false"))
				{
					tokens.add(new ESValue(new Boolean(false)));
				}
				else if(function.equalsIgnoreCase("null"))
				{
					tokens.add(new ESValue(null)); //currently this won't work so we're going to have to rethink how nulls are handled 
				}
				else //handle function calls
				{
					tokens.add(new ESCall(function));
				}				
			}
			else if(line.charAt(pointer) == '(')
			{
				//character is a bracket, must be the beginning of a sub-expression or explicit cast
				//count brackets and make it to the closing brace, then try to figure out what it is
				int firstPos = pointer;
				int bracketCount = 1; //we have the opening bracket only
				do
				{
					pointer++;
					if(line.charAt(pointer) == '(')
					{
						bracketCount++; //another opening bracket
					}
					else if(line.charAt(pointer) == ')')
					{
						bracketCount--; //a matching closing bracket
					}
				} while(bracketCount > 0);
				
				String subexpr = line.substring(firstPos+1,pointer); //we want everything inside the brackets
				
				//support explicit casts
				if(subexpr.equalsIgnoreCase("bool"))
				{
					tokens.add(new ESCastOperator(ESDataType.BOOLEAN));
				}
				else if(subexpr.equalsIgnoreCase("int"))
				{
					tokens.add(new ESCastOperator(ESDataType.INTEGER));
				}
				else if(subexpr.equalsIgnoreCase("float"))
				{
					tokens.add(new ESCastOperator(ESDataType.FLOAT));
				}
				else if(subexpr.equalsIgnoreCase("string"))
				{
					tokens.add(new ESCastOperator(ESDataType.STRING));
				}
				else
				{
					//it's actually a subexpression
					//add the subexpression
					tokens.add(tokenizeLine(subexpr)); //recursion!
				}
				
				pointer++; //advance the pointer when we're done with the expression because we landed ON the end, not AFTER it
			}
			else
			{
				//character is not recognizable, we broke something
				throw new ESUnrecognizedTokenException();
			}
			//curlybraces shouldn't happen because the script tokenizer should handle those
		}
		
		
		return new ESExpression(tokens);
	}

}
