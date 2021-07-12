package org.example.domain.spaceinvaders.infrastructure.repository.connectors;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class FileLoader {

    private final String rootFolder = System.getenv("INPUT_ROOT")!=null?System.getenv("INPUT_ROOT"):"src/main/resources/";


    public List<File> getFilesInDirectory(final String absolutePath) {
        log.info(rootFolder);
        final File path = new File(rootFolder + absolutePath);

        System.out.println(path.getPath());

        return Arrays.stream(Objects.requireNonNull(path.listFiles()))
                .peek(file -> log.info("File found {}", file.getPath()))
                .filter(File::isFile)
                .peek(file -> log.info("File found after filter {}", file.getPath()))
                .filter(File::canRead)
                .peek(file -> log.info("File which can be read {}", file.getPath())).collect(Collectors.toList());

    }

}
