package hw4;

import api.Card;

/**
 * Evaluator for a hand containing (at least) two cards of the same rank.
 * The number of main cards is two.
 * 
 * The name of this evaluator is "One Pair".
 */
public class OnePairEvaluator extends OfAKindEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param totalCards
   *   number of cards in a hand
   */
  public OnePairEvaluator(int ranking, int totalCards)
  {
    super("One Pair", ranking, 2, totalCards);
  }
}
