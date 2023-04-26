package org.example.shows.service;

import org.example.shows.model.Show;
import org.example.shows.repository.ShowRepository;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;

    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public List<Show> query(List<Predicate<Show>> filters, List<Comparator<Show>> sorting) {
        List<Show> shows = showRepository.getAll();
        applyFilters(shows, filters);
        applySorting(shows, sorting);
        return shows;
    }

    private void applyFilters(List<Show> shows, List<Predicate<Show>> filters) {
        if (!filters.isEmpty()) {
            Iterator<Predicate<Show>> filtersIterator = filters.iterator();
            Predicate<Show> finalFilter = filtersIterator.next();
            while (filtersIterator.hasNext()) {
                Predicate<Show> next = filtersIterator.next();
                finalFilter = finalFilter.and(next);
            }
            shows.removeIf(finalFilter.negate());
        }
    }

    private void applySorting(List<Show> shows, List<Comparator<Show>> sorting) {
        if (!sorting.isEmpty()) {
            Iterator<Comparator<Show>> sortingIterator = sorting.iterator();
            Comparator<Show> finalSorting = sortingIterator.next();
            while (sortingIterator.hasNext()) {
                Comparator<Show> next = sortingIterator.next();
                finalSorting = finalSorting.thenComparing(next);
            }
            shows.sort(finalSorting);
        }

    }
}
