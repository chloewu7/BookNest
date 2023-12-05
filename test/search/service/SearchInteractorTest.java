package search.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.data_access.SearchDataAccessObject;
import search.entity.*;
import search.service.interface_adapter.SearchPresenter;
import search.service.interface_adapter.SearchState;
import search.service.interface_adapter.SearchViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchInteractorTest {

    private SearchInteractor searchInteractor;
    private TestSearchDataAccessObject searchDataAccessObject;
    private TestSearchOutputBoundary searchPresenter;
    private TestResponseFactory responseFactory;

    class TestSearchDataAccessObject implements SearchDataAccessInterface {
        public List searchByTitle(String title) {
            Book book1 = new CommonBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction",
                    "1234567890");
            if (title.equals(book1.getTitle())){
                return List.of(book1);
            }
            else {
                List empty = new ArrayList<>();
                return empty;
            }
        }

        public List searchByAuthor(String author) {
            Book book1 = new CommonBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction",
                    "1234567890");
            if (author.equals(book1.getAuthor())){
                return List.of(book1);
            }
            else {
                List empty = new ArrayList<>();
                return empty;
            }
        }

        public List searchByCategory(String category){
            Book book1 = new CommonBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction",
                    "1234567890");
            if (category.equals(book1.getCategory())){
                return List.of(book1);
            }
            else {
                List empty = new ArrayList<>();
                return empty;
            }
        }

        public List searchByISBN(String isbn){
            Book book1 = new CommonBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction",
                    "1234567890");
            if (isbn.equals(book1.getISBN())){
                return List.of(book1);
            }
            else {
                List empty = new ArrayList<>();
                return empty;
            }
        }

    }

    class TestSearchOutputBoundary implements SearchOutputBoundary {
        public String lastMethodCalled;
        public SearchOutputData lastOutputData;
        public String lastMessage;

        @Override
        public void prepareSuccessView(SearchOutputData outputData) {
            lastMethodCalled = "prepareSuccessView";
            lastOutputData = outputData;
        }

        @Override
        public void prepareNotFoundView(String message) {
            lastMethodCalled = "prepareNotFoundView";
            lastMessage = message;
        }

        @Override
        public void prepareFailView(String message) {
            lastMethodCalled = "prepareFailView";
            lastMessage = message;
        }
    }

    class TestResponseFactory implements ResponseFactory {
        @Override
        public Response create(List<Book> books) {
            return () -> books;
        }
    }

    @BeforeEach
    public void setUp() {
        searchDataAccessObject = new TestSearchDataAccessObject();
        searchPresenter = new TestSearchOutputBoundary();
        responseFactory = new TestResponseFactory();
        searchInteractor = new SearchInteractor(searchDataAccessObject, searchPresenter, responseFactory);
    }


    @Test
    void execute() {

    }

    @Test
    void testSuccessfulSearchTitle(){
        SearchInputData inputData = new SearchInputData("Title", "The Great Gatsby");

        searchInteractor.execute(inputData);

        assertEquals("prepareSuccessView", searchPresenter.lastMethodCalled);
    }

    @Test
    void testSuccessfulSearchAuthor(){
        SearchInputData inputData = new SearchInputData("Author", "F. Scott Fitzgerald");

        searchInteractor.execute(inputData);

        assertEquals("prepareSuccessView", searchPresenter.lastMethodCalled);
    }

    @Test
    void testSuccessfulSearchCategory(){
        SearchInputData inputData = new SearchInputData("Category", "Fiction");

        searchInteractor.execute(inputData);

        assertEquals("prepareSuccessView", searchPresenter.lastMethodCalled);
    }

    @Test
    void testSuccessfulSearchISBN(){
        SearchInputData inputData = new SearchInputData("ISBN", "1234567890");

        searchInteractor.execute(inputData);

        assertEquals("prepareSuccessView", searchPresenter.lastMethodCalled);
    }

    @Test
    public void testNoResultsFoundTitle() {
        SearchInputData inputData = new SearchInputData("Title", "new");

        searchInteractor.execute(inputData);

        assertEquals("prepareNotFoundView", searchPresenter.lastMethodCalled);
    }

    @Test
    public void testNoResultsFoundAuthor() {
        SearchInputData inputData = new SearchInputData("Author", "new");

        searchInteractor.execute(inputData);

        assertEquals("prepareNotFoundView", searchPresenter.lastMethodCalled);
    }

    @Test
    public void testNoResultsFoundCategory() {
        SearchInputData inputData = new SearchInputData("Category", "new");

        searchInteractor.execute(inputData);

        assertEquals("prepareNotFoundView", searchPresenter.lastMethodCalled);
    }

    @Test
    public void testNoResultsFoundISBN() {
        SearchInputData inputData = new SearchInputData("ISBN", "new");

        searchInteractor.execute(inputData);

        assertEquals("prepareNotFoundView", searchPresenter.lastMethodCalled);
    }

    @Test
    public void testSearchErrorTitle(){
        SearchInputData inputData = new SearchInputData("Title", "Throw Exception");
        searchDataAccessObject = new TestSearchDataAccessObject() {
            @Override
            public List<Book> searchByTitle(String title) {
                if ("Throw Exception".equals(title)) {
                    throw new RuntimeException("Search failed");
                }
                return super.searchByTitle(title);
            }
        };

        searchInteractor = new SearchInteractor(searchDataAccessObject, searchPresenter, responseFactory);

        searchInteractor.execute(inputData);

        assertEquals("prepareFailView", searchPresenter.lastMethodCalled);

    }

    @Test
    public void testSearchErrorAuthor(){
        SearchInputData inputData = new SearchInputData("Author", "Throw Exception");
        searchDataAccessObject = new TestSearchDataAccessObject() {
            @Override
            public List<Book> searchByAuthor(String author) {
                if ("Throw Exception".equals(author)) {
                    throw new RuntimeException("Search failed");
                }
                return super.searchByAuthor(author);
            }
        };

        searchInteractor = new SearchInteractor(searchDataAccessObject, searchPresenter, responseFactory);

        searchInteractor.execute(inputData);

        assertEquals("prepareFailView", searchPresenter.lastMethodCalled);

    }

    @Test
    public void testSearchErrorCategory(){
        SearchInputData inputData = new SearchInputData("Category", "Throw Exception");
        searchDataAccessObject = new TestSearchDataAccessObject() {
            @Override
            public List<Book> searchByCategory(String category) {
                if ("Throw Exception".equals(category)) {
                    throw new RuntimeException("Search failed");
                }
                return super.searchByCategory(category);
            }
        };

        searchInteractor = new SearchInteractor(searchDataAccessObject, searchPresenter, responseFactory);

        searchInteractor.execute(inputData);

        assertEquals("prepareFailView", searchPresenter.lastMethodCalled);

    }

    @Test
    public void testSearchErrorISBN(){
        SearchInputData inputData = new SearchInputData("ISBN", "Throw Exception");
        searchDataAccessObject = new TestSearchDataAccessObject() {
            @Override
            public List<Book> searchByISBN(String ISBN) {
                if ("Throw Exception".equals(ISBN)) {
                    throw new RuntimeException("Search failed");
                }
                return super.searchByISBN(ISBN);
            }
        };

        searchInteractor = new SearchInteractor(searchDataAccessObject, searchPresenter, responseFactory);

        searchInteractor.execute(inputData);

        assertEquals("prepareFailView", searchPresenter.lastMethodCalled);

    }



}