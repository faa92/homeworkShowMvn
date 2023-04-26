package org.example.shows.repository;

import org.example.shows.model.Show;

public interface ShowDeserializer {
    Show deserialize(String line);
}
