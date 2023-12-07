package user_manage.service.collection_management.show_books_in_list;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ShowBooksInListInteractorTest {

    private static class FakeShowBooksInListDataAccessObject implements ShowBooksInListDataAccessInterface {
        @Override
        public Map<String, String> getBooksInlist(String userName, String listName) {
            // Simulate some fake data
            Map<String, String> books = new HashMap<>();
            books.put("Book1", "Author1");
            books.put("Book2", "Author2");
            return books;
        }
    }

    private static class FakeShowBooksInListPresenter implements ShowBooksInListOutputBoundary {
        private ShowBooksInListOutputData preparedOutputData;

        @Override
        public void prepareSuccessView(ShowBooksInListOutputData books) {
            this.preparedOutputData = books;
        }
    }

    @Test
    void testExecute() {
        FakeShowBooksInListDataAccessObject fakeDataAccessObject = new FakeShowBooksInListDataAccessObject();
        FakeShowBooksInListPresenter fakePresenter = new FakeShowBooksInListPresenter();
        ShowBooksInListInteractor interactor = new ShowBooksInListInteractor(fakeDataAccessObject, fakePresenter);

        String userName = "testUser";
        String listName = "ReadingList";
        ShowBooksInListInputData inputData = new ShowBooksInListInputData(userName, listName);

        interactor.execute(inputData);

        // Verify that the fake presenter received the correct data
        assertNotNull(fakePresenter.preparedOutputData);
        assertEquals(listName, fakePresenter.preparedOutputData.getListName());
        assertEquals(2, fakePresenter.preparedOutputData.getBooks().size());
        // Add more assertions based on your specific implementation
    }
}
