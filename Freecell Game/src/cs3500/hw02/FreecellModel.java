package cs3500.hw02;

/**
 * Created by kevin on 31.01.17.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * to make the methods.
 */
public class FreecellModel implements FreecellOperations<Card> {
  protected ArrayList<Pile> openpiles;
  protected ArrayList<Pile> cascadepiles;
  protected ArrayList<Pile> foundationpiles;
  protected boolean isstarted;

  /**
   * Constructor.
   */
  public FreecellModel(List<Card> deck, int numCascadePiles,
                       int numopenpiles, boolean shuffle) {
    if (deck == null) {
      deck = this.getDeck();
    }

    this.foundationpiles = new ArrayList<>();
    this.openpiles = new ArrayList<>();
    this.cascadepiles = new ArrayList<>();
    this.isstarted = false;

    this.startGame(deck, numCascadePiles, numopenpiles, shuffle);
  }
  
  public FreecellModel() {
    this.isstarted = false;
  }

  /**
   * to get the deck.
   * @return the deck of cards as a list
   */
  public List<Card> getDeck() {
    ArrayList<Card> answer = new ArrayList<>();
    for (Suits s : Suits.values()) {
      for (Values value : Values.values()) {
        answer.add(0, new Card(value, s));
      }
    }
    return answer;
  }

  /**
   * to check the Deck.
   */
  public void checkDeck(List<Card> deck) throws IllegalArgumentException {
    Set<Card> deckset = new HashSet<>(deck);
    if (deckset.size() < 52) {
      throw new IllegalArgumentException("some duplicates found");
    }
    if (deck.size() != 52) {
      throw new IllegalArgumentException("wrong number of cards");
    }
  }

