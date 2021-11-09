package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.Book;
import com.explosivepomegranate.rest.api.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
