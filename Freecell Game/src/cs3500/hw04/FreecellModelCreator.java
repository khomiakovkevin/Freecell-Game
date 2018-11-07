package cs3500.hw04;

import cs3500.hw02.FreecellModel;

/**
 * Created by kevin on 18.02.17.
 */

/**
 * to represent a FreecellModelCreator.
 */
public class FreecellModelCreator {

  public enum GameType {
    SINGLEMOVE,
    MULTIMOVE;
  }

  /**
   * to create right model based on the movetype.
   * @param type is either single or multi
   * @return either a FreecellModel or a FreecellModelCreator
   */
  public static FreecellModel create(GameType type) {
    if (type.equals(GameType.SINGLEMOVE)) {
      return new FreecellModel();
    }
    if (type.equals(GameType.MULTIMOVE)) {
      return new FreecellModelCreatorImpl();
    }
    else {
      throw new IllegalArgumentException("Invalid model type");
    }
  }
}
