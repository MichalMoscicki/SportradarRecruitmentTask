package org.example.model;

import java.time.LocalDateTime;

public class Match {

    private Team hostTeam;
    private Team guestTeam;
    private int hostTeamScore = 0;
    private int guestTeamScore = 0;
    private LocalDateTime createDate;

    public Match(Team hostTeam, Team guestTeam) {
        this.hostTeam = hostTeam;
        this.guestTeam = guestTeam;
        createDate = LocalDateTime.now();
    }

    public Team getHostTeam() {
        return hostTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public int getHostTeamScore() {
        return hostTeamScore;
    }

    public void increaseHostTeamScore() {
        this.hostTeamScore++;
    }

    public int getGuestTeamScore() {
        return guestTeamScore;
    }

    public void increaseGuestTeamScore() {
        this.guestTeamScore++;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void merge(Match matchParam) {
        this.hostTeamScore = matchParam.getHostTeamScore();
        this.guestTeamScore = matchParam.getGuestTeamScore();
    }

}
