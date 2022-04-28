package com.backend.demo.error.exceptions;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> clazz, String... searchParams) {
        super(generateMessage(clazz.getSimpleName(), toMap(searchParams)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return entity + " was not found for parameters " + searchParams;
    }

    private static Map<String, String> toMap(String[] searchParams) {
        if (searchParams.length % 2 == 1)
            throw new IllegalArgumentException();
        return IntStream.range(0, searchParams.length - 1)
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> Map.of(searchParams[i], searchParams[i + 1]))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
