package hw4;

import api.Card;

/**
 * Evaluator for a hand that contains at least one card from
 * each suit.  The number of main cards is four.
 * 
 * The name of this evaluator is "All Suits".
 */
public class AllSuitsEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this type of hand
   * @param totalCards
   *   total number of cards in a hand
   */
  public AllSuitsEvaluator(int ranking, int totalCards)
  {
    super("All Suits", ranking, 4, totalCards);
  }

  @Override
  public boolean satisfiedBy(Card[] mainCards) 
  { 
    boolean[] found = new boolean[4];
    for (Card c : mainCards)
    {
      // the ordinal positions are 0 for CLUBS, 1 for DIAMONDS, 
      // 2 for HEARTS, and 3 for SPADES
      int index = c.getSuit().ordinal();
      found[index] = true;
    }
    
    // did we find them all?
    return found[0] && found[1] && found[2] && found[3];
  }
}
