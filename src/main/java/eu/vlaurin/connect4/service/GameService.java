package eu.vlaurin.connect4.service;

import eu.vlaurin.connect4.dto.Players;
import eu.vlaurin.connect4.dto.Turn;
import eu.vlaurin.connect4.model.GameBoard;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public interface GameService {

    GameBoard create(Players players);

    GameBoard play(Long gameId, String username, Turn turn);

    GameBoard retrieve(Long gameId);
}
