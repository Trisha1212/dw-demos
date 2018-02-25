package com.wwc.sg.dw.dao;

import com.wwc.sg.dw.representations.Book;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class BookDAO extends AbstractDAO<Book> {

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public BookDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Method returns all books stored in the database.
     *
     * @return list of all books stored in the database
     */
    public List<Book> findAll() {
        return list(namedQuery("Book.findAll"));
    }

    /**
     * Looks for books whose title contains the passed
     * parameter as a substring.
     *
     * @param query query parameter
     * @return list of books whose title contains the passed
     * parameter as a substring.
     */
    public List<Book> findByName(String query) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(query).append("%");
        return list(
                namedQuery("Book.findByTitle")
                        .setParameter("title", builder.toString())
        );
    }

    /**
     * Method looks for a book by id.
     *
     * @param id the id of an book we are looking for.
     * @return Optional containing the found record or an empty Optional
     * otherwise.
     */
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(get(id));
    }
}