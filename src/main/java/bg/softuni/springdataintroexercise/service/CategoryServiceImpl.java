package bg.softuni.springdataintroexercise.service;

import bg.softuni.springdataintroexercise.model.entity.Category;
import bg.softuni.springdataintroexercise.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORIES_PATH = "src/main/resources/files/categories.txt";

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategory() throws IOException {

        if(categoryRepository.count() > 0){
            return;
        }
        Files.readAllLines(Path.of(CATEGORIES_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(categoryName -> {
                    Category category = new Category(categoryName);

                    categoryRepository.save(category);
                });
    }
}
