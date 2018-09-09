package hw4;

import api.Card;

/**
 * Evaluator satisfied by any set of cards.  The number of
 * main cards is equal to the total cards.
 * 
 * The name of this evaluator is "Catch All".
 */
public class CatchAllEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param totalCards
   *   number of cards in a hand
   */
  public CatchAllEvaluator(int ranking, int totalCards)
  {
    super("Catch All", ranking, totalCards, totalCards);
  }

  @Override
  public boolean satisfiedBy(Card[] mainCards)
  {
    if (mainCards.length != numMainCards())
    {
      return false;
    }
    return true;
  }
  

}
