package cs3500.hw04;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import cs3500.hw02.Card;
import cs3500.hw02.PileType;
import cs3500.hw02.Suits;
import cs3500.hw02.Values;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevin on 18.02.17.
 */

/**
 * to test all the exceptions and abilities of moving cards through different models.
 */
public class FreecellModelCreatorImplTest {

  FreecellModelCreatorImpl model1;
  FreecellModelCreatorImpl model2;
  ArrayList<Card> cards;

  /**
   * Initial data.
   */
  @Before
  public void init() {
    cards = new ArrayList<>();
    int num = 0;
    int val = 12;
    while (cards.size() <= 51) {
      if (num / 2 * 2 == num) {
        cards.add(new Card(Values.values()[val], Suits.DIAMONDS));
        cards.add(new Card(Values.values()[val], Suits.CLUBS));
        cards.add(new Card(Values.values()[val], Suits.HEARTS));
        cards.add(new Card(Values.values()[val], Suits.SPADES));
        num++;
        val--;
      }
      else {
        cards.add(new Card(Values.values()[val], Suits.SPADES));
        cards.add(new Card(Values.values()[val], Suits.HEARTS));
        cards.add(new Card(Values.values()[val], Suits.CLUBS));
        cards.add(new Card(Values.values()[val], Suits.DIAMONDS));
        num++;
        val--;
      }
    }
    model1 = new FreecellModelCreatorImpl();
    model1.startGame(this.cards, 4, 1, false);
    model2 = new FreecellModelCreatorImpl();
    model2.startGame(model2.getDeck(), 52, 4, false);

  }

