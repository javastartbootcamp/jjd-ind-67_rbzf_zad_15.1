package pl.javastart.task.comparators;

import pl.javastart.task.Competitor;

import java.util.Comparator;

public class ScoreComparatorDescendingOrder implements Comparator<Competitor> {
    @Override
    public int compare(Competitor c1, Competitor c2) {
        return -Integer.compare(c1.getScore(), c2.getScore());
    }
}
