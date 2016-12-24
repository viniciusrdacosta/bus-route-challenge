package com.goeuro.challenge.io;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class FileManager {

    static String SAMPLE_DATA_PATH = "data/sample-data";
    @Getter String filePath;

    public FileManager(@Value("${routesFilePath}") String filePath) {
        this.filePath = filePath;
    }

    @SneakyThrows
    public List<String> routesData() {
        List<String> routesData = Files.readAllLines(getPath());
        return routes(routesData);
    }

    @SneakyThrows
    private Path getPath() {
        return isNullOrEmpty(filePath)
                ? Paths.get(ClassLoader.getSystemResource(SAMPLE_DATA_PATH).toURI())
                : Paths.get(filePath);
    }

    private List<String> routes(List<String> data) {
        List<String> validRoutes = data.subList(1, data.size());
        validateNumberOfRoutes(Integer.parseInt(data.get(0)), validRoutes.size());
        ;
        return validRoutes;
    }

    private void validateNumberOfRoutes(int numberOfRoutes, int actualNumberOfRoutes) {
        if (numberOfRoutes != actualNumberOfRoutes) {
            throw new RuntimeException(format("Number of routes [%s] is different than defined [%s]",
                    actualNumberOfRoutes, numberOfRoutes));
        }
    }

    private boolean isNullOrEmpty(String file) {
        return file == null || file.isEmpty();
    }
}
