package problem2;

import java.io.PrintWriter;
import java.io.BufferedReader;

/**
 * Procedure that takes a string as input and prints out a picture that shows
 * the nesting.
 */

public class MatchingParentheses
{
  PrintWriter pen = new PrintWriter(System.out, true);
  BufferedReader eyes =
      new BufferedReader(new java.io.InputStreamReader(System.in));

  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * String entered by user
   */
  String str;

  /**
   * Stack to keep track of symbols and indices
   */
  Stacks<Object> stack;

  /**
   * Current position in str
   */
  int pos;

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+

  public void checkParens(String input)
    throws Exception
  {
    if (input == null)
      {
        pen.println("Enter a string: ");
        input = eyes.readLine();
      } // if
    checkParens(input);
    for (int index = 0; index < input.length(); index++)
      {
        char character = input.charAt(index);
        int category = symbolType(character);
        // account for '
        if (character == '\'')
          {
            Tracker topObj = (Tracker) stack.get();
            if (topObj.getSymbol() == '\'')
              {
                category = 1;
              } // if
            else
              {
                stack.put(topObj);
                category = 0;
              } // else
          } // if
        if (category == 0)
          {
            stack.put(new Tracker(character, index, category));
          } // if
        else if (category == 1)
          {
            Tracker openObj = (Tracker) stack.get();

            pen.print(makeRepetitiveString(openObj.getIndex(), " "));
            pen.print(openObj.getSymbol());
            pen.print(makeRepetitiveString(index - openObj.getIndex(), "-"));
            pen.print(character);
            if (!isMatch(openObj.getSymbol(), character))
              {
                pen.print("<-- incorrect match");
                break;
              } // if
            pen.println();
          } // else if
      } // for
    pen.close();
  } // checkParens (String)

  // +----------------+--------------------------------------------------
  // | Helper Methods |
  // +----------------+

  /**
   * Sorts symbols into categories. Returns 0 if c is an opening supported
   * character, 1 if c is a closing supported character, 2 if c is a single
   * quotation mark, and 3 if c is not a supported symbol
   */
  public int symbolType(char c)
  {
    if ((c == '(') || (c == '[') || (c == '{') || (c == '<'))
      {
        return 0;
      } // if
    else if ((c == ')') || (c == ']') || (c == '}') || (c == '>'))
      {
        return 1;
      } // else if
    else if (c == '\'')
      {
        return 2;
      } // else if
    else
      {
        return 3;
      } // else
  } // int symbolType (char)

  /**
   * Makes a string that contains the string str length number of times
   */
  public String makeRepetitiveString(int length, String str)
  {
    String repeat = "";

    for (int i = 0; i < length; i++)
      {
        repeat += str;
      } // for
    return repeat;
  } // makeRepetitiveString (int, String)

  public boolean isMatch(char openSymbol, char closeSymbol)
    throws Exception
  {
    switch (openSymbol)
      {
        case '(':
          if (closeSymbol == ')')
            return true;
          else
            return false;
        case '[':
          if (closeSymbol == ']')
            return true;
          else
            return false;
        case '{':
          if (closeSymbol == '}')
            return true;
          else
            return false;
        case '<':
          if (closeSymbol == '>')
            return true;
          else
            return false;
        case '\'':
          if (closeSymbol == '\'')
            return true;
          else
            return false;
        default:
          throw new Exception("Unsupported Symbol");
      } // switch (openSymbol)
  } // isMatch (char, char)

} // class MatchingParentheses