  /**
   * to catch the exception while the source and dest are nulls.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test1() {
    model2.startGame(model2.getDeck(), 4, 2, false);
    model2.move(PileType.CASCADE, 0, 12, null, 0);
    model2.move(null, 0, 12, PileType.OPEN, 0);
  }

  /**
   * to catch the exception while cascade piles are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test2() {
    model2.startGame(model2.getDeck(), 3, 1, false);
  }

  /**
   * to catch the exception while open piles are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test3() {
    model2.startGame(model2.getDeck(), 8, 0, false);
  }

  /**
   * to catch the exception while moving to open.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test4() {
    model2.move(PileType.CASCADE, 51, 0, PileType.CASCADE, 24);
    model2.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 0);
  }

  /**
   * to catch the exception while destpilenumber is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test45() {
    model2.move(PileType.CASCADE, 51, 0, PileType.CASCADE, 55);
  }

  /**
   * to catch the exception while foundation piles are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test5() {
    model2.move(PileType.CASCADE, 51, 0, PileType.CASCADE, 24);
    model2.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 0);
  }

  /**
   * to catch the exception with multi-moving cards.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test6() {
    model2.move(PileType.CASCADE, 51, 0, PileType.CASCADE, 24);
    model2.move(PileType.CASCADE, 24, 0, PileType.CASCADE, 0);
  }

  /**
   * to catch the exception while reaching the maximum number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test7() {
    model2.startGame(this.cards, 8, 2, false);
    String gameState = "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "C1: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C3: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C4: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "C8: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦";
    assertEquals(gameState, model2.getGameState());
    model2.move(PileType.CASCADE, 4, 0, PileType.CASCADE, 0);
  }

  /**
   * to catch the exception while moving the wrong build.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test8() {
    model2.startGame(model2.getDeck(), 4, 2, false);
    String gameState = "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "C1: K♥, 9♥, 5♥, A♥, 10♦, 6♦, 2♦, J♣, 7♣, 3♣, Q♠, 8♠, 4♠\n" +
            "C2: Q♥, 8♥, 4♥, K♦, 9♦, 5♦, A♦, 10♣, 6♣, 2♣, J♠, 7♠, 3♠\n" +
            "C3: J♥, 7♥, 3♥, Q♦, 8♦, 4♦, K♣, 9♣, 5♣, A♣, 10♠, 6♠, 2♠\n" +
            "C4: 10♥, 6♥, 2♥, J♦, 7♦, 3♦, Q♣, 8♣, 4♣, K♠, 9♠, 5♠, A♠";
    assertEquals(gameState, model2.getGameState());
    model2.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model2.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    model2.move(PileType.CASCADE, 2, 11, PileType.CASCADE, 0);
  }

  /**
   * to catch the exception while moving to the invalid build.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test9() {
    model2.startGame(this.cards, 8, 2, false);
    String gameState = "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "C1: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C3: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C4: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C5: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C6: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C7: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "C8: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦";
    assertEquals(gameState, model2.getGameState());
    model2.move(PileType.CASCADE, 5, 0, PileType.CASCADE, 0);
  }

  /**
   * to test normal multiple moves.
   */
  @Test
  public void test10() {
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2: Q♥\n" +
            "C3: J♥\n" +
            "C4: 10♥\n" +
            "C5: 9♥\n" +
            "C6: 8♥\n" +
            "C7: 7♥\n" +
            "C8: 6♥\n" +
            "C9: 5♥\n" +
            "C10: 4♥\n" +
            "C11: 3♥\n" +
            "C12: 2♥\n" +
            "C13: A♥\n" +
            "C14: K♦\n" +
            "C15: Q♦\n" +
            "C16: J♦\n" +
            "C17: 10♦\n" +
            "C18: 9♦\n" +
            "C19: 8♦\n" +
            "C20: 7♦\n" +
            "C21: 6♦\n" +
            "C22: 5♦\n" +
            "C23: 4♦\n" +
            "C24: 3♦\n" +
            "C25: 2♦\n" +
            "C26: A♦\n" +
            "C27: K♣\n" +
            "C28: Q♣\n" +
            "C29: J♣\n" +
            "C30: 10♣\n" +
            "C31: 9♣\n" +
            "C32: 8♣\n" +
            "C33: 7♣\n" +
            "C34: 6♣\n" +
            "C35: 5♣\n" +
            "C36: 4♣\n" +
            "C37: 3♣\n" +
            "C38: 2♣\n" +
            "C39: A♣\n" +
            "C40: K♠\n" +
            "C41: Q♠\n" +
            "C42: J♠\n" +
            "C43: 10♠\n" +
            "C44: 9♠\n" +
            "C45: 8♠\n" +
            "C46: 7♠\n" +
            "C47: 6♠\n" +
            "C48: 5♠\n" +
            "C49: 4♠\n" +
            "C50: 3♠\n" +
            "C51: 2♠\n" +
            "C52: A♠", model2.getGameState());
    model2.move(PileType.CASCADE, 51, 0, PileType.CASCADE, 24);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2: Q♥\n" +
            "C3: J♥\n" +
            "C4: 10♥\n" +
            "C5: 9♥\n" +
            "C6: 8♥\n" +
            "C7: 7♥\n" +
            "C8: 6♥\n" +
            "C9: 5♥\n" +
            "C10: 4♥\n" +
            "C11: 3♥\n" +
            "C12: 2♥\n" +
            "C13: A♥\n" +
            "C14: K♦\n" +
            "C15: Q♦\n" +
            "C16: J♦\n" +
            "C17: 10♦\n" +
            "C18: 9♦\n" +
            "C19: 8♦\n" +
            "C20: 7♦\n" +
            "C21: 6♦\n" +
            "C22: 5♦\n" +
            "C23: 4♦\n" +
            "C24: 3♦\n" +
            "C25: 2♦, A♠\n" +
            "C26: A♦\n" +
            "C27: K♣\n" +
            "C28: Q♣\n" +
            "C29: J♣\n" +
            "C30: 10♣\n" +
            "C31: 9♣\n" +
            "C32: 8♣\n" +
            "C33: 7♣\n" +
            "C34: 6♣\n" +
            "C35: 5♣\n" +
            "C36: 4♣\n" +
            "C37: 3♣\n" +
            "C38: 2♣\n" +
            "C39: A♣\n" +
            "C40: K♠\n" +
            "C41: Q♠\n" +
            "C42: J♠\n" +
            "C43: 10♠\n" +
            "C44: 9♠\n" +
            "C45: 8♠\n" +
            "C46: 7♠\n" +
            "C47: 6♠\n" +
            "C48: 5♠\n" +
            "C49: 4♠\n" +
            "C50: 3♠\n" +
            "C51: 2♠\n" +
            "C52: ", model2.getGameState());
    model2.move(PileType.CASCADE, 24, 0, PileType.CASCADE, 36);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2: Q♥\n" +
            "C3: J♥\n" +
            "C4: 10♥\n" +
            "C5: 9♥\n" +
            "C6: 8♥\n" +
            "C7: 7♥\n" +
            "C8: 6♥\n" +
            "C9: 5♥\n" +
            "C10: 4♥\n" +
            "C11: 3♥\n" +
            "C12: 2♥\n" +
            "C13: A♥\n" +
            "C14: K♦\n" +
            "C15: Q♦\n" +
            "C16: J♦\n" +
            "C17: 10♦\n" +
            "C18: 9♦\n" +
            "C19: 8♦\n" +
            "C20: 7♦\n" +
            "C21: 6♦\n" +
            "C22: 5♦\n" +
            "C23: 4♦\n" +
            "C24: 3♦\n" +
            "C25: \n" +
            "C26: A♦\n" +
            "C27: K♣\n" +
            "C28: Q♣\n" +
            "C29: J♣\n" +
            "C30: 10♣\n" +
            "C31: 9♣\n" +
            "C32: 8♣\n" +
            "C33: 7♣\n" +
            "C34: 6♣\n" +
            "C35: 5♣\n" +
            "C36: 4♣\n" +
            "C37: 3♣, 2♦, A♠\n" +
            "C38: 2♣\n" +
            "C39: A♣\n" +
            "C40: K♠\n" +
            "C41: Q♠\n" +
            "C42: J♠\n" +
            "C43: 10♠\n" +
            "C44: 9♠\n" +
            "C45: 8♠\n" +
            "C46: 7♠\n" +
            "C47: 6♠\n" +
            "C48: 5♠\n" +
            "C49: 4♠\n" +
            "C50: 3♠\n" +
            "C51: 2♠\n" +
            "C52: ", model2.getGameState());
  }
}