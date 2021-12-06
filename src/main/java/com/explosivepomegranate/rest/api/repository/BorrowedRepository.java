package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.Book;
import com.explosivepomegranate.rest.api.model.Borrowed;
import com.explosivepomegranate.rest.api.service.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;

public interface BorrowedRepository extends JpaRepository<Borrowed, Integer> {

    /**
     * @author Sonja
     * Methode for returing user id
     */
    findByUserID(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails;
    }
}
