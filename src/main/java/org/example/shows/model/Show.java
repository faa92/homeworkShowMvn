package org.example.shows.model;

import java.time.Year;

public abstract class Show {
    public static final double MIN_RATING = 0;
    public static final double MAX_RATING = 10;
    public static final int COUNTRY_CODE_LENGTH = 2;

    protected final String title;
    protected final Year releaseYear;
    protected final String countryCode;
    protected final double rating;
    protected final int marksCount;

    public Show(String title, Year releaseYear, String countryCode, double rating, int marksCount) {
        if (title.isEmpty()) throw new IllegalArgumentException("Нет названия");
        if (countryCode.length() != COUNTRY_CODE_LENGTH) throw new IllegalArgumentException("Неверный код страны");
        if (rating < MIN_RATING || rating > MAX_RATING) throw new IllegalArgumentException("Неверный реётинг");
        if (marksCount < 0) throw new IllegalArgumentException("Неверное количество оценок");
        this.title = title;
        this.releaseYear = releaseYear;
        this.countryCode = countryCode;
        this.rating = rating;
        this.marksCount = marksCount;
    }

    public String getTitle() {
        return title;
    }

    public Year getRealiseYear() {
        return releaseYear;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public double getRating() {
        return rating;
    }

    public int getMarksCount() {
        return marksCount;
    }
}
