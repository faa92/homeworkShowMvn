package org.example.shows.model;

import java.time.Year;

public class Film extends Show {
    public Film(
            String title,
            Year releaseYear,
            String countryCode,
            double rating,
            int marksCount
    ) {
        super(title, releaseYear, countryCode, rating, marksCount);
    }

    @Override
    public String toString() {
        return "[Фильм ] %-40s  %-9s  %s             %.1f  %7s"
                .formatted(title, releaseYear, countryCode, rating, marksCount);
    }
}
