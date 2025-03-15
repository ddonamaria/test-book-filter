package com.ddu.bookfilter.service;

import com.ddu.bookfilter.model.Book;
import com.ddu.bookfilter.utils.FileUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * BookService filters and processes results.
 */
public class BookService {

    private final List<Book> books;

    private final FileUtil fileUtil;

    public BookService(String jsonFilePath) throws IOException {
        fileUtil = new FileUtil();
        books = fileUtil.loadBooksFromJson(jsonFilePath);

        books.forEach(book -> {
            book.calculateWordCount();
            book.calculateFormattedDate();
        });
    }

    public List<Book> filterBooksByPagesAndTitle(int minPages, String titleContains) {
        if (titleContains == null) {
            return Collections.emptyList();
        }
        
        return books.stream()
                .filter(book -> book.getPages() > minPages && 
                        (book.getTitle() != null && book.getTitle().contains(titleContains)))
                .toList();
    }

    public List<Book> getBooksByAuthor(String authorName) {
        if (authorName == null) {
            return Collections.emptyList();
        }
        
        return books.stream()
                .filter(book -> book.getAuthor() != null &&
                        authorName.equals(book.getAuthor().getFullName()))
                .toList();
    }

    public List<String> getTitlesSortedAlphabetically() {
        return books.stream()
                .map(Book::getTitle)
                .filter(Objects::nonNull)
                .sorted()
                .toList();
    }

    public Map<String, Long> countBooksByAuthor() {
        return books.stream()
                .filter(book -> book.getAuthor() != null)
                .collect(Collectors.groupingBy(
                        book -> book.getAuthor().getName(),
                        Collectors.counting()
                ));
    }

    public List<Book> getBooksWithFormattedDates() {
        return books.stream()
                .filter(book -> book.calculateFormattedDate() != null)
                .toList();
    }

    public double calculateAveragePages() {
        return books.stream()
                .mapToInt(Book::getPages)
                .average()
                .orElse(0);
    }

    public Optional<Book> findBookWithMostPages() {
        return books.stream()
                .max(Comparator.comparingInt(Book::getPages));
    }

    public Optional<Book> findBookWithFewestPages() {
        return books.stream()
                .min(Comparator.comparingInt(Book::getPages));
    }

    public List<Book> getBooksWithWordCount() {
        return books.stream()
                .filter(book -> book.getWordCount() != null)
                .toList();
    }

    public Map<String, List<Book>> groupBooksByAuthor() {
        return books.stream()
                .filter(book -> book.getAuthor() != null && book.getAuthor().getFullName() != null)
                .collect(Collectors.groupingBy(
                        book -> book.getAuthor().getFullName()
                ));
    }

    public Optional<List<String>> findDuplicatedAuthors() {
        List<String> duplicated = books.stream()
                .filter(book -> book.getAuthor() != null)
                .collect(Collectors.groupingBy(
                        book -> book.getAuthor().getFullName(),
                        Collectors.counting()
                )).entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();

        return duplicated.isEmpty() ? Optional.empty() : Optional.of(duplicated);
    }

    public Optional<List<Book>> findBooksWithoutPublicationTimestamp() {
        List<Book> booksWithoutDate = books.stream()
                .filter(book -> book.getPublicationTimestamp() == null || book.getPublicationTimestamp().isEmpty())
                .toList();

        return booksWithoutDate.isEmpty() ? Optional.empty() : Optional.of(booksWithoutDate);
    }

    public Optional<List<Book>> findMostRecentBooks(int limit) {
        List<Book> recentBooks = books.stream()
                .filter(book -> book.getPublicationTimestamp() != null && !book.getPublicationTimestamp().isEmpty())
                .sorted(Comparator.comparing(Book::getPublicationTimestamp).reversed())
                .limit(limit)
                .toList();

        return recentBooks.isEmpty() ? Optional.empty() : Optional.of(recentBooks);
    }

}