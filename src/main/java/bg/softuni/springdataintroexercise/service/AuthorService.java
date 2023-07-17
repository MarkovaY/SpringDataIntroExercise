package bg.softuni.springdataintroexercise.service;

import bg.softuni.springdataintroexercise.model.entity.Author;


import java.io.IOException;
import java.util.List;


public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderedByCountOFBooks();
}
