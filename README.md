# 📚 Book Filter Application

## Descrición del proyecto
Aplicación desarrollada con Java 21 que filtra y procesa datos de libros desde un archivo JSON, generando los siguientes reportes:

    1. Filtra los libros con más de 400 páginas y aquellos cuyo título contenga "Harry".
    
    2. Obtén los libros escritos por "J.K. Rowling".
    
    3a. Lista los títulos ordenados alfabéticamente.
    
    3b. Cuenta cuántos libros ha escrito cada autor.
    
    4. Convierte publicationTimestamp a formato AAAA-MM-DD.
    
    5. Calcula el promedio de páginas y encuentra el libro con más y menos páginas.
    
    6a. Añade un campo wordCount (250 palabras por página).
    
    6b. Agrupa los libros por autor.
    
    7a. Verifica si hay autores duplicados.
    
    7b. Encuentra los libros sin publicationTimestamp.
    
    8. Identifica los libros más recientes.

Cada reporte se muestra en la consola con un formato estructurado, facilitando la lectura de la información.

## Tecnologías Utilizadas

- **Java 21**: Lenguaje de programación.
- **Maven 3.9.6**: Gestión de dependencias y construcción del proyecto.
- **Jackson**: Procesamiento de JSON.
- **Lombok**: Reducción de código mediante anotaciones.

## Clonado y ejecución de la aplicación

1. Clonar el repositorio:
```bash
git clone https://github.com/ddonamaria/test-book-filter.git
```

2. Acceder a la raíz del proyecto:
```bash
cd test-book-filter
```

3. Compilar el proyecto:
```bash
.\mvnw.cmd clean package
```

4. Ejecutar la aplicación:
```bash
java -jar target/book-filter-1.0.0.jar
```

La aplicación procesará el archivo `books.json` ubicado en `src/main/resources/` y mostrará los resultados en la consola.

## Estructura del Proyecto
```text
test-book-filter/
├── pom.xml          # Archivo de configuración de Maven
├── README.md        # Documentación del proyecto
├── .gitignore       # Archivos a ignorar por Git
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ddu/
│   │   │           └── bookfilter/
│   │   │               ├── BookFilterApp.java                   # Clase principal con método main
│   │   │               ├── config/
│   │   │               │   └── BookConfig.java                  # Configuraciones globales
│   │   │               ├── model/
│   │   │               │   ├── Author.java                      # Modelo de autore
│   │   │               │   └── Book.java                        # Modelo de libro
│   │   │               ├── service/
│   │   │               │   ├── BookService.java                 # Lógica de filtrado y procesamiento
│   │   │               │   └── BookReportGeneratorService.java  # Generación de reportes
│   │   │               └── utils/
│   │   │                   └── FileUtil.java                    # Utilidades para manejo de archivos
│   │   └── resources/
│   │       └── books.json                                       # Datos de entrada en formato JSON
```
