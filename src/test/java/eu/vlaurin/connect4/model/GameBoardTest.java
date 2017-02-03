package eu.vlaurin.connect4.model;

import eu.vlaurin.connect4.model.exception.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
import static org.mockito.Mockito.*;

/**
 * @author vlaurin
 * @since 0.0.0
 */
@RunWith(Enclosed.class)
public class GameBoardTest {

    public static class CreateGameTest {
        private Player player1;
        private DiscColour player1Colour = DiscColour.RED;
        private Player player2;
        private DiscColour player2Colour = DiscColour.YELLOW;

        private GameBoard gameBoard;

        @Before
        public void setUp() {
            player1 = mock(Player.class);
            when(player1.getColour()).thenReturn(player1Colour);

            player2 = mock(Player.class);
            when(player2.getColour()).thenReturn(player2Colour);
            gameBoard = new GameBoard(player1, player2);
        }

        @Test
        public void shouldBeCreatedInNewState() {
            assertThat(gameBoard.getState(), is(GameBoard.State.NEW));
        }

        @Test
        public void shouldBeCreatedWith7Columns() {
            assertThat(gameBoard.getColumns(), is(arrayWithSize(7)));
            assertThat(gameBoard.getColumns(), not(arrayContaining(nullValue())));
        }

        @Test
        public void shouldBeCreatedWithGivenPlayers() {
            assertThat(gameBoard.getPlayers(), is(arrayWithSize(2)));
            assertThat(gameBoard.getPlayers(), arrayContaining(player1, player2));
        }

        @Test
        public void nextPlayerShouldBeTheFirstPlayerProvided() {
            assertThat(gameBoard.getNextPlayer(), is(player1));
        }

        @Test
        public void winnerShouldBeNull() {
            assertThat(gameBoard.getWinner(), is(nullValue()));
        }

        @Test(expected = SameColourException.class)
        public void playersColoursShouldBeDifferent() {
            when(player1.getColour()).thenReturn(DiscColour.RED);
            when(player2.getColour()).thenReturn(DiscColour.RED);

            gameBoard = new GameBoard(player1, player2);
        }
    }

    public static class PlayGameTest {

        private Player player1;
        private DiscColour player1Colour = DiscColour.RED;
        private Player player2;
        private DiscColour player2Colour = DiscColour.YELLOW;
        private Player[] players;
        private GameBoardColumn[] columns;

        private GameBoard gameBoard;

        @Before
        public void setUp() {
            player1 = mock(Player.class);
            when(player1.getColour()).thenReturn(player1Colour);

            player2 = mock(Player.class);
            when(player2.getColour()).thenReturn(player2Colour);

            players = new Player[] {player1, player2};
            columns = new GameBoardColumn[GameBoard.COLUMN_COUNT];
            for (int i = 0; i < columns.length; i++) {
                columns[i] = mock(GameBoardColumn.class);
            }

            // Create a started board
            gameBoard = new GameBoard(GameBoard.State.STARTED, players, columns, 0, null);
        }

        @Test
        public void stateShouldBeStartedAfterFirstTurn() {
            // Create a new board
            gameBoard = new GameBoard(GameBoard.State.NEW, players, columns, 0, null);

            assumeThat(gameBoard.getState(), is(GameBoard.State.NEW));
            assumeThat(gameBoard.getNextPlayer(), is(player1));

            gameBoard.play(player1, 0);

            assertThat(gameBoard.getState(), is(GameBoard.State.STARTED));
        }

        @Test
        public void nextPlayerShouldBeUpdatedAtEachTurn() {
            assumeThat(gameBoard.getNextPlayer(), is(player1));

            gameBoard.play(player1, 0);

            assertThat(gameBoard.getNextPlayer(), is(player2));

            gameBoard.play(player2, 0);

            assertThat(gameBoard.getNextPlayer(), is(player1));
        }

        @Test(expected = NotPlayerTurnException.class)
        public void shouldOnlyBeAllowedToPlayWhenItsTurn() {
            assumeThat(gameBoard.getNextPlayer(), is(player1));

            gameBoard.play(player2, 0);
        }

        @Test
        public void playingShouldDropMatchingColourDiscInColumn() {
            gameBoard.play(player1, 0);

            verify(columns[0]).drop(player1Colour);
            verify(columns[1], never()).drop(Matchers.any());
        }

