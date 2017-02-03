package eu.vlaurin.connect4.model;

import eu.vlaurin.connect4.model.exception.ColumnOutOfBoundsException;
import eu.vlaurin.connect4.model.exception.FinishedGameException;
import eu.vlaurin.connect4.model.exception.NotPlayerTurnException;
import eu.vlaurin.connect4.model.exception.SameColourException;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class GameBoard {

    public final static Integer COLUMN_COUNT = 7;

    private Long id;
    private State state;
    private GameBoardColumn[] columns;
    private Player[] players;
    private Integer nextPlayer;
    private Integer winner;

    public GameBoard(Player player1, Player player2) {
        if (player1.getColour().equals(player2.getColour())) {
            throw new SameColourException();
        }

        state = State.NEW;
        columns = new GameBoardColumn[COLUMN_COUNT];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = new GameBoardColumn();
        }
        players = new Player[]{player1, player2};
        nextPlayer = 0;
    }

    public GameBoard(State state, Player[] players, GameBoardColumn[] columns, Integer nextPlayer, Integer winner) {
        this.state = state;
        this.players = players;
        this.columns = columns;
        this.nextPlayer = nextPlayer;
        this.winner = winner;
    }

    public State getState() {
        return state;
    }

    public GameBoardColumn[] getColumns() {
        return columns;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getNextPlayer() {
        return players[nextPlayer];
    }

    public Player getWinner() {
        return null != winner ? players[winner] : null;
    }

    public void play(Player player, Integer column) {

        validatePlay(player, column);

        columns[column].drop(player.getColour());

        updateState();

        nextPlayer = (nextPlayer + 1) % players.length;
    }

    private void validatePlay(Player player, Integer column) {
        if (State.FINISHED.equals(state) || State.DRAW.equals(state)) {
            throw new FinishedGameException();
        }

        if (!getNextPlayer().equals(player)) {
            throw new NotPlayerTurnException();
        }

        if (column == null || column < 0 || column >= columns.length) {
            throw new ColumnOutOfBoundsException();
        }
    }

    private void updateState() {
        switch (state) {
            case NEW:
                state = State.STARTED;
                break;
            case STARTED:
                if (isWon()) {
                    state = State.FINISHED;
                    winner = nextPlayer;
                } else if (isDraw()) {
                    state = State.DRAW;
                }
                break;
        }
    }

    private Boolean isWon() {
        for (int col = 0; col < columns.length; col++) {
            final GameBoardColumn column = columns[col];
            for (int row = 0; row < GameBoardColumn.ROW_COUNT; row++) {
                final Coordinate origin = new Coordinate(col, row);
                final DiscColour colour = column.getRow(row);

                // Don't check other player colour as victory can only be for current player
                if (!getNextPlayer().getColour().equals(colour)) {
                    continue;
                }

                if (null != colour) {
                    if (areSameColour(Coordinate.verticalConnectionCoordinates(origin), colour)
                            || areSameColour(Coordinate.horizontalConnectionCoordinates(origin), colour)
                            || areSameColour(Coordinate.forwardDiagonalConnectionCoordinates(origin), colour)
                            || areSameColour(Coordinate.backwardDiagonalConnectionCoordinates(origin), colour)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private Boolean isDraw() {
        for (int col = 0; col < COLUMN_COUNT; col++) {
            for (int row = 0; row < GameBoardColumn.ROW_COUNT; row++) {
                if (null == columns[col].getRow(row)) {
                    return false;
                }
            }
        }
        return true;
    }

    private Boolean areSameColour(Coordinate[] coords, DiscColour colour) {
        for (int i = 0; i < coords.length; i++) {
            final Coordinate coord = coords[i];

            // Out of board
            if (coord.getX() < 0 || coord.getX() >= COLUMN_COUNT) {
                return false;
            }

            if(!colour.equals(columns[coord.getX()].getRow(coord.getY()))) {
                return false;
            }
        }
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public enum State {
        NEW, STARTED, FINISHED, DRAW
    }
}
