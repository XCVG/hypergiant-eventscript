package com.xcvgsystems.hypergiant.eventscript;

/**
 * Precedence values for an operation.
 * subexpressions (not an operator)
 * calls (not an operator)
 * unary operators (including casts)
 * div/mul operators
 * add/sub operators
 * comparison operators (including equality)
 * boolean operators (AND, OR)
 * assignment operators
 * This may change later
 * 
 * @author Chris
 *
 */
public enum ESPrecedence
{
	UNARY, MULTIPLICATIVE, ADDITIVE, COMPARISON, LOGICAL, ASSIGNMENT //the order here is VERY CRITICAL (maybe)
}
