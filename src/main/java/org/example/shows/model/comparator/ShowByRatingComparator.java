package org.example.shows.model.comparator;

import org.example.shows.model.Show;

import java.util.Comparator;

public class ShowByRatingComparator implements Comparator<Show> {
    public static final Comparator<Show> INSTANCE = new ShowByRatingComparator();

    private ShowByRatingComparator() {
    }

    @Override
    public int compare(Show show1, Show show2) {
        return Double.compare(show1.getRating(), show2.getRating());
    }
}
