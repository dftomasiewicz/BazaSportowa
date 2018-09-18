package com.dft;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;

public class MatchService implements MatchServiceInterface {


    Set<Match> matchSet = new HashSet<>();
    Set<Team> teams = new HashSet<>();

    public MatchService() {
        System.out.println("Aktywowano obsluge meczy\n");
    }

    @Override
    public void matchPlanning(Match match) {
        if (match.getDate().isAfter(LocalDateTime.now())) {
            matchSet.add(match);
            teams.add(match.getTeam1());
            teams.add(match.getTeam2());
            System.out.println("Zaplanowano mecz " + match);
        } else {
            System.out.println("Nie mozna zaplanowac meczu na te date, jest wczesniejsza niz akutalna");
        }
    }

    @Override
    public boolean resultOfMatch(Match match, int scoreTeam1, int scoreTeam2) {
        for (Match m : matchSet) {
            if (m.equals(match)) {
                if (m.getMatchStatus().equals(MatchStatus.ANULOWANY)) {
                    System.out.println("Mecz zostal anulowany, nie da sie mu przypisac wyniku");
                    return false;
                }
                if (m.getDate().isBefore(LocalDateTime.now())) {
                    m.writeResult(scoreTeam1, scoreTeam2);
                    return true;
                }
                System.out.println("Nie mozesz jeszcze wpisac wyniku, mecz sie nie zakonczyl");
                return false;
            }
        }
        if (match.getDate().isBefore(LocalDateTime.now())) {
            matchSet.add(match);
            teams.add(match.getTeam1());
            teams.add(match.getTeam2());
            return resultOfMatch(match, scoreTeam1, scoreTeam2);
        } else {
            System.out.println("No panie, tu sie wpisuje wynik meczu, ktory sie zakonczyl, zaplanuj go najpierw");
            return false;
        }

    }

    //    bardzo uproszczona jest ta metoda dodawania zawonika, mozna go np dodac do kilku druzyn, co jest chyba takie nie za bardzo
    @Override
    public boolean addPlayerToTeam(Player player, Team team) {
        if (team.getPlayers().contains(player)) {
            System.out.println("Ten zawodnik juz gra w tej druzynie");
            return false;
        }
        System.out.println("Dodano zawodnika " + player.getName());
        return team.getPlayers().add(player);
    }

    @Override
    public int statistic(Team team, Enum<MatchResult> whichResult) {
        if (whichResult.equals(MatchResult.WYGRANY)) {
            if (teams.contains(team)) {
                return team.getNumberOfWins();
            } else {
                System.out.println("1Nie ma takiej druzyny w naszych rozgrywkach");
                return -1;
            }
        } else if (whichResult.equals((MatchResult.PRZEGRANY))) {
            if (teams.contains(team)) {
                return team.getNumberOfLoses();
            } else {
                System.out.println("2Nie ma takiej druzyny w naszych rozgrywkach");
                return -1;
            }
        } else {
            if (teams.contains(team)) {
                return team.getNumberOfDraws();
            } else {
                System.out.println("3Nie ma takiej druzyny w naszych rozgrywkach");
                return -1;
            }
        }
    }

//    mozna by tez nie podawac parametru w metodzie, tylko przyjac ze zaplanowane od teraz LocalDateTime.now
    @Override
    public List<Match> matchesPlanned(LocalDateTime sinceWhen) {
        List<Match> plannedMatches = new ArrayList<>();
        for (Match m : matchSet) {
            if (m.getDate().isAfter(sinceWhen)) {
                plannedMatches.add(m);
            }
        }
        Collections.sort(plannedMatches);
        return plannedMatches;
    }

    @Override
    public List<Match> matchesBetweenDates(LocalDateTime start, LocalDateTime end) {
        List<Match> matchesList = new ArrayList<>();
        for (Match m : matchSet) {
            if (m.getDate().isAfter(start) && m.getDate().isBefore(end)) {
                matchesList.add(m);
            }
        }
        Collections.sort(matchesList);
        return matchesList;
    }

    @Override
    public List<Team> rankingTeamsByWons() {
        List<Team> teamsByWons = new ArrayList<>();
        for (Team t : teams) {
            teamsByWons.add(t);
        }

//        ComparatorByWins cbw = new ComparatorByWins(teamsByWons);
//        tak nie, tak jak ponizej

        Collections.sort(teamsByWons,Collections.reverseOrder(new ComparatorByWins()));
        return teamsByWons;
    }

    @Override
    public List<Team> teamsInAlphabeticalOrder() {
        List<Team> alphabeticOrder = new ArrayList<>();
        for (Team t : teams) {
            alphabeticOrder.add(t);
        }
        Collections.sort(alphabeticOrder);
        return alphabeticOrder;
    }
}
