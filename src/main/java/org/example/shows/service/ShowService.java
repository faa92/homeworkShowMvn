package org.example.shows.service;

import org.example.shows.model.Show;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface ShowService {
    List<Show> query(List<Predicate<Show>> filters, List<Comparator<Show>> sorting);
}
