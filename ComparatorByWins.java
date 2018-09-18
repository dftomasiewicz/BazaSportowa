package com.dft;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ComparatorByWins implements Comparator<Team> {

    @Override
    public int compare(Team o1, Team o2) {
        return o1.getNumberOfWins()-o2.getNumberOfWins();
    }
}
