package org.example.scoreboard;

import org.example.exception.ScoreBoardException;
import org.example.model.Match;
import org.example.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
    void updateScore_shouldReturnError_NoOngoingMatch() {
        String hostTeam1 = "Bulgaria";
        String guestTeam1 = "Japan";
        String message = "No ongoing match between " + hostTeam1 + " and " + guestTeam1;

        Match match = scoreBoard.startGame(hostTeam1, guestTeam1);
        scoreBoard.endGame(match);
        match.increaseGuestTeamScore();

        Exception exception = assertThrows(ScoreBoardException.class, () -> scoreBoard.updateScore(match));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void updateScore_scoreUpdated() {
        String hostTeam1 = "Bulgaria";
        String guestTeam1 = "Japan";

        Match match = scoreBoard.startGame(hostTeam1, guestTeam1);
        match.increaseGuestTeamScore();
        scoreBoard.updateScore(match);

        assertEquals(((ScoreBoardImpl) scoreBoard).getOngoingMatches().getFirst().getGuestTeamScore(), match.getGuestTeamScore());
    }

    @Test
    void getSummary_differentScores() {
        Match match1 = scoreBoard.startGame("Bulgaria", "Japan");
        Match match2 = scoreBoard.startGame("Poland", "Germany");
        Match match3 = scoreBoard.startGame("Lithuania", "Spain");

        match1.increaseGuestTeamScore();
        scoreBoard.updateScore(match1);

        match3.increaseGuestTeamScore();
        match3.increaseHostTeamScore();
        scoreBoard.updateScore(match1);

        List<Match> properOrderList = Arrays.asList(match3, match1, match2);

        assertEquals(properOrderList, scoreBoard.getSummary());
    }

    @Test
    void getSummary_sameScores() {
        Match match1 = scoreBoard.startGame("Bulgaria", "Japan");
        Match match2 = scoreBoard.startGame("Poland", "Germany");
        Match match3 = scoreBoard.startGame("Turkey", "England");

        match3.increaseHostTeamScore();
        scoreBoard.updateScore(match3);

        match2.increaseHostTeamScore();
        scoreBoard.updateScore(match2);

        match1.increaseGuestTeamScore();
        scoreBoard.updateScore(match1);

        List<Match> properOrderList = Arrays.asList(match1, match2, match3);

        assertEquals(properOrderList, scoreBoard.getSummary());
    }

    @Test
    void getSummary_mixedScores() {
        Match match1 = scoreBoard.startGame("Bulgaria", "Japan");
        Match match2 = scoreBoard.startGame("Poland", "Germany");
        Match match3 = scoreBoard.startGame("Turkey", "England");
        Match match4 = scoreBoard.startGame("Lithuania", "Spain");


        match1.increaseGuestTeamScore();
        scoreBoard.updateScore(match1);

        match3.increaseHostTeamScore();
        scoreBoard.updateScore(match3);

        match4.increaseGuestTeamScore();
        match4.increaseHostTeamScore();
        scoreBoard.updateScore(match1);

        List<Match> properOrderList = Arrays.asList(match4, match1, match3, match2);

        assertEquals(properOrderList, scoreBoard.getSummary());
    }

}