package bg.softuni.springdataintroexercise.service.impl;

import bg.softuni.springdataintroexercise.model.entity.*;
import bg.softuni.springdataintroexercise.repository.BookRepository;
import bg.softuni.springdataintroexercise.service.AuthorService;
import bg.softuni.springdataintroexercise.service.BookService;
import bg.softuni.springdataintroexercise.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_PATH = "src/main/resources/files/books.txt";
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {

        if(bookRepository.count() > 0){
            return;
        }

        Files.readAllLines(Path.of(BOOKS_PATH))
                .forEach(row -> {
                    String[] booksData = row.split("\\s+");

                    Book book = createBookFromBooksData(booksData);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterGivenYear(int year) {

        return bookRepository.findAllByReleaseDateAfter(LocalDate.of(year, 12,31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksBeforeYear(int year) {

        return bookRepository.findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(), book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastName(String firstName, String lastName) {
        return bookRepository.findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d", book.getTitle(), book.getReleaseDate(), book.getCopies()))
                .collect(Collectors.toList());
    }

    private Book createBookFromBooksData(String[] booksData) {

        EditionType editionType = EditionType.values()[Integer.parseInt(booksData[0])];
        LocalDate releaseDate = LocalDate.parse(booksData[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(booksData[2]);
        BigDecimal price = new BigDecimal(booksData[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(booksData[4])];
        String title = Arrays.stream(booksData).skip(5).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();

        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);
    }
}
