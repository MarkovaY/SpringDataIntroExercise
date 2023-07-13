package bg.softuni.springdataintroexercise.service.impl;

import bg.softuni.springdataintroexercise.repository.BookRepository;
import bg.softuni.springdataintroexercise.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void seedBooks() {

    }
}
