package br.com.barbara.service;

import br.com.barbara.domain.model.Book;
import br.com.barbara.repository.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookService {

    @Inject
    private BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.listAll();
    }

    public Book getById(Long id) {
        Optional<Book> bookSearch = bookRepository.findByIdOptional(id);

        return bookSearch.get();
    }

    @Transactional
    public Book create(Book bookToCreate) {
        if( isBookAlreadyRegistered(bookToCreate) ) {
            throw new RuntimeException("book already registered");
        }

        bookRepository.persist(bookToCreate);
        return bookToCreate;
    }

    private boolean isBookAlreadyRegistered(Book bookToCreate) {
        return bookRepository.listAll()
                .stream()
                .anyMatch(book -> {
                    boolean hasSameTitle = book.getTitle().equalsIgnoreCase(bookToCreate.getTitle());
                    boolean hasSameAuthor = book.getAuthor().equalsIgnoreCase(bookToCreate.getAuthor());
                    return hasSameAuthor && hasSameTitle;
                });
    }
}
