package hw4;

import api.Card;

/**
 * Abstract class for hands that require a fixed size group
 * of cards with matching rank.
 */
public class OfAKindEvaluator extends AbstractEvaluator
{
  /**
   * Constructs an evaluator with the given data.
   * @param name
   *   name for this evaluator
   * @param ranking
   *   ranking for this evaluator
   * @param cardsRequired
   *   number of main cards needed
   * @param handSize
   *   total number of cards in a hand
   */
  protected OfAKindEvaluator(String name, int ranking, int cardsRequired, int handSize)
  {
    super(name, ranking, cardsRequired, handSize);
  }

  @Override
  public boolean satisfiedBy(Card[] mainCards)
  {
    if (mainCards.length != numMainCards())
    {
      return false;
    }
    
    // cards are sorted, just check whether first and last are the same
    return (mainCards[0].getRank() == mainCards[mainCards.length - 1].getRank());
  }
}
