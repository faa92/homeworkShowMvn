package org.example.shows.repository;

import org.example.shows.model.Film;
import org.example.shows.model.Show;

import java.time.Year;

public class FilmDeserializer implements ShowDeserializer {
    @Override
    public Show deserialize(String line) {
        String[] parts = line.split(",");
        return new Film(
                parts[0],
                Year.parse(parts[1]),
                parts[2],
                Double.parseDouble(parts[3]),
                Integer.parseInt(parts[4])
        );
    }
}