  /**
   * to start a game.
   * @param numCascadePiles number of cascade piles
   * @param numOpenPiles    number of open piles
   * @param deck            the deck to be dealt
   * @param shuffle         if true, shuffle the deck else deal the deck as-is
   * @throws IllegalArgumentException if the deck is invalid
   */
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
          throws IllegalArgumentException {
    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("wrong number of O piles");
    }
    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("wrong number of C piles");
    }
    this.checkDeck(deck);
    this.cascadepiles = new ArrayList<>();
    this.openpiles = new ArrayList<>();
    this.foundationpiles = new ArrayList<>();
    for (int i  = 0; i < 4; i++) {
      foundationpiles.add(0, new Pile(PileType.FOUNDATION));
    }
    for (int i = 0; i < numOpenPiles; i++) {
      this.openpiles.add(0, new Pile(PileType.OPEN));
    }
    for (int i = 0; i < numCascadePiles; i++) {
      this.cascadepiles.add(0, new Pile(PileType.CASCADE));
    }
    if (shuffle) {
      this.shuffle(deck);
    }
    this.giveaway(deck);
    this.isstarted = true;
  }

  /**
   * to shuffle the C piles.
   */
  private void shuffle(List<Card> deck) {
    Collections.shuffle(deck);
  }
  
  /**
   * to give away the C piles.
   */
  private void giveaway(List<Card> deck) {
    for (int i = 0; i < deck.size(); i++) {
      this.cascadepiles.get(i % this.cascadepiles.size()).addcard(deck.get(i), true);
    }
  }

  /**
   * Move a card from the given source pile to the given destination pile, if
   * the move is valid.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible
   * {@link PileType})
   */

  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber) throws IllegalArgumentException {
    Pile sourcePile = this.getPile(source, pileNumber);
    Pile destPile = this.getPile(destination, destPileNumber);
    if (sourcePile.emptycards()) {
      throw new IllegalArgumentException("empty pile");
    }
    if (cardIndex != sourcePile.getsize() - 1) {
      throw new IllegalArgumentException("cannot be moved");
    }
    if (destPile.canbemoved(sourcePile.getfromtop())) {
      destPile.addcard(sourcePile.getfromtop(),true);
      sourcePile.removeHelp();
    }
    else {
      throw new IllegalArgumentException("The specified move is not allowed "
              + sourcePile.getfromtop() + " onto " + destination + " pile " + (destPileNumber + 1)
              + "\nThe current game state is :\n" + this.getGameState());
    }
  }


  /**
   * to get the pile.
   * @param pile reps the pile
   * @param index reps the idx
   * @return the pile
   * @throws IllegalArgumentException if idx<0
   */
  protected Pile getPile(PileType pile, int index) throws IllegalArgumentException {
    if (index < 0) {
      throw new IllegalArgumentException("wrong idx");
    }
    if (pile == PileType.OPEN) {
      if (index >= this.openpiles.size()) {
        throw new IllegalArgumentException("wrong number of O piles");
      }
      return this.openpiles.get(index);
    }
    if (pile == PileType.CASCADE) {
      if (index >= this.cascadepiles.size()) {
        throw new IllegalArgumentException("wrong number of C piles");
      }
      return this.cascadepiles.get(index);
    }
    else {
      if (index >= this.foundationpiles.size()) {
        throw new IllegalArgumentException("wrong number of F piles");
      }
      return this.foundationpiles.get(index);
    }
  }

  /**
   * Signal if the game is over or not.
   *
   * @return true if game is over, false otherwise.
   */
  public boolean isGameOver() {
    if (!this.isstarted) {
      return false;
    }
    for (Pile pile : this.openpiles) {
      if (!(pile.emptycards())) {
        return false;
      }
    }
    for (Pile pile : this.cascadepiles) {
      if (!(pile.emptycards())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Return the present state of the game as a string. The string is formatted
   * as follows:
   * <pre>
   * F1:[b]f11,[b]f12,[b],...,[b]f1n1[n] (Cards in foundation pile 1 in order)
   * F2:[b]f21,[b]f22,[b],...,[b]f2n2[n] (Cards in foundation pile 2 in order)
   * ...
   * Fm:[b]fm1,[b]fm2,[b],...,[b]fmnm[n] (Cards in foundation pile m in
   * order)
   * O1:[b]o11[n] (Cards in open pile 1)
   * O2:[b]o21[n] (Cards in open pile 2)
   * ...
   * Ok:[b]ok1[n] (Cards in open pile k)
   * C1:[b]c11,[b]c12,[b]...,[b]c1p1[n] (Cards in cascade pile 1 in order)
   * C2:[b]c21,[b]c22,[b]...,[b]c2p2[n] (Cards in cascade pile 2 in order)
   * ...
   * Cs:[b]cs1,[b]cs2,[b]...,[b]csps (Cards in cascade pile s in order)
   *
   * where [b] is a single blankspace, [n] is newline. Note that there is no
   * newline on the last line
   * </pre>
   * @return the formatted string as above
   */
  public String getGameState() {
    String answer = "";
    if (!this.isstarted) {
      return answer;
    }
    for (int i = 0; i < foundationpiles.size(); i++) {
      if (this.foundationpiles.get(i).emptycards()) {
        answer += "F" + (i + 1) + ":\n";
      }
      else {
        answer += "F" + (i + 1) + ": " + this.foundationpiles.get(i) + "\n";
      }
    }
    for (int i = 0; i < openpiles.size(); i++) {
      if (this.openpiles.get(i).emptycards()) {
        answer += "O" + (i + 1) + ":\n";
      }
      else {
        answer += "O" + (i + 1) + ": " + this.openpiles.get(i) + "\n";
      }
    }
    for (int i = 0; i < cascadepiles.size(); i++) {
      if (i == cascadepiles.size() - 1) {
        answer += "C" + (i + 1) + ": " + this.cascadepiles.get(i);
      }
      else {
        answer += "C" + (i + 1) + ": " + this.cascadepiles.get(i) + "\n";
      }
    }
    return answer;
  }
}
