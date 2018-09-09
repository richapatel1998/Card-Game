package hw4;

import api.Card;

/**
 * Evaluator for a hand in which the rank of each card is a prime number.
 * The number of main cards is equal to the total cards. 
 * 
 * The name of this evaluator is "All Primes".
 */
public class AllPrimesEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param totalCards
   *   number of cards in a hand
   */
  public AllPrimesEvaluator(int ranking, int totalCards)
  {
    super("All Primes", ranking, totalCards, totalCards);
  }

  @Override
  public boolean satisfiedBy(Card[] mainCards)
  {
    if (mainCards.length != numMainCards())
    {
      return false;
    }
    for (int i = 0; i < mainCards.length; ++i)
    {
      if (!isPrime(mainCards[i].getRank()))
      {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Returns true if the given integer is prime.
   * @param n
   *   given integer
   * @return
   *   true if the given integer is prime, false otherwise
   */
  private boolean isPrime(int n)
  {
    if (n <= 1) return false;
    if (n == 2) return true;
    for (int i = 2; i * i <= n ; i += 1)
    {
      if (n % i == 0)
      {
        return false;
      }
    }
    return true;
  }
}
