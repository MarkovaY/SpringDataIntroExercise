package bg.softuni.springdataintroexercise.repository;

import bg.softuni.springdataintroexercise.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
