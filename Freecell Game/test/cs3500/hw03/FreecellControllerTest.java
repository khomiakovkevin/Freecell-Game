package cs3500.hw03;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevin on 08.02.17.
 */
public class FreecellControllerTest {

  Readable input0;
  Appendable output0;
  FreecellController controller0;

  FreecellModel model00;
  FreecellController controller00;

  Readable input1;
  Readable input2;
  Readable input3;
  Readable input4;
  Readable input5;

  Appendable output1;
  Appendable output2;
  Appendable output3;
  Appendable output4;
  Appendable output5;

  FreecellModel model1;
  FreecellModel model52;

  FreecellController controller1;
  FreecellController controller52;


  /**
   * to store initial data.
   */
  @Before
  public void init() {
    input0 = null;
    output0 = null;

    model00 = new FreecellModel();

    input1 = new StringReader("");
    output1 = new StringBuilder();

    input2 = new StringReader("");
    output2 = new StringBuilder();

    input3 = new StringReader("");
    output3 = new StringBuilder();

    input4 = new StringReader("");
    output4 = new StringBuilder();

    input5 = new StringReader("");
    output5 = new StringBuilder();

    this.model1 = new FreecellModel();
    this.model52 = new FreecellModel();

    this.controller0 = new FreecellController(input3, output3);
    this.controller00 = new FreecellController(input4, output4);

    this.controller1 = new FreecellController(input1, output1);
    this.controller52 = new FreecellController(input2, output2);

    this.controller1.playGame(this.model1.getDeck(), this.model1, 8, 4, false);
    this.controller52.playGame(this.model52.getDeck(),
            this.model52, 52, 1, false);

  }

  @Test(expected = IllegalArgumentException.class)

  public void testIAEonnulldeck() {
    this.controller0.playGame(null, new FreecellModel(), 1, 2, true);
    this.controller0.playGame(null, null, 1, 2, true);
  }
  

  @Test
  public void testStartGame1() {
    this.controller00.playGame(new ArrayList<Card>(), model00, 1, 2, true);
    assertEquals("Could not start game.", controller00.output.toString());
  }

  @Test
  public void testStartGame2() {
    this.controller00.playGame(model00.getDeck(), model00, -10, 2, true);
    assertEquals("Could not start game.", controller00.output.toString());
  }

  @Test
  public void testStartGame3() {
    this.controller00.playGame(model00.getDeck(), model00, 8, -10, true);
    assertEquals("Could not start game.", controller00.output.toString());
  }

  @Test
  public void testPlayGame0() {
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, 5♥, 10♦, 2♦, 7♣, Q♠, 4♠\n" +
            "C2: Q♥, 4♥, 9♦, A♦, 6♣, J♠, 3♠\n" +
            "C3: J♥, 3♥, 8♦, K♣, 5♣, 10♠, 2♠\n" +
            "C4: 10♥, 2♥, 7♦, Q♣, 4♣, 9♠, A♠\n" +
            "C5: 9♥, A♥, 6♦, J♣, 3♣, 8♠\n" +
            "C6: 8♥, K♦, 5♦, 10♣, 2♣, 7♠\n" +
            "C7: 7♥, Q♦, 4♦, 9♣, A♣, 6♠\n" +
            "C8: 6♥, J♦, 3♦, 8♣, K♠, 5♠\n" +
            "Game quit prematurely.", this.controller1.output.toString());
  }

  @Test
  public void testPlayGame1() {
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
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
            "C52: A♠\n" +
            "Game quit prematurely.", this.controller52.output.toString());
  }

  @Test
  public void testwrongin() {
    StringReader input1 = new StringReader("D4 6 F1");
    StringBuilder output1 = new StringBuilder();
    FreecellController controller1 = new FreecellController(input1, output1);
    controller1.playGame(model1.getDeck(), model1, 8, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, 5♥, 10♦, 2♦, 7♣, Q♠, 4♠\n" +
            "C2: Q♥, 4♥, 9♦, A♦, 6♣, J♠, 3♠\n" +
            "C3: J♥, 3♥, 8♦, K♣, 5♣, 10♠, 2♠\n" +
            "C4: 10♥, 2♥, 7♦, Q♣, 4♣, 9♠, A♠\n" +
            "C5: 9♥, A♥, 6♦, J♣, 3♣, 8♠\n" +
            "C6: 8♥, K♦, 5♦, 10♣, 2♣, 7♠\n" +
            "C7: 7♥, Q♦, 4♦, 9♣, A♣, 6♠\n" +
            "C8: 6♥, J♦, 3♦, 8♣, K♠, 5♠\n" +
            "Game quit prematurely.", output1.toString());
  }

  @Test
  public void testq() {
    StringReader input1 = new StringReader("q 1 F1");
    StringBuilder output1 = new StringBuilder();
    FreecellController moveTest = new FreecellController(input1, output1);
    moveTest.playGame(model1.getDeck(), model1, 8, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, 5♥, 10♦, 2♦, 7♣, Q♠, 4♠\n" +
            "C2: Q♥, 4♥, 9♦, A♦, 6♣, J♠, 3♠\n" +
            "C3: J♥, 3♥, 8♦, K♣, 5♣, 10♠, 2♠\n" +
            "C4: 10♥, 2♥, 7♦, Q♣, 4♣, 9♠, A♠\n" +
            "C5: 9♥, A♥, 6♦, J♣, 3♣, 8♠\n" +
            "C6: 8♥, K♦, 5♦, 10♣, 2♣, 7♠\n" +
            "C7: 7♥, Q♦, 4♦, 9♣, A♣, 6♠\n" +
            "C8: 6♥, J♦, 3♦, 8♣, K♠, 5♠\n" +
            "Game quit prematurely.", output1.toString());
  }

  @Test
  public void testQ() {
    StringReader input1 = new StringReader("C1 Q F1");
    StringBuilder output1 = new StringBuilder();
    FreecellController moveTest = new FreecellController(input1, output1);
    moveTest.playGame(model1.getDeck(), model1, 8, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, 5♥, 10♦, 2♦, 7♣, Q♠, 4♠\n" +
            "C2: Q♥, 4♥, 9♦, A♦, 6♣, J♠, 3♠\n" +
            "C3: J♥, 3♥, 8♦, K♣, 5♣, 10♠, 2♠\n" +
            "C4: 10♥, 2♥, 7♦, Q♣, 4♣, 9♠, A♠\n" +
            "C5: 9♥, A♥, 6♦, J♣, 3♣, 8♠\n" +
            "C6: 8♥, K♦, 5♦, 10♣, 2♣, 7♠\n" +
            "C7: 7♥, Q♦, 4♦, 9♣, A♣, 6♠\n" +
            "C8: 6♥, J♦, 3♦, 8♣, K♠, 5♠\n" +
            "Game quit prematurely.", output1.toString());
  }
}
