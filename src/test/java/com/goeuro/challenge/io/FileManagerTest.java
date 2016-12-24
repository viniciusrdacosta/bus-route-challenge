package com.goeuro.challenge.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileManagerTest {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void shouldReadRoutesDataFromDefaultFileWhenFilePathIsEmpty() {
        FileManager manager = new FileManager("");
        assertThat(manager.routesData()).containsExactlyElementsOf(asList("0 0 1 2 3 4", "1 3 1 6 5", "2 0 6 4"));
    }

    @Test
    public void shouldReadRoutesDataFromDefaultFileWhenFilePathIsNull() {
        FileManager manager = new FileManager(null);
        assertThat(manager.routesData()).containsExactlyElementsOf(asList("0 0 1 2 3 4", "1 3 1 6 5", "2 0 6 4"));
    }

    @Test
    public void shouldReadRoutesDataFromCustomFileWhenFilePathIsProvided() throws IOException {
        FileManager manager = new FileManager(createTempFile());
        String fileContent = new StringBuilder()
                .append("2")
                .append(LINE_SEPARATOR)
                .append("0 0 1 2 3 4")
                .append(LINE_SEPARATOR)
                .append("1 3 1 6 5")
                .toString();

        writeFileContent(manager.getFilePath(), fileContent);

        assertThat(manager.routesData()).containsExactlyElementsOf(asList("0 0 1 2 3 4", "1 3 1 6 5"));
    }

    @Test
    public void shouldNotHaveNumberOfRoutesDifferentThanDefined() throws IOException {
        FileManager manager = new FileManager(createTempFile());
        String fileContent = new StringBuilder()
                .append("2")
                .append(LINE_SEPARATOR)
                .append("0 0 1 2 3 4")
                .toString();

        writeFileContent(manager.getFilePath(), fileContent);

        assertThatThrownBy(manager::routesData)
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Number of routes [1] is different than defined [2]");
    }

    @Test
    public void shouldThrowsNoSuchFileExceptionWhenInvalidFilePathIsProvided() {
        FileManager manager = new FileManager("invalidPath");
        assertThatThrownBy(manager::routesData)
                .isInstanceOf(NoSuchFileException.class)
                .hasMessageContaining("invalidPath");
    }

    private String createTempFile() throws IOException {
        return tempFolder.newFile("routesData").getPath();
    }

    private void writeFileContent(String tempFilePath, String fileContent) throws IOException {
        Files.write(Paths.get(tempFilePath), fileContent.getBytes(Charset.defaultCharset()));
    }
}