package search.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @BeforeEach
    public void setUp(){
        book = new CommonBook("Effective Java", "Joshua Bloch", "Programming",
                "978-0134685991");
    }

    @Test
    void getTitle() {
        assertEquals("Effective Java", book.getTitle());
    }

    @Test
    void getAuthor() {
        assertEquals("Joshua Bloch", book.getAuthor());
    }

    @Test
    void getCategory() {
        assertEquals("Programming", book.getCategory());
    }

    @Test
    void getISBN() {
        assertEquals("978-0134685991", book.getISBN());
    }
}