package eu.vlaurin.connect4.service;

import eu.vlaurin.connect4.dto.Players;
import eu.vlaurin.connect4.dto.Turn;
import eu.vlaurin.connect4.model.GameBoard;
import eu.vlaurin.connect4.model.Player;
import eu.vlaurin.connect4.repository.GameBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author vlaurin
 * @since 0.0.0
 */
@Component
public class GameServiceImpl implements GameService {

    @Autowired
    private GameBoardRepository repository;

    @Override
    public GameBoard create(Players players) {
        // TODO Validation

        final Player player1 = new Player(players.getUsername1(), players.getColour1());
        final Player player2 = new Player(players.getUsername2(), players.getColour2());
        final GameBoard gameBoard = new GameBoard(player1, player2);

        // TODO Handle exception

        repository.save(gameBoard);

        return gameBoard;
    }

    @Override
    public GameBoard play(Long gameId, String username, Turn turn) {
        final GameBoard gameBoard = repository.findOne(gameId);

        if (null == gameBoard) {
            // TODO Exception
        }

        // TODO Validation

        final Player[] players = gameBoard.getPlayers();
        Player player = null;
        if (players[0].getUsername().equals(username)) {
            player = players[0];
        } else if (players[1].getUsername().equals(username)) {
            player = players[1];
        } else {
            // TODO ERROR
        }

        gameBoard.play(player, turn.getColumn());

        // TODO Handle exception

        return gameBoard;
    }

    @Override
    public GameBoard retrieve(Long gameId) {
        final GameBoard gameBoard = repository.findOne(gameId);

        if (null == gameBoard) {
            // TODO Exception
        }

        return gameBoard;
    }
}
