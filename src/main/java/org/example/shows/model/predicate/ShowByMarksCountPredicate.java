package org.example.shows.model.predicate;

import org.example.shows.model.Show;

import java.util.function.Predicate;

public class ShowByMarksCountPredicate implements Predicate<Show> {
    private final int marksCountFrom;
    private final int marksCountTo;

    public ShowByMarksCountPredicate(int marksCountFrom, int marksCountTo) {
        if (marksCountFrom < 0) throw new IllegalArgumentException();
        if (marksCountTo < 0) throw new IllegalArgumentException();
        if (marksCountFrom > marksCountTo) throw new IllegalArgumentException();

        this.marksCountFrom = marksCountFrom;
        this.marksCountTo = marksCountTo;
    }

    @Override
    public boolean test(Show show) {
        return show.getMarksCount() >= marksCountFrom && show.getMarksCount() <= marksCountTo;
    }
}
