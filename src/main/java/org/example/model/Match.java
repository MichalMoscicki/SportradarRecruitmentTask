package org.example.model;

public class Match {

    private Team hostTeam;
    private Team guestTeam;
    private int hostTeamScore= 0;
    private int guestTeamScore = 0;

    public Match(Team hostTeam, Team guestTeam) {
        this.hostTeam = hostTeam;
        this.guestTeam = guestTeam;
    }

    public Team getHostTeam() {
        return hostTeam;
    }

    public void setHostTeam(Team hostTeam) {
        this.hostTeam = hostTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Team guestTeam) {
        this.guestTeam = guestTeam;
    }

    public int getHostTeamScore() {
        return hostTeamScore;
    }

    public void setHostTeamScore(int hostTeamScore) {
        this.hostTeamScore = hostTeamScore;
    }

    public int getGuestTeamScore() {
        return guestTeamScore;
    }

    public void setGuestTeamScore(int guestTeamScore) {
        this.guestTeamScore = guestTeamScore;
    }
}
