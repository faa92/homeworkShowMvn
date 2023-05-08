package org.example.shows.model.predicate;

import org.example.shows.model.Show;

import java.time.Year;
import java.util.function.Predicate;

public class ShowByReleaseYearPredicate implements Predicate<Show> {
    private final Year releaseYear;

    public ShowByReleaseYearPredicate(Year releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean test(Show show) {
        return show.getRealiseYear().equals(releaseYear);
    }
}
