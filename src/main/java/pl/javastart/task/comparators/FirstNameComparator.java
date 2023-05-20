package pl.javastart.task.comparators;

import pl.javastart.task.Competitor;

import java.util.Comparator;

public class FirstNameComparator implements Comparator<Competitor> {
    public int compare(Competitor c1, Competitor c2) {
        return c1.getFirstName().compareTo(c2.getFirstName());
    }
}


