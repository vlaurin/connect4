# connect4
An implementation of https://github.com/michaeldfallen/coding-tests/blob/master/Connect_4.md

## API

### POST /games

Creates a new game board for 2 players.

### PUT /games/{id}/disc

Drop a colour disc into a non-full column.

### GET /games/{id}

Retrieve the current state of a game board.
