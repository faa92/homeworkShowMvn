package org.example.shows.model.comparator;

import org.example.shows.model.Show;

import java.util.Comparator;

public final class ShowByTitleComparator implements Comparator<Show> {
    public static final Comparator<Show> INSTANCE = new ShowByTitleComparator();

    public ShowByTitleComparator() {
    }

    @Override
    public int compare(Show show1, Show show2) {
        return show1.getTitle().compareTo(show2.getTitle());
    }
}
