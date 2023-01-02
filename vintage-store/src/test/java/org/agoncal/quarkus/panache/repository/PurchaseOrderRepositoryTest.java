package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.jpa.Customer;
import org.agoncal.quarkus.panache.model.*;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PurchaseOrderRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindAPurchaseOrder() throws SQLException {
        //Create an Artist
        Artist artist = new Artist("artist name", "artist bio");

        //Create a Publisher
        Publisher publisher = new Publisher("publisher name");

        //Create a Book
        Book book = new Book();
        book.title = "title of the book";
        book.description = "description of the book";
        book.nbOfPages = 500;
        book.language = Language.ENGLISH;
        book.price = new BigDecimal(10);
        book.isbn = "isbn";
        //Sets the relationship
        book.publisher = publisher;
        book.artist = artist;
        //Persists the book
        Book.persist(book);

        //Create a Customer
        Customer customer = new Customer("customer first name", "customer last name", "customer email");
        //Persists the customer
        customerRepository.persist(customer);

        //Create a OrderLine
        OrderLine orderLine = new OrderLine();
        orderLine.item = book;
        orderLine.quantity = 5;

        //Create a PurchaseOrder
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.customer = customer;
        purchaseOrder.addOrderLine(orderLine);
        //Persists a PurchaseOrder
        PurchaseOrder.persist(purchaseOrder);

    }
}