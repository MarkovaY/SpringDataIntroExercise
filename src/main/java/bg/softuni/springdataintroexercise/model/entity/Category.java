package bg.softuni.springdataintroexercise.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.Column;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
