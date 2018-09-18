package com.dft;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Team implements Comparable<Team> {
    private String name;
    private Set<Player> players;
    private int numberOfWins;
    private int numberOfLoses;
    private int numberOfDraws;
    private MatchResult matchResult;

    public Team(String name) {
        this.name = name;
        this.players = new HashSet<>();
        this.numberOfWins = 0;
        this.numberOfLoses = 0;
        this.numberOfDraws = 0;
    }

    public String getName() {
        return name;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public int getNumberOfLoses() {
        return numberOfLoses;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public int changePoints(MatchResult matchResult){
        if(matchResult == MatchResult.WYGRANY){
            return numberOfWins++;
         } else if (matchResult == MatchResult.PRZEGRANY){
            return numberOfLoses++;
        } else {
            return numberOfDraws++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return  Objects.equals(name, team.name);

    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", players=" + players +
                ", numberOfWins=" + numberOfWins +
                ", numberOfLoses=" + numberOfLoses +
                ", numberOfDraws=" + numberOfDraws +
                ", matchResult=" + matchResult +
                '}';
    }


    @Override
    public int compareTo(Team t) {
        return this.getName().compareTo(t.getName());
    }
}
