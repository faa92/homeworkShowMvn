package org.example.shows.model;

import java.time.Year;

public class Series extends Show {
    private final Year lastSeasonRelease;
    private final int seasonsCount;
    private final int episodesCount;

    public Series(
            String title,
            Year releaseYear,
            Year lastSeasonRelease,
            String countryCode,
            int seasonsCount,
            int episodesCount,
            double rating,
            int marksCount
    ) {
        super(title, releaseYear, countryCode, rating, marksCount);
        if (seasonsCount < 0) throw new IllegalArgumentException("Неверное количество сезонов");
        if (episodesCount < 0) throw new IllegalArgumentException("Неверное количество эпизодов");
        this.lastSeasonRelease = lastSeasonRelease;
        this.seasonsCount = seasonsCount;
        this.episodesCount = episodesCount;
    }

    @Override
    public String toString() {
        return "[Сериал] %-40s  %s–%s  %s  %2s  %3s  %.1f  %7s"
                .formatted(title, releaseYear, lastSeasonRelease, countryCode, seasonsCount,
                        episodesCount, rating, marksCount);
    }
}