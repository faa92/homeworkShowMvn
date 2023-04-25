package org.example.shows;

import org.example.shows.config.RepositoryProperties;
import org.example.shows.model.Show;
import org.example.shows.repository.ShowFileRepository;
import org.example.shows.repository.ShowRepository;

import java.nio.file.Path;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Path fileStorageDir = Path.of(System.getenv("SHOWS_FILE_STORAGE_DIR"));
        RepositoryProperties repositoryProperties = new RepositoryProperties(fileStorageDir);

        ShowRepository showRepository = new ShowFileRepository(repositoryProperties);

        List<Show> shows = showRepository.getAll();
        for (Show show : shows) {
            System.out.println(show);
        }
    }
}
