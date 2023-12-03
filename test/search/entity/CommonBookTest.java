package search.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonBookTest {

    private CommonBook book;

    @BeforeEach
    public void setUp(){
        book = new CommonBook("Effective Java", "Joshua Bloch", "Programming", "978-0134685991");
    }

    @Test
    void getTitle() {
        assertEquals("Effective Java", book.getTitle());
    }

    @Test
    void getAuthor() {
        assertTrue(book.getAuthor().contains("Joshua Bloch"));
    }

    @Test
    void getCategory() {
        assertTrue(book.getCategory().contains("Programming"));
    }

    @Test
    void getISBN() {
        assertEquals("978-0134685991", book.getISBN());
    }
}