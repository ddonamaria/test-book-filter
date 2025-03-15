package com.ddu.bookfilter.service;

import com.ddu.bookfilter.model.Book;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * BookReportGenerator generates the report with results.
 */
public class BookReportGeneratorService {

    private final BookService bookService;

    public BookReportGeneratorService(String jsonFilePath) throws IOException {
        this.bookService = new BookService(jsonFilePath);
    }

    public void generateBooksByPagesAndTitleReport(int minPages, String titleContains) {
        List<Book> filteredBooks = bookService.filterBooksByPagesAndTitle(minPages, titleContains);

        System.out.println("1. Libros con mas de " + minPages + " paginas o que contienen '" + titleContains + "' en el titulo:");
        filteredBooks.forEach(book -> System.out.println("   - " + book.getTitle() + " (" + book.getPages() + " paginas)"));
    }

    public void generateBooksByAuthorReport(String authorName) {
        List<Book> authorBooks = bookService.getBooksByAuthor(authorName);

        System.out.println("2. Libros escritos por " + authorName + ":");
        authorBooks.forEach(book -> System.out.println("   - " + book.getTitle()));
    }

    public void generateSortedTitlesReport() {
        List<String> sortedTitles = bookService.getTitlesSortedAlphabetically();

        System.out.println("3a. Titulos ordenados alfabeticamente:");
        sortedTitles.forEach(title -> System.out.println("   - " + title));
    }

    public void generateBookCountByAuthorReport() {
        Map<String, Long> bookCountByAuthor = bookService.countBooksByAuthor();

        System.out.println("3b. Numero de libros por autor:");
        bookCountByAuthor.forEach((author, count) -> System.out.println("   - " + author + ": " + count + " libro/s"));
    }

    public void generateFormattedDatesReport() {
        List<Book> booksWithFormattedDates = bookService.getBooksWithFormattedDates();

        System.out.println("4. Libros con fechas formateadas (YYYY-MM-DD):");
        booksWithFormattedDates.forEach(book -> System.out.println("   - " + book.getTitle() + ": " + book.calculateFormattedDate()));
    }

    public void generatePageStatisticsReport() {
        double averagePages = bookService.calculateAveragePages();
        Optional<Book> bookWithMostPages = bookService.findBookWithMostPages();
        Optional<Book> bookWithFewestPages = bookService.findBookWithFewestPages();

        System.out.println("5. Estadistica de paginas:");
        System.out.println("   - Promedio de paginas: " + String.format("%.2f", averagePages));
        bookWithMostPages.ifPresentOrElse(
                book -> System.out.println("   - Maximo de paginas: " + book.getPages() + " paginas --> " + book.getTitle()),
                () -> System.out.println("   - Maximo de paginas: No se encontro ningun libro")
        );
        bookWithFewestPages.ifPresentOrElse(
                book -> System.out.println("   - Minimo de paginas: " + book.getPages() + " paginas --> " + book.getTitle()),
                () -> System.out.println("   - Minimo de paginas: No se encontro ningun libro")
        );

    }

    public void generateWordCountReport() {
        List<Book> booksWithWordCount = bookService.getBooksWithWordCount();

        System.out.println("6a. Libros con recuento de palabras:");
        booksWithWordCount.forEach(book -> System.out.println("   - " + book.getTitle() + ": " + book.getWordCount() + " palabras"));
    }

    public void generateGroupByAuthorReport() {
        Map<String, List<Book>> booksByAuthor = bookService.groupBooksByAuthor();

        System.out.println("6b. Libros agrupados por autor:");
        booksByAuthor.forEach((author, books) -> {
            System.out.println("    " + author);
            books.stream()
                    .map(Book::getTitle)
                    .sorted()
                    .forEach(title -> System.out.println("      - " + title));
        });
    }

    public void generateAuthorDuplicatedReport() {
        Optional<List<String>> duplicatedAuthors = bookService.findDuplicatedAuthors();

        System.out.println("7a. Autores duplicados:");
        duplicatedAuthors.ifPresentOrElse(
                authors -> authors.forEach(name -> System.out.println("   - " + name)),
                () -> System.out.println("   No hay autores duplicados")
        );
    }

    public void generateBookWithoutPublicationTimestampReport() {
        Optional<List<Book>> booksWithoutPublicationTimestamp = bookService.findBooksWithoutPublicationTimestamp();

        System.out.println("7b. Libros sin publicationTimestamp:");
        booksWithoutPublicationTimestamp.ifPresentOrElse(
                books -> books.forEach(book -> System.out.println("   - " + book.getTitle())),
                () -> System.out.println("   No hay libros sin sin publicationTimestamp")
        );
    }

    public void generateMostRecentBooksReport(int limit) {
        Optional<List<Book>> recentBooks = bookService.findMostRecentBooks(limit); // Mostramos los 3 más recientes

        System.out.println("8. Estos son los "+ limit + " libros mas recientes:");
        recentBooks.ifPresentOrElse(
                books -> books.forEach(book ->
                        System.out.println("   - " + book.getTitle() + " (" + book.calculateFormattedDate() + ")")),
                () -> System.out.println("   No hay libros con fecha de publicación")
        );
    }


}
