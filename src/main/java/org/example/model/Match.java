package org.example.model;

import java.time.LocalDate;

public class Match {

    private Team hostTeam;
    private Team guestTeam;
    private int hostTeamScore= 0;
    private int guestTeamScore = 0;
    private LocalDate createDate;

    public Match(Team hostTeam, Team guestTeam) {
        this.hostTeam = hostTeam;
        this.guestTeam = guestTeam;
        createDate = LocalDate.now();
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
        this.hostTeamScore ++;
    }

    public int getGuestTeamScore() {
        return guestTeamScore;
    }

    public void increaseGuestTeamScore() {
        this.guestTeamScore ++;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
