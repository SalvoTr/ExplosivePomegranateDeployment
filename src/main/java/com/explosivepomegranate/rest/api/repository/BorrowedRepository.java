package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.Borrowed;
import com.explosivepomegranate.rest.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedRepository extends JpaRepository<Borrowed, Integer> {

    /**
     * @author Sonja
     * Methode for returing user id
     */
    List<Borrowed> findByUser(User user);

    List<Borrowed> findById(int user_id);
    // https://www.netsurfingzone.com/jpa/how-to-write-custom-method-in-repository-in-spring-data-jpa/

}
