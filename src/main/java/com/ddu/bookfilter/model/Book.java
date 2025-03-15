package com.ddu.bookfilter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.ddu.bookfilter.config.BookConfig.DATE_FORMAT_PATTERN;
import static com.ddu.bookfilter.config.BookConfig.WORDS_PER_PAGE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Integer id;
    private String title;
    private Author author;
    private Integer pages;
    private String publicationTimestamp;
    private String summary;

    @JsonIgnore
    private Integer wordCount;

    public void calculateWordCount() {
        this.wordCount = this.pages * WORDS_PER_PAGE;
    }

    public String calculateFormattedDate() {
        if (publicationTimestamp != null) {
            LocalDate date = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(Long.parseLong(publicationTimestamp)),
                ZoneId.systemDefault()
            ).toLocalDate();
            return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
        }
        return null;
    }
} 