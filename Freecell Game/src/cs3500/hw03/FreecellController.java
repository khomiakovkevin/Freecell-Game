package cs3500.hw03;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Objects;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellOperations;

/**
 * Created by kevin on 08.02.17.
 */
public class FreecellController implements IFreecellController<Card> {

  Readable input;
  Appendable output;
  boolean isstarted;
  boolean isover;
  String[] inputAsString;
  Scanner scanner;

  /**
   * Constructor for the controller.
   * @param rd describes readable interface(our input)
   * @param ap describes appendable interface(our output)
   */
  public FreecellController(Readable rd, Appendable ap) {
    this.input = rd;
    this.output = ap;
    this.inputAsString = new String[3];
    this.scanner = new Scanner(input);

    isstarted = false;
    isover = false;

    try {
      Objects.requireNonNull(rd);
      Objects.requireNonNull(ap);
    } catch (NullPointerException npe) {
      throw new IllegalStateException("Appendable and Readable should not be null");
    }
  }

  @Override
  public void playGame(List deck, FreecellOperations model, int numCascades, int numOpens,
                       boolean shuffle) {
    if (deck == null || model == null) {
      throw new IllegalArgumentException("Cannot start the game because "
              + "either the deck or the model is null");
    }
    if (!this.isstarted) {
      try {
        model.startGame(deck, numCascades, numOpens, shuffle);
      } catch (IllegalArgumentException iae) {
        try {
          this.output.append("Could not start game.");
          return;
        } catch (IOException ioe) {
          return;
        }
      }
      this.isstarted = true;
    }
    while (!(this.isover) && !model.isGameOver() ) {
      try {
        this.output.append(model.getGameState());
      } catch (IOException ioe) {
        return;
      }
      try {
        this.output.append("\n");
      }
      catch (IOException ioe) {
        return;
      }
      if (!model.isGameOver()) {
        if (this.inputAsString[2] == null) {
          this.analyzeInput();
        }
        if (this.inputAsString[2] != null) {
          MoveHelp m = new MoveHelp(inputAsString);
          try {
            model.move(m.source, m.pileNumber, m.cardIndex,
                    m.destination, m.destPileNumber);
          } catch (IllegalArgumentException iae) {
            try {
              output.append("Cannot move");
            } catch (IOException ioe) {
              return;
            }
          }
          this.inputAsString = new String[3];
        }
      }
    }
    if (model.isGameOver()) {
      try {
        this.output.append(model.getGameState());
      } catch (IOException ioe) {
        return;
      }
      try {
        this.output.append("Game over");
      } catch (IOException ioe) {
        return;
      }
    }
    else {
      try {
        this.output.append("Game quit prematurely.");
      } catch (IOException ioe) {
        return;
      }
    }
  }

  /**
   * to analyze the input.
   */
  public void analyzeInput() {
    boolean tocheck;
    String nextinput = "";
    Input i = Input.SOURCEPILE;
    while (this.inputAsString[2] == null && !this.isover) {
      try {
        nextinput = this.scanner.next();
      } catch (NoSuchElementException nse) {
        this.endGame();
        return;
      }
      tocheck = this.checkInput(nextinput, i);
      if (tocheck) {
        inputAsString[i.ordinal()] = nextinput;
        i = this.next();
      }
    }
  }

  /**
   * to check the input.
   * @param input describes the input string
   * @param i describes the input(see the input class)
   * @return a boolean whether the input is formatted or not
   */
  public boolean checkInput(String input, Input i) {
    if (input.charAt(0) == 'q' || input.charAt(0) == 'Q') {
      this.endGame();
      return false;
    }
    if (i.equals(Input.SOURCEPILE)) {
      return this.checkPileIndex(input);
    }
    if (i.equals(Input.CARDINDEX)) {
      try {
        int index = Integer.parseInt(input);
        if (index < 0) {
          return false;
        }
      } catch (IllegalArgumentException iae) {
        try {
          this.output.append("Cannot find a card at this index");
        } catch (IOException ioe) {
          ioe.printStackTrace();
        }
      }
      return true;
    }
    if (i.equals(Input.DESTPILE)) {
      return this.checkPileIndex(input);
    }
    throw new RuntimeException("The input state is wrong");
  }

  /**
   * to check the pile idx.
   * @param pile describes the pile as a string
   * @return boolean whether the idx is correct
   */
  public boolean checkPileIndex(String pile) {
    if ((pile.charAt(0) == 'C') || (pile.charAt(0) == 'F') || (pile.charAt(0) == 'O')) {
      try {
        int idx = Integer.parseInt(pile.substring(1));
        if (idx < 1) {
          return false;
        }
      }
      catch (IllegalArgumentException iae) {
        try {
          this.output.append("Cannot find a pile at this index");
          return false;
        } catch (IOException ioe) {
          ioe.printStackTrace();
        }
      }
    }
    else {
      return false;
    }
    return true;
  }

  /**
   * returns the next input.
   * @return the next input
   */
  public Input next() {
    if (this.equals(Input.SOURCEPILE)) {
      return Input.CARDINDEX;
    } else if (this.equals(Input.CARDINDEX)) {
      return Input.DESTPILE;
    } else {
      return Input.DESTPILE;
    }
  }

  /**
   * to end the game.
   */
  public void endGame() {

    this.isover = true;
  }
}


