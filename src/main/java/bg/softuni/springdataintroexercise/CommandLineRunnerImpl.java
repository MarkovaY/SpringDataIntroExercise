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
        seedData();

        printAllBooksAfter2000(2000);
    }

    private void printAllBooksAfter2000(int year) {
        bookService.findAllBooksAfterGivenYear(year).stream().map(Book::getTitle).forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
