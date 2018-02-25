package com.wwc.sg.dw.resources;

import com.wwc.sg.dw.dao.BookDAO;
import com.wwc.sg.dw.representations.Book;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/catalog")
@Produces(MediaType.APPLICATION_JSON)
public class BookCatalogResource {

    /**
     * The DAO object to access books from db.
     */
    private BookDAO bookDAO;

    public BookCatalogResource(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GET
    @Path("/book")
    @UnitOfWork
    public Response findAll() {
        return Response.ok(bookDAO.findAll()).build();
    }

    @GET
    @Path("/book/{id}")
    @UnitOfWork
    public Response findById(@PathParam("id") LongParam id) {
        Optional<Book> book = bookDAO.findById(id.get());
        if (!book.isPresent()){
            return Response.ok("No book found.").build();
        } else {
            return Response.ok(book).build();
        }
    }

    /**
     * Query for books whose title contains the passed parameter as a substring.
     *
     * @param title query parameter
     * @return list of books
     */
    @GET
    @Path("/book/title/{title}")
    @UnitOfWork
    public Response findByName(@PathParam("title") Optional<String> title
    ) {
        if (!title.isPresent()) {
            return Response.ok("Please specify a title.").build();
        } else {
            List<Book> books = bookDAO.findByName(title.get());
            if (!books.isEmpty()) {
                return Response.ok(books).build();
            } else {
                return Response.ok("No books found.").build();
            }
        }
    }




}