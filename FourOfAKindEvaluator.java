
package hw4;

/**
 * Evaluator for a hand containing (at least) four cards of the same rank.
 * The number of main cards is four.
 * 
 * The name of this evaluator is "Four of a Kind".
 */
public class FourOfAKindEvaluator extends OfAKindEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param totalCards
   *   number of cards in a hand
   */
  public FourOfAKindEvaluator(int ranking, int totalCards)
  {
    super("Four of a Kind", ranking, 4, totalCards);
  }
}
