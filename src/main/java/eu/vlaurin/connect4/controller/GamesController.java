package eu.vlaurin.connect4.controller;

import eu.vlaurin.connect4.dto.Players;
import eu.vlaurin.connect4.dto.Turn;
import eu.vlaurin.connect4.exception.Connect4Exception;
import eu.vlaurin.connect4.exception.UnauthorizedPlayerException;
import eu.vlaurin.connect4.model.GameBoard;
import eu.vlaurin.connect4.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author vlaurin
 * @since 0.0.0
 */
@RestController
@RequestMapping("/games")
public class GamesController {

    @Autowired
    private GameService gameService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createGame(@RequestBody Players players) {

        try {
            final GameBoard gameBoard = gameService.create(players);

            return ResponseEntity.ok(gameBoard);
        } catch (Connect4Exception exception) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
        }
    }

    @RequestMapping(value = "/{id}/disc", method = RequestMethod.PUT)
    public ResponseEntity playTurn(@PathVariable("id") Long gameId, @RequestHeader("Authorization") String username, @RequestBody Turn turn) {

        try {
            final GameBoard gameBoard = gameService.play(gameId, username, turn);

            if (null == gameBoard) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(gameBoard);
        } catch (UnauthorizedPlayerException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        } catch (Connect4Exception exception) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getGame(@PathVariable("id") Long gameId) {

        final GameBoard gameBoard = gameService.retrieve(gameId);

        if (null == gameBoard) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(gameBoard);
    }
}
