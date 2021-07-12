package org.example.domain.spaceinvaders.infrastructure.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.spaceinvaders.core.model.Radar;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;
import org.example.domain.spaceinvaders.infrastructure.repository.connectors.FileLoader;
import org.example.domain.spaceinvaders.infrastructure.repository.interfaces.RadarDataRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class LocalRadarDataRepository implements RadarDataRepository {

    private final FileLoader fileLoader = new FileLoader();

    private final Function<File, List<String>> convert = file -> {
        try {
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error(e.toString());
            return Collections.emptyList();
        }

    };

    @Override
    public Radar get() {

        List<String> radarContent = fileLoader
                .getFilesInDirectory("radar")
                .stream()
                .flatMap(file -> convert.apply(file).stream())
                .collect(Collectors.toList());
        return new Radar(new Matrix(radarContent));
    }
}
