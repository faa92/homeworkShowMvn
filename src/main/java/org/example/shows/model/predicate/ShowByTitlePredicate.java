package org.example.shows.model.predicate;

import org.example.shows.model.Show;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowByTitlePredicate implements Predicate<Show> {
    private final Pattern pattern;

    public ShowByTitlePredicate(String query) {
        this.pattern = Pattern.compile(
                "\\b" + Pattern.quote(query),
                Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE
                        | Pattern.UNICODE_CASE);
    }

    @Override
    public boolean test(Show show) {
        Matcher matcher = pattern.matcher(show.getTitle());
        return matcher.find();
    }
}
