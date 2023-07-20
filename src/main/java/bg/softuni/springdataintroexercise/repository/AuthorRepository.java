package bg.softuni.springdataintroexercise.repository;

import bg.softuni.springdataintroexercise.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT a FROM Author AS a ORDER BY size(a.books) DESC")
    List<Author> findAllByBooksSizeDESC();
}
