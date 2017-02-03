# connect4
An implementation of https://github.com/michaeldfallen/coding-tests/blob/master/Connect_4.md

## Build and run

with Java:

`mvn clean mvn spring-boot:run`

with Docker:

`mvn clean package docker:build && docker run -p 8080:8080 connect4`

## API

### POST /games

Creates a new game board for 2 players.

#### Input

Body:

```json
{
	"username1": "alfred",
	"colour1": "RED",
	"username2": "henry",
	"colour2": "YELLOW"
}
```

#### Output

Current state of the game board, as returned by `GET /games/{id}`.

### PUT /games/{id}/disc

Drop a colour disc into a non-full column.

#### Input

Header:

- `Authorization`: Username of the user playing, eg. `alfred`

Body:

```json
{
	"column": 0
}
```

### GET /games/{id}

Retrieve the current state of a game board.

#### Output

Example output for resource `/games/1`:

```json
{
  "id": 1,
  "state": "FINISHED",
  "columns": [
    {
      "rows": [
        "RED",
        "RED",
        "RED",
        "RED",
        null,
        null
      ],
      "length": 4
    },
    {
      "rows": [
        "YELLOW",
        "YELLOW",
        "YELLOW",
        null,
        null,
        null
      ],
      "length": 3
    },
    {
      "rows": [
        null,
        null,
        null,
        null,
        null,
        null
      ],
      "length": 0
    },
    {
      "rows": [
        null,
        null,
        null,
        null,
        null,
        null
      ],
      "length": 0
    },
    {
      "rows": [
        null,
        null,
        null,
        null,
        null,
        null
      ],
      "length": 0
    },
    {
      "rows": [
        null,
        null,
        null,
        null,
        null,
        null
      ],
      "length": 0
    },
    {
      "rows": [
        null,
        null,
        null,
        null,
        null,
        null
      ],
      "length": 0
    }
  ],
  "players": [
    {
      "username": "alfred",
      "colour": "RED"
    },
    {
      "username": "henry",
      "colour": "YELLOW"
    }
  ],
  "nextPlayer": {
    "username": "henry",
    "colour": "YELLOW"
  },
  "winner": {
    "username": "alfred",
    "colour": "RED"
  }
}
```
