package hw4;
import java.util.ArrayList;
import java.util.Arrays;

import api.Card;
import api.Hand;
import api.IEvaluator;
import api.SubsetFinder;
import api.Util;

/**
 * The class AbstractEvaluator includes common code for all evaluator types.
 * All methods are implemented here except canSatisfy.  Most of the concrete
 * evaluator types extend this class directly. However, the classes OnePairEvaluator,
 * ThreeOfAKindEvaluator, and FourOfAKindEvaluator extend a common abstract
 * class OfAKindEvaluator. Also, StraightFlushEvaluator extends
 * StraightEvaluator.  Since the StraightEvaluator constructor
 * doesn't take a name argument, there a second protected constructor
 * for StraightEvaluator that allows the name to be set when called
 * from StraightFlushEvaluator.
 * 
 * The only method that is overridden in the eight concrete subclasses
 * is canSatisfy, with two exceptions:  The method createHand is 
 * overridden in FullHouseEvaluator and in StraightEvaluator to 
 * take care of the two cases described on p. 5 of the pdf.
 */
public abstract class AbstractEvaluator implements IEvaluator
{
  /**
   * Name of this evaluator.
   */
  private String name;
  
  /**
   * Ranking of this evaluator.
   */
  private int ranking;
  
  /**
   * Number of cards required for this evaluator's canSatisfy method.
   */
  private int cardsRequired;
  
  /**
   * Total number of cards in a hand produced by this evaluator.
   */
  private int handSize;
  
  /**
   * Constructs an evaluator with the given data.  This constructor can
   * only be invoked by subclasses.
   * 
   * @param name
   *   name for this evaluator
   * @param ranking
   *   ranking for this evaluator
   * @param cardsRequired
   *   number of main cards needed
   * @param totalCards
   *   total number of cards in a hand
   */
  protected AbstractEvaluator(String name, int ranking, int cardsRequired, int totalCards)
  {
    this.name = name;
    this.ranking = ranking;
    this.cardsRequired = cardsRequired;
    this.handSize = totalCards;
  }
  
  @Override
  public String getName()
  {
    return name;
  }

  @Override
  public int getRanking()
  {
    return ranking;
  }

  @Override
  public int numMainCards()
  {
    return cardsRequired;
  }

  @Override
  public int totalCards()
  {
    return handSize;
  }

  @Override
  public boolean canSubsetSatisfy(Card[] allCards)
  {
    if (allCards.length < numMainCards())
    {
      return false;
    }

    // for each subset of required size, if canSatisfy is true
    // for that set of cards, then return true
    ArrayList<int[]> subsets = SubsetFinder.findSubsets(allCards.length, numMainCards());
    for (int[] subset : subsets)
    {
      Card[] mainCards = Util.getCardSubset(allCards, subset);
      if (satisfiedBy(mainCards))
      {
        return true;
      }
    }
    
    // did not find one...
    return false;
  }
  
  @Override
  public Hand createHand(Card[] allCards, int[] subset)
  {
    // need to have enough cards in total, and the selected 
    // subset needs to be the right size
    if (allCards.length < totalCards() || subset.length != numMainCards())
    {
      return null;
    }

    Card[] mainCards = Util.getCardSubset(allCards, subset);
    if (satisfiedBy(mainCards))
    {
      // get the potential side cards
      Card[] nonSelectedCards = Util.getCardNonSubset(allCards, subset);

      // there may be more extra cards than we need, take the best ones
      int numSideCards = totalCards() - numMainCards();
      Card[] sideCards = Arrays.copyOf(nonSelectedCards, numSideCards);
      
      // make the hand
      return new Hand(mainCards, sideCards, getName(), getRanking());
    }
    else
    {
      return null;
    }
  }
    
  @Override
  public Hand createBestHand(Card[] allCards)
  {
    if (allCards.length < totalCards())
    {
      // not enough cards given
      return null;
    }    
    
    // for each subset of required size, create a Hand, and keep it if
    // it's the best one so far
    Hand best = null;
    ArrayList<int[]> subsets = SubsetFinder.findSubsets(allCards.length, numMainCards());
    for (int[] subset : subsets)
    {
      Card[] mainCards = Util.getCardSubset(allCards, subset);
      if (satisfiedBy(mainCards))
      {
        Hand result = createHand(allCards, subset);
        if (best == null || result != null && result.compareTo(best) < 0)
        {
          best = result;
        }
      }
    }
    
    return best;
  }
  
}