        @Test(expected = ColumnOutOfBoundsException.class)
        public void shouldNotBeAllowedToPlayInvalidColumn() {
            gameBoard.play(player1, 7);
        }

        @Test(expected = ColumnFullException.class)
        public void shouldNotBeAllowedToPlayFullColumn() {
            doThrow(ColumnFullException.class).when(columns[0]).drop(player1Colour);

            gameBoard.play(player1, 0);
        }

        @Test(expected = FinishedGameException.class)
        public void shouldNotBeAllowedToPlayFinishedGame() {
            // Create a finished board
            gameBoard = new GameBoard(GameBoard.State.FINISHED, players, columns, 0, 0);

            gameBoard.play(player1, 0);
        }

        @Test(expected = FinishedGameException.class)
        public void shouldNotBeAllowedToPlayDrawGame() {
            // Create a draw board
            gameBoard = new GameBoard(GameBoard.State.DRAW, players, columns, 0, null);

            gameBoard.play(player1, 0);
        }

        @Test
        public void shouldDetectVerticalVictory() {
            // 4 red discs, vertically, in 2nd column
            when(columns[1].getRow(any())).then(invocationOnMock -> {
                final Integer row = (Integer) invocationOnMock.getArguments()[0];
                return row < 4 ? DiscColour.RED : null;
            });

            gameBoard.play(player1, 0);

            assertThat(gameBoard.getState(), is(GameBoard.State.FINISHED));
            assertThat(gameBoard.getWinner(), is(player1));
        }

        @Test
        public void shouldDetectHorizontalVictory() {
            // 4 red discs, horizontally, in 2nd row
            when(columns[0].getRow(1)).thenReturn(DiscColour.RED);
            when(columns[1].getRow(1)).thenReturn(DiscColour.RED);
            when(columns[2].getRow(1)).thenReturn(DiscColour.RED);
            when(columns[3].getRow(1)).thenReturn(DiscColour.RED);

            gameBoard.play(player1, 0);

            assertThat(gameBoard.getState(), is(GameBoard.State.FINISHED));
            assertThat(gameBoard.getWinner(), is(player1));
        }

        @Test
        public void shouldDetectForwardDiagonalVictory() {
            // 4 red discs, in forward diagonal
            when(columns[0].getRow(0)).thenReturn(DiscColour.RED);
            when(columns[1].getRow(1)).thenReturn(DiscColour.RED);
            when(columns[2].getRow(2)).thenReturn(DiscColour.RED);
            when(columns[3].getRow(3)).thenReturn(DiscColour.RED);

            gameBoard.play(player1, 0);

            assertThat(gameBoard.getState(), is(GameBoard.State.FINISHED));
            assertThat(gameBoard.getWinner(), is(player1));
        }

        @Test
        public void shouldDetectBackwardDiagonalVictory() {
            // 4 red discs, in backward diagonal
            when(columns[0].getRow(3)).thenReturn(DiscColour.RED);
            when(columns[1].getRow(2)).thenReturn(DiscColour.RED);
            when(columns[2].getRow(1)).thenReturn(DiscColour.RED);
            when(columns[3].getRow(0)).thenReturn(DiscColour.RED);

            gameBoard.play(player1, 0);

            assertThat(gameBoard.getState(), is(GameBoard.State.FINISHED));
            assertThat(gameBoard.getWinner(), is(player1));
        }

        @Test
        public void shouldNotDetectVictoryOfOtherPlayer() {
            // 4 yellow discs, vertically, in 2nd column
            when(columns[1].getRow(any())).then(invocationOnMock -> {
                final Integer row = (Integer) invocationOnMock.getArguments()[0];
                return row < 4 ? DiscColour.YELLOW : null;
            });

            gameBoard.play(player1, 0);

            assertThat(gameBoard.getState(), is(not(GameBoard.State.FINISHED)));
            assertThat(gameBoard.getWinner(), is(nullValue()));
        }

        @Test
        public void shouldDetectDraw() {
            for (int i = 0; i < GameBoard.COLUMN_COUNT; i++) {
                when(columns[i].getRow(any())).thenReturn(DiscColour.YELLOW);
            }

            gameBoard.play(player1, 0);

            assertThat(gameBoard.getState(), is(GameBoard.State.DRAW));
            assertThat(gameBoard.getWinner(), is(nullValue()));
        }
    }
}
