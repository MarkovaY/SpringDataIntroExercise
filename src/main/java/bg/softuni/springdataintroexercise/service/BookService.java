package bg.softuni.springdataintroexercise.service;

import bg.softuni.springdataintroexercise.model.entity.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterGivenYear(int year);
}
