package org.example.scoreboard;

import org.example.model.Match;

import java.util.ArrayList;
import java.util.List;

class ScoreBoardImpl implements ScoreBoard {

    private static ScoreBoardImpl instance;
    private List<Match> ongoingMatches = new ArrayList<>();

    private ScoreBoardImpl() {
        // private constructor to prevent instantiation
    }

    public static ScoreBoardImpl getInstance() {
        if (instance == null) {
            instance = new ScoreBoardImpl();
        }
        return instance;
    }

    @Override
    public void startGame() {
        // implementation
    }

    @Override
    public void endGame() {
        // implementation
    }

    @Override
    public void updateScore() {
        // implementation
    }

    @Override
    public void getSummary() {
        // implementation
    }
}
