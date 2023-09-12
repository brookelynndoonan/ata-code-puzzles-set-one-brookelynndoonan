package com.kenzie.gettingstarted.balance;

import java.util.List;
import java.util.Stack;

/**
 * Drills to apply the problem-solving framework on variations of the Balanced Parentheses problem.
 */
public class BalancedParens {

    /**
     * Determine whether a string composed entirely of opening and closing parentheses is balanced.
     * If a closing paren appears without an opening paren, the string is not balanced.
     * If an opening paren is not closed by the end of the string, it is not balanced.
     * <p>
     * BalancedParensTest includes some sample test cases.*
     *
     * @param parens A String of opening and closing parentheses
     * @return true if every opening and closing paren has a partner
     */
    public boolean areParensBalanced(String parens) {
        int count = 0;
        for (char c : parens.toCharArray()) {
            if (c == '(') {
                count++;
            }
            if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    /**
     * Placeholder for the we-do classroom activity for Problem-Solving Framework.
     * <p>
     * BalancedParensTest includes some sample test cases.
     *
     * @param text A text String, described in the classroom.
     * @return True as described in the classroom, false otherwise.
     */
    public boolean balancedGroupParen(String text) {

        if (text.isEmpty())
            return true;

        Stack<Character> paren = new Stack<>();

        for (char character : text.toCharArray()) {

            if (character == '(' || character == '{' || character == '[') {
                paren.push(character);
            }

            if (character == ')' || character == '}' || character == ']') {
                if (paren.isEmpty()) {
                    return false;
                }

                char end = paren.pop();
                if (end != '(' && character == ')' || end != '{' && character == '}' ||
                        end != '[' && character == ']') {
                    return false;
                }
            }

        }
        return paren.isEmpty();
    }


    /**
     * Placeholder for the you-do classroom activity for Problem-Solving Framework.
     * <p>
     * BalancedParensTest includes some sample test cases.
     *
     * @param text A text String, described in the classroom.
     * @return True as described in the classroom, false otherwise.
     */

    // Jacobus helped me build the boolean flags and broke down how the characters go through the loop in order to go
    // through the if statements and how these flags function whether they come across the required character or not,
    // before cycling through again and moving onto the next if checks.
    public boolean smileyFaces(String text) {
        boolean colonCharacter = false;
        if (text.isEmpty())
            return true;

        Stack<Character> paren = new Stack<>();

        for (char character : text.toCharArray()) {
            if (character == ':') {
                colonCharacter = true;
                continue;
            }
            if (colonCharacter) {
                colonCharacter = false;
                continue;
            }

            if (character == '(' || character == '{' || character == '[') {
                paren.push(character);

            }
            if (character == ')' || character == '}' || character == ']') {
                if (paren.isEmpty()) {
                    return false;
                }

                char end = paren.pop();
                if (end != '(' && character == ')' || end != '{' && character == '}' ||
                        end != '[' && character == ']') {
                    return false;
                }
            }
        }
        return paren.isEmpty();
    }


}
