package org.example.shows.repository;

import org.example.shows.config.RepositoryProperties;
import org.example.shows.model.Show;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowFileRepository implements ShowRepository {
    private static final Path FILMS_FILE = Path.of("films.csv");
    private static final Path SERIES_FILE = Path.of("series.csv");
    private final RepositoryProperties repositoryProperties;

    public ShowFileRepository(RepositoryProperties repositoryProperties) {
        this.repositoryProperties = repositoryProperties;
    }

    @Override
    public List<Show> getAll() {
        try (
                Stream<String> filmCsv = fileLines(FILMS_FILE);
                Stream<String> seriesCsv = fileLines(SERIES_FILE)
        ) {
            return Stream.concat(
                    filmCsv.map(FilmDeserializer.INSTANCE::deserialize),
                    seriesCsv.map(SeriesDeserializer.INSTANCE::deserialize)
            ).collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private Stream<String> fileLines(Path filePath) throws IOException {
        return Files.lines(repositoryProperties.getFileStorage().resolve(filePath), StandardCharsets.UTF_8);
    }


}
