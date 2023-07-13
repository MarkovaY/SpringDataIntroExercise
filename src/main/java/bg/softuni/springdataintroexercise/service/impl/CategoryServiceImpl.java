package bg.softuni.springdataintroexercise.service.impl;

import bg.softuni.springdataintroexercise.model.entity.Category;
import bg.softuni.springdataintroexercise.repository.CategoryRepository;
import bg.softuni.springdataintroexercise.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORIES_PATH = "src/main/resources/files/categories.txt";

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {

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

    @Override
    public Set<Category> getRandomCategories() {

        Set<Category> randomSet = new HashSet<>();

        int randomCount = ThreadLocalRandom.current().nextInt(1, 3);
        long categoryCount = categoryRepository.count() + 1;

        for (int index = 0; index < randomCount ; index++) {
            long randomCategoryId = ThreadLocalRandom.current().nextLong(1, categoryCount);
            Category category = categoryRepository.findById(randomCategoryId).orElse(null);
            if(randomSet.contains(category)){
                continue;
            } else {
                randomSet.add(category);
            }
        }

        return randomSet;
    }
}
