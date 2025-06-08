package org.example.scoreboard;

import org.example.exception.ScoreBoardException;
import org.example.model.Match;
import org.example.model.Team;

import java.util.ArrayList;
import java.util.List;

class ScoreBoardImpl implements ScoreBoard {

    private static ScoreBoardImpl instance;
    private List<Match> ongoingMatches = new ArrayList<>();

    private ScoreBoardImpl() {
    }

    public static ScoreBoardImpl getInstance() {
        if (instance == null) {
            instance = new ScoreBoardImpl();
        }
        return instance;
    }

    protected void clearBoard() {
        this.ongoingMatches.clear();;
    }

    protected List<Match> getOngoingMatches() {
        return this.ongoingMatches;
    }

    @Override
    public Match startGame(String hostTeam, String guestTeam) {
        validate(hostTeam, guestTeam);
        Match match = new Match(new Team(hostTeam), new Team(guestTeam));
        ongoingMatches.add(match);
        return match;
    }

    private void validate(String hostTeam, String guestTeam) {
        if (hostTeam == null || guestTeam == null) {
            throw new ScoreBoardException("Team name must not be null. Host team: " + hostTeam + ", guestTeam: " + guestTeam);
        }
        if (hostTeam.isEmpty() || guestTeam.isEmpty()) {
            throw new ScoreBoardException("Team name must not be empty. Host team: " + hostTeam + ", guestTeam: " + guestTeam);
        }
        if (isNotOnlyLetters(hostTeam) || isNotOnlyLetters(guestTeam)) {
            throw new ScoreBoardException("Team name must contain only letters. Host team: " + hostTeam + ", guestTeam: " + guestTeam);
        }
        if (hostTeam.equalsIgnoreCase(guestTeam)) {
            throw new ScoreBoardException("Team name must be different. Host team: " + hostTeam + ", guestTeam: " + guestTeam);
        }
        if (isTeamAlreadyPlaying(hostTeam)) {
            throw new ScoreBoardException("Team " + hostTeam + " is already playing");
        }
        if (isTeamAlreadyPlaying(guestTeam)) {
            throw new ScoreBoardException("Team " + guestTeam + " is already playing");
        }
    }

    private boolean isNotOnlyLetters(String teamName) {
        return !teamName.matches("\\p{L}+");
    }

    private boolean isTeamAlreadyPlaying(String teamName) {
        return ongoingMatches.stream().anyMatch(match -> match.getGuestTeam().getName().equals(teamName) || match.getHostTeam().getName().equals(teamName));
    }

    @Override
    public void endGame(Match match) {
        ongoingMatches.remove(match);
    }

    @Override
    public void updateScore(Match match) {

    }

    @Override
    public void getSummary() {
        // implementation
    }
}
