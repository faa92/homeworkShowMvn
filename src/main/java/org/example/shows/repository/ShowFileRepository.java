package org.example.shows.repository;

import org.example.shows.config.RepositoryProperties;
import org.example.shows.model.Show;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ShowFileRepository implements ShowRepository {
    private static final Path FILMS_FILE = Path.of("films.csv");
    private static final Path SERIES_FILE = Path.of("series.csv");
    private final RepositoryProperties repositoryProperties;

    public ShowFileRepository(RepositoryProperties repositoryProperties) {
        this.repositoryProperties = repositoryProperties;
    }

    @Override
    public List<Show> getAll() {
        List<Show> shows = new ArrayList<>();
        readAllShows(shows, FILMS_FILE, new FilmDeserializer());
        readAllShows(shows, SERIES_FILE, new SeriesDeserializer());

        return shows;
    }

    private void readAllShows(List<Show> destination, Path path, ShowDeserializer deserializer) {
        Path seriesPath = repositoryProperties.getFileStorage().resolve(path);
        try (BufferedReader reader = Files.newBufferedReader(seriesPath)) {
            String csvLine = reader.readLine();
            while (csvLine != null) {
                Show show = deserializer.deserialize(csvLine);
                destination.add(show);
                csvLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
