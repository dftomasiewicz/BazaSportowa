package com.dft;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface MatchServiceInterface {
    void matchPlanning(Match match);
    boolean resultOfMatch(Match match, int scoreTeam1, int scoreTeam2);
    boolean addPlayerToTeam(Player player, Team team);
    int statistic (Team team, Enum<MatchResult> whichResult);
    List<Match> matchesPlanned(LocalDateTime sinceWhen);
    List<Match> matchesBetweenDates(LocalDateTime start, LocalDateTime end);
    List<Team> rankingTeamsByWons();
    List<Team> teamsInAlphabeticalOrder();
}
