package org.agoncal.quarkus.panache.resouces;

import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.repository.ArtistRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class BookResource {

    @GET
    @Path("{id}")
    public Book findBookById(@PathParam("id") Long id) {
        return Book.findById(id);
    }

    @GET
    public List<Book> listALlBooks() {
        return Book.listAll();
    }


}
