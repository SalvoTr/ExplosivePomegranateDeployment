package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Salvatore - Interface for CURD functionality
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoryByCategoryName(String categoryName);
}
