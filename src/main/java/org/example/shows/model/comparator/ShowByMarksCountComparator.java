package org.example.shows.model.comparator;

import org.example.shows.model.Show;

import java.util.Comparator;

public class ShowByMarksCountComparator implements Comparator<Show> {
    public static final Comparator<Show> INSTANCE = new ShowByMarksCountComparator();

    @Override
    public int compare(Show show1, Show show2) {
        return Integer.compare(show1.getMarksCount(), show2.getMarksCount());
    }
}
