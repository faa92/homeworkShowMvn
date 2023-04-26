package org.example.shows.config;

import java.nio.file.Path;

public class RepositoryProperties {
    private final Path fileStorage;

    public RepositoryProperties(Path fileStorage) {
        this.fileStorage = fileStorage;
    }

    public Path getFileStorage() {
        return fileStorage;
    }
}
