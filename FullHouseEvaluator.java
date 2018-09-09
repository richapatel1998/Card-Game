package hw4;

import api.Card;
import api.Hand;

/**
 * Evaluator for a generalized full house.  The number of main
 * cards is equal to the total cards.  If the total cards N is an even number,
 * then the cards must consist of two groups of N / 2 cards that have matching rank.  
 * If N is an odd number, then the cards must consist of two groups, one of 
 * (N / 2) + 1 cards of matching rank and other of (N / 2) cards of matching rank. 
 * (This evaluator is always satisfied when all cards are of the same rank.)
 * In the case that N is odd, when creating
 * a hand, <strong>the larger group must be listed first even if of lower rank
 * than the smaller group</strong> (e.g. as [3 3 3 5 5] rather than [5 5 3 3 3]).
 * 
 * The name of this evaluator is "Full House".
 */
public class FullHouseEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param totalCards
   *   number of cards in a hand
   */
  public FullHouseEvaluator(int ranking, int totalCards)
  {
    super("Full House", ranking, totalCards, totalCards);
  }

  @Override
  public boolean satisfiedBy(Card[] mainCards)
  {
    if (mainCards.length != totalCards())
    {
      return false;
    }
    if (mainCards.length <= 2)
    {
      return true;
    }
    
    int mid = mainCards.length / 2;
    int end = mainCards.length - 1;

    // check the following:
    //   - all values up to the middle are the same
    //   - all values to the right of the middle are the same
    //   - if length is odd, middle value matches EITHER the left half or the right half
    if (mainCards.length % 2 == 0)
    {
      // even hand size
      return sameRank(mainCards, 0, mid - 1) && 
             sameRank(mainCards, mid, end);
    }
    else
    {
      // odd hand size
      return sameRank(mainCards, 0, mid - 1) && 
             sameRank(mainCards, mid + 1, end) &&
             (sameRank(mainCards, mid - 1, mid) ||
              sameRank(mainCards, mid, mid + 1));
    }
  }
  
  @Override
  public Hand createHand(Card[] allCards, int[] subset)
  {
    // create the hand using the AbstractEvaluator
    Hand hand = super.createHand(allCards, subset);    
    
    // if size of hand is odd, the larger group of matching cards needs to come 
    // at the beginning
    if (hand != null && totalCards() % 2 != 0)
    {
      Card[] cards = hand.getMainCards();
      int mid = cards.length / 2;
      if (cards[mid].getRank() != cards[mid - 1].getRank())
      {
        // smaller "half" is at beginning, so swap them around
        Card[] temp = new Card[cards.length];
        for (int i = 0; i < mid; ++i)
        {
          temp[mid + i + 1] = cards[i];
        }
        for (int i = mid; i < cards.length; ++i)
        {
          temp[i - mid] = cards[i];
        }
        hand = new Hand(temp, new Card[]{}, getName(), getRanking());
      }
    }
    return hand;
  }
  
  /**
   * Returns true if the starting and ending ranks match.
   * @param arr
   *   given array of cards
   * @param start
   *   starting index to check
   * @param end
   *   ending index to check
   * @return
   *   true if the ranks are the same for cards at start and end
   */
  private boolean sameRank(Card[] arr, int start, int end)
  {
    return arr[start].getRank() == arr[end].getRank();
  }
}