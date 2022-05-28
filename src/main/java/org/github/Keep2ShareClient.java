package org.github;

import org.github.commands.*;
import org.github.exceptions.Keep2ShareAuthenticationException;
import org.github.exceptions.Keep2ShareException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Keep2ShareClient {

    private static final String ACCESS_TOKEN = "access_token";

    private String accessToken;

    public Keep2ShareClient setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public static Keep2ShareClient of(String token) {
        return new Keep2ShareClient()
                .setAccessToken(Optional.ofNullable(token).orElseThrow(() -> new Keep2ShareAuthenticationException("Unable to find property : access_token")));
    }

    public static Keep2ShareClient fromResource(String fileName) {
        try {
            Map<String, String> properties = getPropertiesFromResource(fileName);

            validate(properties);

            return Keep2ShareClient.of(properties.get(ACCESS_TOKEN));
        } catch (Exception exception) {
            throw new Keep2ShareException(exception);
        }
    }

    public static Keep2ShareClient fromFile(File file) {

        try {
            Map<String, String> properties = getPropertiesFromFile(file);

            validate(properties);

            return Keep2ShareClient.of(properties.get(ACCESS_TOKEN));
        } catch (Exception exception) {
            throw new Keep2ShareException(exception);
        }
    }

    public static Map<String, String> getPropertiesFromFile(File file) throws IOException {
        return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8).stream()
                .filter(l -> !l.isEmpty())
                .collect(Collectors.
                        toMap(
                                l -> l.substring(0,l.indexOf('=')),
                                l -> l.substring(l.indexOf('=')+1)
                        )
                );
    }


    private static Map<String, String> getPropertiesFromResource(String fileName) throws IOException, URISyntaxException {
        ClassLoader classLoader = Keep2ShareClient.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);

        File file = new File(Optional.ofNullable(resource).orElseThrow(() -> new IllegalArgumentException("file not found! " + fileName)).toURI());
        return getPropertiesFromFile(file);
    }

    private static void validate(Map<String, String> properties) {

        String token = properties.get(ACCESS_TOKEN) != null && !properties.get(ACCESS_TOKEN).isEmpty() ? properties.get(ACCESS_TOKEN) : null;

        if(token != null) {
            return;
        }

        throw new Keep2ShareException("None of the properties can't be empty : access_token");
    }

    public Keep2ShareAvailableDomainsCommand listDomains() {
        return new Keep2ShareAvailableDomainsCommand(this.accessToken);
    }

    public Keep2ShareCreateFolderCommand createFolder() {
        return new Keep2ShareCreateFolderCommand(this.accessToken);
    }

    public Keep2ShareFileListCommand listFiles() {
        return new Keep2ShareFileListCommand(this.accessToken);
    }

    public Keep2ShareFilesInfoCommand filesInfo() {
        return new Keep2ShareFilesInfoCommand(this.accessToken);
    }

    public Keep2ShareFoldersListCommand listFolders() {
        return new Keep2ShareFoldersListCommand(this.accessToken);
    }

    public Keep2ShareGetBalanceCommand balance() {
        return new Keep2ShareGetBalanceCommand(this.accessToken);
    }

    public Keep2ShareUploadSingleCommand upload(File file) {
        return new Keep2ShareUploadSingleCommand(this.accessToken, file);
    }

    public Keep2ShareUploadMultipleCommand uploadMultiple(File folder) {
        return new Keep2ShareUploadMultipleCommand(this.accessToken, folder);
    }

    public Keep2ShareUploadMultipleCommand uploadMultiple(List<File> files) {
        return new Keep2ShareUploadMultipleCommand(this.accessToken, files);
    }

}
