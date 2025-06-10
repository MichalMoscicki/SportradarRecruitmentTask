# ScoreBoard

A Java implementation of a live football/soccer scoreboard system that allows tracking ongoing matches, updating scores, and generating match summaries.

## Features

- Start new matches between two teams
- Update match scores in real-time
- End matches
- Get a summary of all ongoing matches sorted by:
  - Total score (highest first)
  - Most recently started match (when scores are equal)
- Input validation for team names
- Singleton pattern implementation for the scoreboard

## Requirements

- Java 8 or higher
- JUnit 5 (for testing)



### Starting a Match

```java
ScoreBoard scoreBoard = ScoreBoardImpl.getInstance();
Match match = scoreBoard.startGame("Bulgaria", "Japan");
```

### Updating Score

```java
// Increase host team score
match.increaseHostTeamScore();
scoreBoard.updateScore(match);

// Increase guest team score
match.increaseGuestTeamScore();
scoreBoard.updateScore(match);
```

### Ending a Match

```java
scoreBoard.endGame(match);
```

### Getting Match Summary

```java
List<Match> summary = scoreBoard.getSummary();
```

## Input Validation

The system performs the following validations:

- Team names must not be null
- Team names must not be empty
- Team names must contain only letters
- Teams must be different (case-insensitive)
- A team cannot play in multiple matches simultaneously

## Example

```java
ScoreBoard scoreBoard = ScoreBoardImpl.getInstance();

// Start matches
Match match1 = scoreBoard.startGame("Bulgaria", "Japan");
Match match2 = scoreBoard.startGame("Poland", "Germany");
Match match3 = scoreBoard.startGame("Turkey", "England");

// Update scores
match1.increaseGuestTeamScore();
scoreBoard.updateScore(match1);

match3.increaseHostTeamScore();
scoreBoard.updateScore(match3);

// Get summary of ongoing matches
List<Match> summary = scoreBoard.getSummary();

// End a match
scoreBoard.endGame(match1);
```

## Error Handling

The system throws `ScoreBoardException` with descriptive messages for various error conditions:

- Invalid team names
- Teams already playing in other matches
- Attempting to update score for a non-ongoing match

