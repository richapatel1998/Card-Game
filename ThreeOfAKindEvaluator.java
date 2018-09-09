package hw4;

/**
 * Evaluator for a hand containing (at least) three cards of the same rank.
 * The number of cards required is three.
 * 
 * The name of this evaluator is "Three of a Kind".
 */
public class ThreeOfAKindEvaluator extends OfAKindEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param totalCards
   *   number of cards in a hand
   */
  public ThreeOfAKindEvaluator(int ranking, int totalCards)
  {
    super("Three of a Kind", ranking, 3, totalCards);
  }
}
