package com.ddu.bookfilter.utils;

import com.ddu.bookfilter.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class to manage files
 */
public class FileUtil {
    private final ObjectMapper objectMapper;

    public FileUtil() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public List<Book> loadBooksFromJson(String jsonFilePath) throws IOException {
        return objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<Book>>(){});
    }

} 