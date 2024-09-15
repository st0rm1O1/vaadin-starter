package com.github.st0rm1O1.backend.service;

import com.github.st0rm1O1.backend.entity.Book;
import com.github.st0rm1O1.backend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Stream<Book> findAll(PageRequest request) {
        return bookRepository.findAll(request).stream();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteAll(Set<Book> books) {
        bookRepository.deleteAll(books);
    }
}
