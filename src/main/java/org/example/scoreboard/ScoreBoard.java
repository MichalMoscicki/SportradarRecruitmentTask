package org.example.scoreboard;

import org.example.model.Match;

public interface ScoreBoard {

    Match startGame(String hostTeam, String guestTeam);
    void endGame(Match match);
    void updateScore(Match match);
    void getSummary();

}