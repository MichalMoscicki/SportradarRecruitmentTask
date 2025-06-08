package org.example.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardImplTest {

    @Test
    void getInstance_shouldReturnScoreBoard() {
        assertNotNull(ScoreBoardImpl.getInstance());
    }
}