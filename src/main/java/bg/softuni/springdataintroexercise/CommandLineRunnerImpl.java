package bg.softuni.springdataintroexercise;

import bg.softuni.springdataintroexercise.model.entity.Book;
import bg.softuni.springdataintroexercise.service.AuthorService;
import bg.softuni.springdataintroexercise.service.BookService;
import bg.softuni.springdataintroexercise.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;

    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
// Seed all the data.
        seedData();
// Print all book titles after year 2000.

//        printAllBooksAfterYear(2000);

// Get all authors with at least one book with a release date before 1990.
// Print their first name and last name.

//        printAllAuthorsNamesWithBookBeforeYear(1990);

// Get all authors, ordered by the number of their books (descending).
// Print their first name, last name and book count.

//        printAllAuthorsByNumberOfBooksAndBookCount();

//        Get all books from author George Powell, ordered by their release date (descending), then by book title (ascending).
//        Print the book's title, release date and copies.

        printAllBooksByAuthorName("George", "Powell");

    }

    private void printAllBooksByAuthorName(String firstName, String lastName) {
        bookService.findAllBooksByAuthorFirstAndLastName(firstName, lastName).forEach(System.out::println);
    }

    private void printAllAuthorsByNumberOfBooksAndBookCount() {
        authorService.getAllAuthorsOrderedByCountOFBooks().forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBookBeforeYear(int year) {
        bookService.findAllAuthorsWithBooksBeforeYear(year).forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService.findAllBooksAfterGivenYear(year).stream().map(Book::getTitle).forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
