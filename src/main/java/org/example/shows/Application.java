package org.example.shows;

import org.example.shows.config.RepositoryProperties;
import org.example.shows.model.Show;
import org.example.shows.model.comparator.ShowByMarksCountComparator;
import org.example.shows.model.comparator.ShowByRatingComparator;
import org.example.shows.model.comparator.ShowByReleaseYearComparator;
import org.example.shows.model.comparator.ShowByTitleComparator;
import org.example.shows.model.predicate.*;
import org.example.shows.repository.ShowFileRepository;
import org.example.shows.repository.ShowRepository;
import org.example.shows.service.ShowService;
import org.example.shows.service.ShowServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.time.Year;
import java.util.*;
import java.util.function.Predicate;

public class Application {
    private static final Logger log = LoggerFactory.getLogger("Application");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        log.info("Программа запущена");

        Path fileStorageDir = Path.of(System.getenv("SHOWS_FILE_STORAGE_DIR"));
        RepositoryProperties repositoryProperties = new RepositoryProperties(fileStorageDir);

        ShowRepository showRepository = new ShowFileRepository(repositoryProperties);
        ShowService showService = new ShowServiceImpl(showRepository);

        List<Predicate<Show>> filters = requestFilters();
        List<Comparator<Show>> sorting = requestSorting();

        List<Show> shows = showService.query(filters, sorting);

        if (shows.isEmpty()) {
            System.out.println("Ничего не найдено");
        } else {
            for (Show show : shows) {
                System.out.println(show);
            }
        }
    }

    private static List<Predicate<Show>> requestFilters() {
        List<Predicate<Show>> filters = new ArrayList<>();
        System.out.println("""
                Add filter:
                  byCountry <countryCode>
                  byMarksCount <from> <to>
                  byRating <from> <to>
                  byReleaseYear <year>
                  byTitlePredicate <query>
                  end""");
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            String[] parts = command.split("\\s+");
            if (parts[0].equals("end")) return filters;

            Predicate<Show> filter = switch (parts[0]) {
                case "byCountry" -> new ShowByCountryPredicate(parts[0]);
                case "byMarksCount" -> new ShowByMarksCountPredicate(Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]));
                case "byRating" -> new ShowByRatingPredicate(Double.parseDouble(parts[1]),
                        Double.parseDouble(parts[2]));
                case "byReleaseYear" -> new ShowByReleaseYearPredicate(Year.parse(parts[1]));
                case "byTitlePredicate" -> new ShowByTitlePredicate(String.join(" ",
                        Arrays.asList(parts).subList(1, parts.length)));
                default -> throw new IllegalArgumentException("Unknown command");
            };
            filters.add(filter);
        }

    }

    public static List<Comparator<Show>> requestSorting() {
        List<Comparator<Show>> sorting = new ArrayList<>();
        System.out.println("""
                Add sorting:
                  byMarksCount direct|reversed
                  byRating direct|reversed
                  byReleaseYear direct|reversed
                  byTitle direct|reversed
                  end""");
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            String[] parts = command.split("\\s+");
            if (parts[0].equals("end")) return sorting;

            Comparator<Show> comparator = switch (parts[0]) {
                case "byMarksCount" -> ShowByMarksCountComparator.INSTANCE;
                case "byRating" -> ShowByRatingComparator.INSTANCE;
                case "byReleaseYear" -> ShowByReleaseYearComparator.INSTANCE;
                case "byTitle" -> ShowByTitleComparator.INSTANCE;
                default -> throw new IllegalArgumentException("Unknown command");
            };
            comparator = switch (parts[1]) {
                case "direct" -> comparator;
                case "reversed" -> comparator.reversed();
                default -> throw new IllegalArgumentException("Unknown sorting direction");
            };
            sorting.add(comparator);
        }
    }
}