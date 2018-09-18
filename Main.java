package com.dft;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Player p1 = new Player("Radovic", 7);
        Player p2 = new Player("Ronaldo", 7);
        Player p3 = new Player("Messi", 10);
        Player p4 = new Player("Lewandowski", 9);
        Player p5 = new Player("Mbappe", 2);
        Player p6 = new Player("Hazard", 10);

        Team t1 = new Team("Legia");
        Team t2 = new Team("Real");
        Team t3 = new Team("Barcelona");
        Team t4 = new Team("Bayern");
        Team t5 = new Team("PSG");
        Team t6 = new Team("Chealse");

        LocalDateTime d1 = LocalDateTime.of(2018,8,6, 20,30);
        LocalDateTime d2 = LocalDateTime.of(2018,8,6, 21,30);
        LocalDateTime d3 = LocalDateTime.of(2018,7,7, 20,30);
        LocalDateTime d4 = LocalDateTime.of(2018,7,8, 20,30);
        LocalDateTime d5 = LocalDateTime.of(2018,8,9, 20,30);
        LocalDateTime d6 = LocalDateTime.of(2018,8,10, 20,30);

        Match m1 = new Match(d1,t1,t2);
        Match m2 = new Match(d2,t3,t4);
        Match m3 = new Match(d3,t5,t6);
        Match m4 = new Match(d4,t1,t3);
        Match m5 = new Match(d5,t2,t5);
        Match m6 = new Match(d6,t3,t6);

        MatchServiceInterface service = new MatchService();

        service.addPlayerToTeam(p1,t1);
        service.addPlayerToTeam(p1,t1);
        service.addPlayerToTeam(p2,t2);
        service.addPlayerToTeam(p3,t3);
        service.addPlayerToTeam(p4,t4);
        service.addPlayerToTeam(p5,t5);
        service.addPlayerToTeam(p6,t6);

        System.out.println();

        service.matchPlanning(m1);
        service.matchPlanning(m2);
        service.matchPlanning(m3);
        service.matchPlanning(m4);
        service.matchPlanning(m5);
        service.matchPlanning(m6);

        System.out.println();

        service.resultOfMatch(m1, 1,1);
        service.resultOfMatch(m2, 2,1);
        service.resultOfMatch(m3, 1,2);
        service.resultOfMatch(m4, 1,1);
        service.resultOfMatch(m5, 0,3);
        service.resultOfMatch(m6, 2,3);

        System.out.println();

        System.out.println(t1.getNumberOfDraws());
        System.out.println(service.statistic(t1,MatchResult.REMIS));
        System.out.println(service.statistic(t2,MatchResult.WYGRANY));
        System.out.println(service.statistic(t3,MatchResult.WYGRANY));
        System.out.println(service.statistic(t5,MatchResult.PRZEGRANY));

        System.out.println();

        System.out.println(service.matchesPlanned(LocalDateTime.now()));
        System.out.println(service.matchesPlanned(d4));

        System.out.println();

        System.out.println(service.matchesBetweenDates(d1,d6));
        System.out.println(service.matchesBetweenDates(d1.minusMinutes(1),d6.plusMinutes(1)));

        System.out.println();
        System.out.println(service.rankingTeamsByWons());
        System.out.println();
        System.out.println(service.teamsInAlphabeticalOrder());






    }
}
