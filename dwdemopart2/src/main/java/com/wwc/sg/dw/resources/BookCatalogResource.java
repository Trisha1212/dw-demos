package com.wwc.sg.dw.resources;

import com.wwc.sg.dw.representations.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/catalog")
@Produces(MediaType.APPLICATION_JSON)
public class BookCatalogResource {

    private static List<Book> books = new ArrayList<>();
    static{
        books.add(new Book("'Autumn'", "Ali Smith"));
        books.add(new Book("Exit West", "Mohsin Hamid"));
        books.add(new Book("Pachinko", "Min Jin Lee"));
    }

    public BookCatalogResource() {
    }

    @GET
    @Path("/book")
    public Response getBooks() {
        return Response.ok(books).build();
    }

    @GET
    @Path("/book/{id}")
    public Response getBooksById(@PathParam("id") int id) {
        try {
            Book book = books.get(id-1);
            return Response.ok(book).build();
        } catch (Exception e){
            return Response.ok("No book found.").build();
        }
    }
}