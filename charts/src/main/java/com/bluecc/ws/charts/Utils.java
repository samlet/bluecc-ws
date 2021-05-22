package com.bluecc.ws.charts;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static List<String> getStatements(String fileName) throws Exception {
        Path path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        return Arrays.stream(contents.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public static String todaysTimestamp() {
        return DateTimeFormatter.ofPattern("yyyyMMdd").withZone(ZoneOffset.UTC).format(Instant.now());
    }
}
