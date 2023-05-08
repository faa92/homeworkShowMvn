package org.example.shows.model.predicate;

import org.example.shows.model.Show;

import java.util.function.Predicate;

public class ShowByRatingPredicate implements Predicate<Show> {
    private final double ratingFrom;
    private final double ratingTo;

    public ShowByRatingPredicate(double ratingFrom, double ratingTo) {
        if (ratingFrom < Show.MIN_RATING || ratingFrom > Show.MAX_RATING) throw new IllegalArgumentException();
        if (ratingTo < Show.MIN_RATING || ratingTo > Show.MAX_RATING) throw new IllegalArgumentException();
        this.ratingFrom = ratingFrom;
        this.ratingTo = ratingTo;
    }

    @Override
    public boolean test(Show show) {
        return show.getRating() >= ratingFrom && show.getRating() <= ratingTo;
    }
}
