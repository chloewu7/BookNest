package search.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.entity.Book;
import search.entity.CommonBook;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchOutputDataTest {

    private List<Book> expectedResponse;
    private boolean expectedUseCaseFailed;
    private SearchOutputData searchOutputData;

    @BeforeEach
    public void setUp(){

        Book book1 = new CommonBook("title1", "author1", "category1", "isbn1");

        Book book2 = new CommonBook("title2", "author2", "category2", "isbn2");

        expectedResponse = Arrays.asList(book1, book2);

        expectedUseCaseFailed = false;

        searchOutputData = new SearchOutputData(expectedResponse, expectedUseCaseFailed);
    }


    @Test
    void getResponse() {

        List<Book> actualResponse = searchOutputData.getResponse();

        assertEquals(expectedResponse, actualResponse, "The response should match the expected response");

    }

}