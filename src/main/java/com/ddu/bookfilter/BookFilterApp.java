package com.ddu.bookfilter;

import com.ddu.bookfilter.service.BookReportGeneratorService;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.logging.Level;

@Log
public class BookFilterApp {
    public static void main(String[] args) {
        try {
            String jsonFilePath = "src/main/resources/books.json";
            BookReportGeneratorService reportGenerator = new BookReportGeneratorService(jsonFilePath);

            System.out.println("=== INICIO DEL PROCESAMIENTO DE LIBROS ===");

            // 1. Filtra los libros con más de 400 páginas y aquellos cuyo título contenga "Harry".
            reportGenerator.generateBooksByPagesAndTitleReport(400, "Harry");

            // 2. Obtén los libros escritos por "J.K. Rowling".
            reportGenerator.generateBooksByAuthorReport("J.K. Rowling");

            // 3a. Lista los títulos ordenados alfabéticamente.
            reportGenerator.generateSortedTitlesReport();

            // 3b. Cuenta cuántos libros ha escrito cada autor.
            reportGenerator.generateBookCountByAuthorReport();

            // 4. Convierte publicationTimestamp a formato AAAA-MM-DD.
            reportGenerator.generateFormattedDatesReport();

            // 5. Calcula el promedio de páginas y encuentra el libro con más y menos páginas.
            reportGenerator.generatePageStatisticsReport();

            // 6a. Añade un campo wordCount (250 palabras por página).
            reportGenerator.generateWordCountReport();

            // 6b. Agrupa los libros por autor.
            reportGenerator.generateGroupByAuthorReport();

            // 7a. Verifica si hay autores duplicados.
            reportGenerator.generateAuthorDuplicatedReport();

            // 7b. Encuentra los libros sin publicationTimestamp.
            reportGenerator.generateBookWithoutPublicationTimestampReport();

            // 8. Identifica los libros más recientes.
            reportGenerator.generateMostRecentBooksReport(2);

            System.out.println("=== PROCESAMIENTO COMPLETADO ===");

        } catch (IOException e) {
            log.log(Level.SEVERE, "Error al procesar los libros: {}" + e.getMessage(), e);
        }
    }
} 