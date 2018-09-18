package com.dft;

import java.time.LocalDateTime;
import java.util.Objects;

public class Match implements Comparable<Match> {
    private Team team1;
    private Team team2;
    private LocalDateTime date;
    private MatchStatus matchStatus;

    public Match(LocalDateTime date, Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        if (date.isAfter(LocalDateTime.now())) {
            this.matchStatus = MatchStatus.ZAPLANOWANY;
        } else {
            this.matchStatus = MatchStatus.ZAKONCZONY;
        }
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public void writeResult(int scoreTeam1, int scoreTeam2) {
        matchStatus = MatchStatus.ZAKONCZONY;
        if (scoreTeam1 > scoreTeam2) {
            System.out.println("Wygrala druzyna " + team1.getName());
            this.team1.changePoints(MatchResult.WYGRANY);
            this.team2.changePoints(MatchResult.PRZEGRANY);
        } else if (scoreTeam1 < scoreTeam2) {
            System.out.println("Wygrala druzyna " + team2.getName());
            this.team1.changePoints(MatchResult.PRZEGRANY);
            this.team2.changePoints(MatchResult.WYGRANY);
        } else {
            System.out.println("Druzyny " + team1.getName() + " i " + team2.getName() + " zremisowaly");
            this.team1.changePoints(MatchResult.REMIS);
            this.team2.changePoints(MatchResult.REMIS);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(team1, match.team1) &&
                Objects.equals(team2, match.team2) &&
                Objects.equals(date, match.date) &&
                matchStatus == match.matchStatus;
    }

    @Override
    public int hashCode() {

        return Objects.hash(team1, team2, date, matchStatus);
    }

    @Override
    public String toString() {
        return "{"
                + team1.getName() +
                " vs. " + team2.getName() +
                " w dniu: " + date +
                ", matchStatus=" + matchStatus +
                '}';
    }

    @Override
    public int compareTo(Match m) {
        return getDate().compareTo(m.getDate());
    }
}
