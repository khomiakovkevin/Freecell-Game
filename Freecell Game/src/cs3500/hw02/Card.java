package cs3500.hw02;

/**
 * Created by kevin on 31.01.17.
 */

/**
 * to represent a Card.
 */

public class Card {
  private Values value;
  private Suits suit;

  /**
   * Constructor.
   */
  public Card(Values value, Suits suit) {
    this.value = value;
    this.suit = suit;
  }

  /**
   * @return value.
   */
  private Values getvalue() {
    return this.value;
  }

  /**
   *
   * @return suit.
   */
  private Suits getsuit() {
    return this.suit;
  }

  @Override
  public String toString() {
    String answer = "";
    switch (this.value) {
      case ACE:
        answer += "A";
        break;
      case TWO:
        answer += "2";
        break;
      case THREE:
        answer += "3";
        break;
      case FOUR:
        answer += "4";
        break;
      case FIVE:
        answer += "5";
        break;
      case SIX:
        answer += "6";
        break;
      case SEVEN:
        answer += "7";
        break;
      case EIGHT:
        answer += "8";
        break;
      case NINE:
        answer += "9";
        break;
      case TEN:
        answer += "10";
        break;
      case JACK:
        answer += "J";
        break;
      case QUEEN:
        answer += "Q";
        break;
      case KING:
        answer += "K";
        break;
      default:
        throw new RuntimeException("default case Values");
    }

    switch (this.suit) {
      case CLUBS:
        answer += "♣";
        break;
      case DIAMONDS:
        answer += "♦";
        break;
      case HEARTS:
        answer += "♥";
        break;
      case SPADES:
        answer += "♠";
        break;
      default:
        throw new RuntimeException("default case Suits");
    }
    return answer;
  }

  /**
   * to check if a card can be a base
   * @return the boolean whether the value is equal to Ace.
   */
  public boolean canbebase() {
    return this.value == Values.ACE;
  }


  /**
   * to cjeck if it can move.
   * @param other is another card.
   * @return the boolean whether it can move
   */
  public boolean canmove(Card other) {
    return (this.value.ordinal() - other.getvalue().ordinal()) == 1;
  }

  /**
   * to check is C piles can be moved
   * @return a boolean whether we can move C piles.
   */
  public boolean canMoveCascade(Card other) {
    return (this.canmove(other) && this.oppositeColor(other));
  }

  /**
   *
   * @return a boolean whether we can move F piles.
   */
  public boolean canMoveFoundation(Card other) {
    return (other.canmove(this) && (this.suit == other.getsuit()));
  }

  /**
   *
   * @return a boolean whether 2 cards have oppposite colors.
   */
  private boolean oppositeColor(Card other) {
    if ((this.suit == Suits.CLUBS) || (this.suit == Suits.SPADES)) {
      return ((other.getsuit() == Suits.DIAMONDS) || (other.getsuit() == Suits.HEARTS));
    }
    else {
      return ((other.getsuit() == Suits.CLUBS) || (other.getsuit() == Suits.SPADES));
    }
  }
}
