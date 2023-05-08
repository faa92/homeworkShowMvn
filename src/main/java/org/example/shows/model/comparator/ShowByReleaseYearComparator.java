package org.example.shows.model.comparator;

import org.example.shows.model.Show;

import java.util.Comparator;

public class ShowByReleaseYearComparator implements Comparator<Show> {
    public static final Comparator<Show> INSTANCE = new ShowByReleaseYearComparator();

    public ShowByReleaseYearComparator() {
    }

    @Override
    public int compare(Show show1, Show show2) {
        return show1.getRealiseYear().compareTo(show2.getRealiseYear());
    }
}
