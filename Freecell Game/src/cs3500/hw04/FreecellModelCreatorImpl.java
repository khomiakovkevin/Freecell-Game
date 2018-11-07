package cs3500.hw04;

import java.util.ArrayList;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.Pile;
import cs3500.hw02.PileType;

/**
 * Created by kevin on 18.02.17.
 */

/**
 * to represent a FreecellModelCreator Implementation.
 */
public class FreecellModelCreatorImpl extends FreecellModel {

  /**
   * Constructor all fields of which are the same as in FreecellModelCreator.
   */
  public FreecellModelCreatorImpl() {
    super();
  }

  @Override
  public void move(PileType sourceType, int sourcePileNumber, int cardIndex, PileType destType,
                   int destPileNumber) throws IllegalArgumentException {
    ArrayList<Card> cards = new ArrayList<>();
    if (!this.validBuild(sourcePileNumber, cardIndex)) {
      throw new IllegalArgumentException("The cards don't form a valid build");
    }
    if (!this.reachedMax(sourcePileNumber, cardIndex)) {
      throw new IllegalArgumentException("The space has reached maximum number");
    }
    for (int i = cardIndex; i < this.getPile(sourceType, sourcePileNumber).getsize(); i++) {
      cards.add(this.getPile(sourceType, sourcePileNumber).cards.get(i));
    }
    if (((destType == PileType.FOUNDATION) || (destType == PileType.OPEN)) &&
            cards.size() > 1) {
      throw new IllegalArgumentException("Only one card can be moved to open and" +
              " foundation piles");
    }
    if (this.getPile(sourceType, sourcePileNumber).emptycards()) {
      throw new IllegalArgumentException("This pile is empty");
    }
    if (this.getPile(destType, destPileNumber)
            .canbemoved(this.getPile(sourceType, sourcePileNumber)
            .cards.get(cardIndex))) {
      for (Card c : cards) {
        this.getPile(destType, destPileNumber).addcard(c, true);
      }
      for (int i = cards.size(); i > 0; i--) {
        this.getPile(sourceType, sourcePileNumber).removeHelp();
      }
    } else {
      throw new IllegalArgumentException("You cannot move like this");
    }
  }

  /**
   * to check the 1 condition: the cards should form a valid build.
   * @param cardIdx determines the current index of the card
   * @param pileIdx determines the current index of the pile
   * @return a boolean whether the cards form a valid build
   */
  private boolean validBuild(int pileIdx, int cardIdx) {
    Pile p = cascadepiles.get(pileIdx);
    if (cardIdx == p.getsize() - 1) {
      return true;
    }
    return p.cards.get(cardIdx).canMoveCascade(p.cards.get(cardIdx + 1))
            && this.validBuild(pileIdx, cardIdx + 1);
  }

  /**
   * to check the 2 condition: Max = (N+1)*2^K.
   * @param cardIdx determines the current index of the card
   * @param pileIdx determines the current index of the pile
   * @return a boolean whether the size has reached its maximum
   */
  private boolean reachedMax(int pileIdx, int cardIdx) {
    Pile p = cascadepiles.get(pileIdx);
    int mtopenpiles = 0;
    int mtcascadepiles = 0;
    for (Pile piles : cascadepiles) {
      if (piles.emptycards()) {
        mtcascadepiles++;
      }
    }
    for (Pile piles : openpiles) {
      if (piles.emptycards()) {
        mtopenpiles++;
      }
    }
    return p.getsize() - cardIdx <= (Math.pow(2, mtcascadepiles)) * (mtopenpiles + 1);
  }
}
