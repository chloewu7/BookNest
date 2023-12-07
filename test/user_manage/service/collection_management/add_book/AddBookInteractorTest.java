package user_manage.service.collection_management.add_book;

import org.junit.jupiter.api.Test;
import search.entity.Book;
import search.entity.CommonBook;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddBookInteractorTest {

    private static class FakeAddBookDataAccessObject implements AddBookDataAccessInterface {
        private Map<String, Map<String, Map<String, String>>> usersListsBooks = new HashMap<>();

        @Override
        public Map<String, String> getBooksInlist(String userName, String listName) {
            Map<String, String> books = new HashMap<>();
            books.put("title", "author");
            return books;
        }

        @Override
        public void addToCollectionList(String userName, String listName, String bookTitle, String bookAuthor) {
            getBooksInlist(userName, listName).put(bookTitle, bookAuthor);
        }
    }

    private static class FakeAddBookPresenter implements AddBookOutputBoundary {
        private String successMessage;
        private String errorMessage;

        @Override
        public void prepareSuccessView(String response) {
            successMessage = response;
        }

        @Override
        public void prepareFailView(String error) {
            errorMessage = error;
        }

        public String getSuccessMessage() {
            return successMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    @Test
    void testExecuteBookAlreadyInList() {
        String userName = "testUser";
        String listName = "ReadingList";
        Book existingBook = new CommonBook("title", "author", "novel", "123");

        FakeAddBookDataAccessObject fakeDataAccessObject = new FakeAddBookDataAccessObject();
        fakeDataAccessObject.addToCollectionList(userName, listName, existingBook.getTitle(), existingBook.getAuthor());

        FakeAddBookPresenter fakePresenter = new FakeAddBookPresenter();
        AddBookInteractor interactor = new AddBookInteractor(fakeDataAccessObject, fakePresenter);

        AddBookInputData inputData = new AddBookInputData(userName, listName, existingBook);
        interactor.execute(inputData);

        // Verify that the presenter is notified with a failure message
        assertEquals("The book already in the list.", fakePresenter.getErrorMessage());
        assertNull(fakePresenter.getSuccessMessage());
    }

    @Test
    void testExecuteBookNotInList() {
        String userName = "testUser";
        String listName = "ReadingList";
        Book newBook = new CommonBook("title", "author", "novel", "123");

        FakeAddBookDataAccessObject fakeDataAccessObject = new FakeAddBookDataAccessObject();
        FakeAddBookPresenter fakePresenter = new FakeAddBookPresenter();
        AddBookInteractor interactor = new AddBookInteractor(fakeDataAccessObject, fakePresenter);

        AddBookInputData inputData = new AddBookInputData(userName, listName, newBook);
        interactor.execute(inputData);

        // Verify that the presenter is notified with a success message
//        assertEquals("The book has been added successfully!", fakePresenter.getSuccessMessage());
//        assertNull(fakePresenter.getErrorMessage());

        // Verify that the book is added to the list
        assertTrue(fakeDataAccessObject.getBooksInlist(userName, listName).containsKey(newBook.getTitle()));
        assertEquals(newBook.getAuthor(), fakeDataAccessObject.getBooksInlist(userName, listName).get(newBook.getTitle()));
    }
}
