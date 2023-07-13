package bg.softuni.springdataintroexercise.service.impl;


import bg.softuni.springdataintroexercise.model.entity.Author;
import bg.softuni.springdataintroexercise.repository.AuthorRepository;
import bg.softuni.springdataintroexercise.service.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_PATH = "src/main/resources/files/authors.txt";
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {

        if(authorRepository.count() > 0){
            return;
        }

        Files.readAllLines(Path.of(AUTHORS_PATH))
                .forEach(row -> {
                    String[] fullName = row.split("\\s+");
                    String firstName = fullName[0];
                    String lastName = fullName[1];

                    Author author = new Author(firstName, lastName);

                    authorRepository.save(author);
                });
    }
}
