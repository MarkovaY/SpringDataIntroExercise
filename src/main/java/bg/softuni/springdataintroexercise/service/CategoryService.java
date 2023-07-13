package bg.softuni.springdataintroexercise.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CategoryService {
    public void seedCategory() throws IOException;
}
