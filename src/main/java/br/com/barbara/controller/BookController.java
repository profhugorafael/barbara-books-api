package br.com.barbara.controller;

import br.com.barbara.domain.model.Book;
import br.com.barbara.service.BookService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

// requisicao http = verbo/metodo + rota/recurso
// GET /books

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController {

    @Inject
    private BookService bookService;

    @GET
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        var book = bookService.getById(id);
        return Response.ok(book).build();
    }

    @POST
    @Transactional
    public Response create(Book book) {
        var bookCreated = bookService.create(book);
        return Response.status(Response.Status.CREATED).entity(bookCreated).build();
    }
}

