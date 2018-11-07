package cs3500.hw02;

/**
 * to test methods
 */
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FreecellModelTest {

  FreecellModel model0 = new FreecellModel();
  FreecellModel model1;
  FreecellModel model2;
  FreecellModel model3;
  FreecellModel model4;
  FreecellModel model5;
  FreecellModel model6;
  FreecellModel model7;

  List<Card> mt = new ArrayList<>();
  List<Card> deck = new ArrayList<>();
  List<Card> duplicates;

  /**
   * intial data.
   */
  @Before
  public void init() {
    deck = model0.getDeck();
    model1 = new FreecellModel(deck, 8, 4, false);
    model2 = new FreecellModel();
    model4 = new FreecellModel();
    model3 = new FreecellModel(duplicates, 4, 1, false);
    getdupes();
    model2.startGame(model2.getDeck(), 4, 4, false);
    model4.startGame(model4.getDeck(), 52, 1, false);
    model5 = new FreecellModel();
    model5.startGame(model0.getDeck(), 8, 4, true);
    model6 = new FreecellModel();
    model7 = new FreecellModel();
    model7.startGame(deck, 6, 4, false);
  }

  public void getdupes() {
    Card c = new Card(Values.ACE, Suits.DIAMONDS);
    duplicates = new ArrayList<Card>(Collections.nCopies(52, c));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException1() {
    model1.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException2() {
    model1.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException3() {
    model1.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    model1.move(PileType.CASCADE, 1, 3, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException4() {
    model1.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException5() {
    model1.move(PileType.CASCADE, 1, 3, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkSmallDeck() {
    model0.startGame(mt, 8, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkduplicates() {
    model0.startGame(duplicates, 8, 4, false);
  }

  @Test
  public void testisgameover() {
    assertEquals(model1.isGameOver(), false);
  }

  @Test
  public void teststartgame() {
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♥, 5♥, 10♦, 2♦, 7♣, Q♠, 4♠\n"
            + "C2: Q♥, 4♥, 9♦, A♦, 6♣, J♠, 3♠\n"
            + "C3: J♥, 3♥, 8♦, K♣, 5♣, 10♠, 2♠\n"
            + "C4: 10♥, 2♥, 7♦, Q♣, 4♣, 9♠, A♠\n"
            + "C5: 9♥, A♥, 6♦, J♣, 3♣, 8♠\n"
            + "C6: 8♥, K♦, 5♦, 10♣, 2♣, 7♠\n"
            + "C7: 7♥, Q♦, 4♦, 9♣, A♣, 6♠\n"
            + "C8: 6♥, J♦, 3♦, 8♣, K♠, 5♠", model1.getGameState());
    assertEquals(52, model2.getDeck().size());
  }

  @Test
  public void testmove() {
    model1.move(PileType.CASCADE, 1, 6, PileType.OPEN, 2);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3: 3♠\n"
            + "O4:\n"
            + "C1: K♥, 5♥, 10♦, 2♦, 7♣, Q♠, 4♠\n"
            + "C2: Q♥, 4♥, 9♦, A♦, 6♣, J♠\n"
            + "C3: J♥, 3♥, 8♦, K♣, 5♣, 10♠, 2♠\n"
            + "C4: 10♥, 2♥, 7♦, Q♣, 4♣, 9♠, A♠\n"
            + "C5: 9♥, A♥, 6♦, J♣, 3♣, 8♠\n"
            + "C6: 8♥, K♦, 5♦, 10♣, 2♣, 7♠\n"
            + "C7: 7♥, Q♦, 4♦, 9♣, A♣, 6♠\n"
            + "C8: 6♥, J♦, 3♦, 8♣, K♠, 5♠", model1.getGameState());
    model1.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    assertEquals("F1: A♠\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3: 3♠\n"
            + "O4:\n"
            + "C1: K♥, 5♥, 10♦, 2♦, 7♣, Q♠, 4♠\n"
            + "C2: Q♥, 4♥, 9♦, A♦, 6♣, J♠\n"
            + "C3: J♥, 3♥, 8♦, K♣, 5♣, 10♠, 2♠\n"
            + "C4: 10♥, 2♥, 7♦, Q♣, 4♣, 9♠\n"
            + "C5: 9♥, A♥, 6♦, J♣, 3♣, 8♠\n"
            + "C6: 8♥, K♦, 5♦, 10♣, 2♣, 7♠\n"
            + "C7: 7♥, Q♦, 4♦, 9♣, A♣, 6♠\n"
            + "C8: 6♥, J♦, 3♦, 8♣, K♠, 5♠", model1.getGameState());
  }
}

