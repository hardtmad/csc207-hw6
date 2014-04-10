package problem2;

/**
 * Object to keep track of a symbol and its index in a string
 */

public class Tracker
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  char symbol;
  int index;
  int symbolCategory;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new tracker
   */
  public Tracker(char s, int i, int symbolCategory) 
  {
    this.symbol = s;
    this.index = i;
    this.symbolCategory = 0;
  }
  
  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+
  
  public char getSymbol ()
  {
    return this.symbol;
  }
  
  public int getIndex ()
  {
    return this.index;
  }
  
  public int getSymbolCategory ()
  {
    return this.symbolCategory;
  }

}
