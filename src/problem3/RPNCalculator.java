package problem3;

import java.io.BufferedReader;
import java.io.PrintWriter;

import problem2.Stacks;

public class RPNCalculator
{

  PrintWriter pen = new PrintWriter(System.out, true);
  BufferedReader eyes =
      new BufferedReader(new java.io.InputStreamReader(System.in));

  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * Stack to keep of operands and operations
   */
  Stacks<Object> stack;

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+

  public int calculate()
    throws Exception
  {
    boolean state = true;
    int soFar = 0;

    while (state)
      {
        pen.println("i -- input a new calculation");
        pen.println("p -- print top value on stack");
        pen.println("s -- print the whole stack");
        pen.println("c -- clear the stack");
        pen.println("m -- mutate last value");
        pen.println("q -- quit");
        pen.println("Enter a command letter: ");

        char op = eyes.readLine().charAt(0);

        switch (op)
          {
            case 'i':
              {
                pen.println("Input a calculation and press enter when you are finished: ");
                String calculation = eyes.readLine();
                stackAndCalc(calculation, 0);
                break;
              } // case i
            case 'p':
              {
                Object obj = stack.get();
                pen.println(obj);
                stack.put(obj);
                break;
              } // case p
            case 's':
              {
                while (!stack.isEmpty())
                  {
                    pen.print(stack.get() + " ");
                  } // while
                pen.println();
                break;
              } // case s
            case 'c':
              {
                while (!stack.isEmpty())
                  {
                    stack.get();
                  } // while
                break;
              } // case c
            case 'm':
              {
                pen.println("Add to the current calculation: " + soFar);
                String calculation = eyes.readLine();
                stackAndCalc(calculation, soFar);
              }
            default:
              {
                pen.println("Unsupported command.");
                break;
              } // default
          } // switch
      } // while

    return soFar;
  }

  // +----------------+--------------------------------------------------
  // | Helper Methods |
  // +----------------+

  public boolean isFunction(char c)
  {
    if ((c == '+') || (c == '-') || (c == '*') || (c == '/'))
      {
        return true;
      } // if
    else
      {
        return false;
      } // else
  } // isFunction

  public int stackAndCalc(String calculation, int soFar)
    throws Exception
  {
    for (int i = 0; i < calculation.length(); i++)
      {
        char ch = calculation.charAt(i);
        if (ch == ' ')
          {
            ;
          } // if
        else if (isFunction(ch))
          {
            switch (ch)
              {
                case '+':
                  {
                    soFar = (int) stack.get() + (int) stack.get();
                    break;
                  } // case +
                case '-':
                  {
                    soFar = (int) stack.get() - (int) stack.get();
                    break;
                  } // case -
                case '*':
                  {
                    soFar = (int) stack.get() * (int) stack.get();
                    break;
                  } // case *
                case '/':
                  {
                    soFar = (int) stack.get() / (int) stack.get();
                    break;
                  } // case /
                default:
                  {
                    throw new Exception("Unsupported opperation");
                  } // default
              } // switch (ch)
          } // else if
        else
          {
            try
              {
                stack.put((Object) ch);
              } // try
            catch (Exception e)
              {
                throw new Exception(ch
                                    + " is neither an operand nor an operation");
              } // catch
          } // else
      } // for
    stack.put(soFar);
    return soFar;
  }// stackAndCalcu

} // class RPNCalculator
