package cs3500.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by kevin on 18.02.17.
 */

/**
 * to test single and multi-moves.
 */
public class FreecellModelCreatorTest {

  FreecellOperations<Card> singlemove;
  FreecellOperations<Card> multimove;

  /**
   * Initial data.
   */
  @Before
  public void init() {
    multimove = FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    singlemove = FreecellModelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE);
    multimove.startGame(this.multimove.getDeck(), 52, 4, false);
    singlemove.startGame(this.singlemove.getDeck(), 52, 4, false);
  }

  /**
   * to test the create method.
   */
  @Test
  public void test1() {
    FreecellModelCreator modelCreator = new FreecellModelCreator();
    FreecellOperations<Card> singlemodel =
            modelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE);
    assertTrue(singlemodel instanceof FreecellModel);
    FreecellOperations<Card> multimodel =
            modelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    assertTrue(multimodel instanceof FreecellModelCreatorImpl);
  }

  /**
   * to test gamestates of 2 models(if they are equal).
   */
  @Test
  public void test3() {
    FreecellOperations<Card> model1 = FreecellModelCreator.create(FreecellModelCreator.GameType
            .MULTIMOVE);
    FreecellOperations<Card> model2 = FreecellModelCreator.create(FreecellModelCreator
            .GameType
            .SINGLEMOVE);
    model1.startGame(model1.getDeck(), 52, 4, false);
    model2.startGame(model2.getDeck(), 52, 4, false);
    assertEquals(model2.getGameState(), model2.getGameState());
  }

  /**
   * to test gamestates of 2 single models(if they are different).
   */
  @Test(expected = IllegalArgumentException.class)
  public void test4() {
    FreecellOperations<Card> model1 = FreecellModelCreator.create(FreecellModelCreator
            .GameType
            .SINGLEMOVE);
    FreecellOperations<Card> model2 = FreecellModelCreator.create(FreecellModelCreator
            .GameType
            .SINGLEMOVE);
    model1.startGame(model1.getDeck(), 52, 4, false);
    model1.startGame(model2.getDeck(), 52, 4, false);
    model1.move(PileType.CASCADE, 1, 0, PileType.OPEN, 2);
    model1.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 3);
    model1.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 4);
    model1.move(PileType.CASCADE, 51, 1, PileType.CASCADE, 51);
    model2.move(PileType.CASCADE, 1, 0, PileType.OPEN, 2);
    model2.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 3);
    model2.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 4);
    model2.move(PileType.CASCADE, 51, 1, PileType.CASCADE, 51);
    assertNotEquals(model1.getGameState(), model2.getGameState());
  }

  /**
   * to test if it is impossible to move multiple cards with a single model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test5() {
    singlemove.move(PileType.CASCADE, 51, 0, PileType.CASCADE, 24);
    singlemove.move(PileType.CASCADE, 24, 0, PileType.CASCADE, 36);
  }

  /**
   * to test if it is possible for a multimove model to move multiple cards.
   */
  @Test
  public void test6() {
    multimove.move(PileType.CASCADE, 51, 0, PileType.CASCADE, 24);
    multimove.move(PileType.CASCADE, 24, 0, PileType.CASCADE, 36);
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
            "C52: ", this.multimove.getGameState());
  }

}