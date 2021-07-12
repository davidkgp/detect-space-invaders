package org.example.domain.spaceinvaders.infrastructure.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.spaceinvaders.core.model.Invader;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;
import org.example.domain.spaceinvaders.infrastructure.repository.connectors.FileLoader;
import org.example.domain.spaceinvaders.infrastructure.repository.exceptions.InvaderPatternNotinDataStoreException;
import org.example.domain.spaceinvaders.infrastructure.repository.interfaces.InvaderRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class LocalInvaderFileRepository implements InvaderRepository {
    private final FileLoader fileLoader = new FileLoader();

    private final Function<File, Optional<Invader>> convert = file -> {
        try {
            return Optional.of(new Invader(file.getName(), new Matrix(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8))));
        } catch (IOException e) {
            log.error(e.toString());
            return Optional.empty();
        }

    };

    @Override
    public List<Invader> get() {

        return fileLoader
                .getFilesInDirectory("invader")
                .stream()
                .map(convert)
                .map(optionalInvaderData ->
                        optionalInvaderData
                                .orElseThrow(() -> new InvaderPatternNotinDataStoreException("No data founc")))
                .collect(Collectors.toList());
    }


}
