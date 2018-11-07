package cs3500.hw02;

import java.util.ArrayList;

/**
 * to represent a Pile.
 */
public class Pile {
  public ArrayList<Card> cards;
  PileType piles;

  /**
   * Constructor.
   */
  public Pile(PileType piles) {
    this.cards = new ArrayList<>();
    this.piles = piles;
  }

  /**
   * Constructor.
   */
  public Pile(ArrayList<Card> cards, PileType piles) {
    this.cards = cards;
    this.piles = piles;
  }


  /**
   * to get the size.
   * @return the size.
   */
  public int getsize() {
    return this.cards.size();
  }

  /**
   * @return a boolean whether the list of cards is empty.
   */
  public boolean emptycards() {
    return this.cards.isEmpty();
  }

  /**
   * @return a boolean whether the card can be moved.
   */
  public boolean canbemoved(Card other) {
    if (this.piles == PileType.OPEN) {
      return emptycards();
    }
    if ((this.piles == PileType.FOUNDATION) && emptycards()) {
      return other.canbebase();
    }

    if ((this.piles == PileType.FOUNDATION)
            && this.cards.get(cards.size() - 1).canMoveFoundation(other)) {
      return true;
    }
    if ((this.piles == PileType.CASCADE) && emptycards()) {
      return true;
    }
    return ((this.piles == PileType.CASCADE)
            && this.cards.get(cards.size() - 1).canMoveCascade(other));
  }


  /**
   * to add a card.
   * @param other is an other card.
   * @param canbemoved is a boolean which determines whether it can be moved.
   */
  public void addcard(Card other, boolean canbemoved) {
    if (canbemoved) {
      this.cards.add(other);
    }
    else {
      if (this.canbemoved(other)) {
        this.cards.add(other);
      } else {
        throw new IllegalArgumentException("cannot be moved");
      }
    }
  }

  /**
   * to remove 1 card from the top.
   */
  public void removeHelp() {
    if (emptycards()) {
      return;
    }
    this.cards.remove(this.cards.size() - 1);
  }


  /**
   * to get from top.
   * @return the card from the top.
   */
  public Card getfromtop() {
    return this.cards.get(this.cards.size() - 1);
  }

  @Override
  public String toString() {
    String answer = "";
    if (emptycards()) {
      return answer;
    }
    else {
      for (int i = 0; i < this.cards.size(); i++) {
        if (i != this.cards.size() - 1) {
          answer += this.cards.get(i);
          answer += ", ";
        }
        else {
          answer += this.cards.get(i);
        }
      }
    }
    return answer;
  }
}
