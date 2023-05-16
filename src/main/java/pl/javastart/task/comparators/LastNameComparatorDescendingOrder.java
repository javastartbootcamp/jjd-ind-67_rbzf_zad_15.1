package pl.javastart.task.comparators;

import pl.javastart.task.Competitor;

import java.util.Comparator;

public class LastNameComparatorDescendingOrder implements Comparator<Competitor> {
    @Override
    public int compare(Competitor c1, Competitor c2) {
        return -c1.getLastName().compareTo(c2.getLastName());
    }
}
