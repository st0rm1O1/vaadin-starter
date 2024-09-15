package com.github.st0rm1O1.backend.repository;

import com.github.st0rm1O1.backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
