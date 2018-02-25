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
        books.add(new Book("Coding with Java: Springboot", "Purnima"));
        books.add(new Book("Coding with Java: Dropwizard", "Marianne"));
        books.add(new Book("Test Driven Development", "Fay"));
    }

    public BookCatalogResource() {
    }

    @GET
    @Path("/book")
    public Response getWorkshops() {
        return Response.ok(books).build();
    }

    @GET
    @Path("/book/{id}")
    public Response getWorkshopById(@PathParam("id") int id) {
        Book book = books.get(id-1);
        if (book==null){
            return Response.ok("No workshop found.").build();
        } else {
            return Response.ok(book).build();
        }

    }
}