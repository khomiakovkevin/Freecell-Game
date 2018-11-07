/**
 * Created by kevin on 08.02.17.
 */

package cs3500.hw03;

import java.util.List;

import cs3500.hw02.FreecellOperations;

public interface IFreecellController<K> {
  void playGame(List<K> deck, FreecellOperations<K> model, int numCascades,
                int numOpens, boolean shuffle);
}
