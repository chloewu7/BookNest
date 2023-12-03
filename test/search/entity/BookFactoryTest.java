package search.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookFactoryTest {

    @Test
    void createBook() {
        String title = "Effective Java";
        String author = "Joshua Bloch";
        String category = "Programming";
        String ISBN = "978-0134685991";

        BookFactory factory = new CommonBookFactory();

        Book book = factory.createBook(title, author, category, ISBN);

        assertNotNull(book, "The book should not be null");

        assertTrue(book instanceof CommonBook, "The book should be an instance of CommonBook");

        assertEquals(title, book.getTitle(), "The title should match");

        assertEquals(author, book.getAuthor(), "The author should match");

        assertEquals(category, book.getCategory(), "The category should match");

        assertEquals(ISBN, book.getISBN(), "The ISBN should match");
    }
}