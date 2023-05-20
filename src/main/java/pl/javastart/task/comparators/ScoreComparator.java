package pl.javastart.task.comparators;

import pl.javastart.task.Competitor;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Competitor> {
    public int compare(Competitor c1, Competitor c2) {
        return Integer.compare(c1.getScore(), c2.getScore());
    }
}

