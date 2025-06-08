package org.example.scoreboard;

import org.example.exception.ScoreBoardException;
import org.example.model.Match;
import org.example.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    ScoreBoard scoreBoard = ScoreBoardImpl.getInstance();

    @BeforeEach
    void setup() {
        ((ScoreBoardImpl) scoreBoard).clearBoard();
    }

    @Test
    void startGame_shouldReturnError_TeamMustNotBeNull() {
        String hostTeam = "Bulgaria";
        String guestTeam = null;
        String message = "Team name must not be null. Host team: " + hostTeam + ", guestTeam: " + guestTeam;

        Exception exception = assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame(hostTeam, guestTeam));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void startGame_shouldReturnError_TeamMustNotBeEmpty() {
        String hostTeam = "Bulgaria";
        String guestTeam = "";
        String message = "Team name must not be empty. Host team: " + hostTeam + ", guestTeam: " + guestTeam;

        Exception exception = assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame(hostTeam, guestTeam));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void startGame_shouldReturnError_TeamNameMustContainOnlyLetters() {
        String hostTeam = "Bulgaria";
        String guestTeam = "Japon1a";
        String message = "Team name must contain only letters. Host team: " + hostTeam + ", guestTeam: " + guestTeam;

        Exception exception = assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame(hostTeam, guestTeam));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void startGame_shouldReturnError_TeamsMustBeDifferent() {
        String hostTeam = "Bulgaria";
        String guestTeam = "BULGARIA";
        String message = "Team name must be different. Host team: " + hostTeam + ", guestTeam: " + guestTeam;

        Exception exception = assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame(hostTeam, guestTeam));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void startGame_shouldReturnError_TeamIsAlreadyPlaying1() {
        String hostTeam1 = "Bulgaria";
        String guestTeam1 = "Japan";
        scoreBoard.startGame(hostTeam1, guestTeam1);

        String hostTeam2 = "France";
        String guestTeam2 = "Bulgaria";
        String message = "Team " + guestTeam2 + " is already playing";

        Exception exception = assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame(hostTeam2, guestTeam2));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void startGame_shouldReturnError_TeamIsAlreadyPlaying2() {
        String hostTeam1 = "Bulgaria";
        String guestTeam1 = "Japan";
        scoreBoard.startGame(hostTeam1, guestTeam1);

        String hostTeam2 = "Bulgaria";
        String guestTeam2 = "Poland";
        String message = "Team " + hostTeam2 + " is already playing";

        Exception exception = assertThrows(ScoreBoardException.class, () -> scoreBoard.startGame(hostTeam2, guestTeam2));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void startGame_shouldReturnMatch() {
        String hostTeam = "Bulgaria";
        String guestTeam = "Japan";
        Match match = scoreBoard.startGame(hostTeam, guestTeam);
        assertEquals(hostTeam, match.getHostTeam().getName());
        assertEquals(guestTeam, match.getGuestTeam().getName());
        assertEquals(0, match.getHostTeamScore());
        assertEquals(0, match.getGuestTeamScore());
        assertNotNull(match.getCreateDate());
        assertTrue(((ScoreBoardImpl) scoreBoard).getOngoingMatches().contains(match));
    }

    @Test
    void endGame_shouldRemoveMatch1() {
        String hostTeam = "Bulgaria";
        String guestTeam = "Japan";
        Match match = scoreBoard.startGame(hostTeam, guestTeam);
        scoreBoard.endGame(match);
        assertFalse(((ScoreBoardImpl) scoreBoard).getOngoingMatches().contains(match));
    }

    @Test
    void endGame_shouldRemoveMatch2() {
        String hostTeam = "Bulgaria";
        String guestTeam = "Japan";
        Match match = scoreBoard.startGame(hostTeam, guestTeam);
        scoreBoard.endGame(match);
        assertFalse(((ScoreBoardImpl) scoreBoard).getOngoingMatches().contains(match));
        scoreBoard.endGame(match);
        assertFalse(((ScoreBoardImpl) scoreBoard).getOngoingMatches().contains(match));
    }

    @Test
    void endGame_shouldRemoveMatch3() {
        String hostTeam = "Bulgaria";
        String guestTeam = "Japan";
        Match match = new Match(new Team(hostTeam), new Team(guestTeam));
        scoreBoard.endGame(match);
        assertFalse(((ScoreBoardImpl) scoreBoard).getOngoingMatches().contains(match));
    }

    @Test
    void updateScore() {
    }
}