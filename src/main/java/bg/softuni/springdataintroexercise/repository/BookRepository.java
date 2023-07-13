package bg.softuni.springdataintroexercise.repository;

import bg.softuni.springdataintroexercise.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
