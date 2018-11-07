package cs3500.hw03;

import cs3500.hw02.PileType;

/**
 * Created by kevin on 08.02.17.
 */
public class MoveHelp {
  
  PileType source;
  int pileNumber;
  int cardIndex;
  PileType destination;
  int destPileNumber;

  /**
   * constructor for MoveHelp.
   * @param movetoIdx helps to move
   */
  public MoveHelp(String[] movetoIdx) {
    String sourcePile = movetoIdx[0];
    String cardIndex = movetoIdx[1];
    String destPile = movetoIdx[2];

    this.source = this.getPile(sourcePile);
    this.cardIndex = Integer.parseInt(cardIndex);
    this.destination = this.getPile(destPile);

    this.pileNumber = Integer.parseInt(sourcePile.substring(1)) - 1;
    if (this.pileNumber < 0) {
      throw new IllegalArgumentException("pileNumber cannot be negative");
    }
    if (this.cardIndex < 0) {
      throw new IllegalArgumentException("cardIdx cannot be negative");
    }
    this.destPileNumber = Integer.parseInt(destPile.substring(1)) - 1;
    if (this.destPileNumber < 0) {
      throw new IllegalArgumentException(("destPileNumber cannot be negative"));
    }
    if (sourcePile == null || cardIndex == null || destPile == null) {
      throw new IllegalArgumentException("Inputs cannot be null");
    }
  }


  /**
   * to get the pileType at given string.
   * @param in describes the input string
   * @return the pileType
   */
  public PileType getPile(String in) {
    if (in.equals('C')) {
      return PileType.CASCADE;
    }
    else if (in.equals('F')) {
      return PileType.FOUNDATION;
    }
    else if (in.equals('O')) {
      return PileType.OPEN;
    }
    else {
      throw new IllegalArgumentException("Wrong Input");
    }
  }
}