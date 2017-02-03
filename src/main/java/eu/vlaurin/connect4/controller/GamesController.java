package eu.vlaurin.connect4.controller;

import eu.vlaurin.connect4.dto.Players;
import eu.vlaurin.connect4.dto.Turn;
import eu.vlaurin.connect4.model.GameBoard;
import eu.vlaurin.connect4.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public GameBoard createGame(@RequestBody Players players) {
        return gameService.create(players);
    }

    @RequestMapping(value = "/{id}/disc", method = RequestMethod.PUT)
    public GameBoard playTurn(@PathVariable("id") Long gameId, @RequestHeader("Authorization") String username, @RequestBody Turn turn) {

        System.out.println("username: " + username);
        return gameService.play(gameId, username, turn);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GameBoard getGame(@PathVariable("id") Long gameId) {
        return gameService.retrieve(gameId);
    }
}
