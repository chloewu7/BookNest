package user_manage.service.collection_management.add_book.interface_adapter;

import org.junit.jupiter.api.Test;
import search.entity.Book;
import search.entity.CommonBook;
import user_manage.service.collection_management.add_book.AddBookInputBoundary;
import user_manage.service.collection_management.add_book.AddBookInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBookControllerTest {

    private static class FakeAddBookInteractor implements AddBookInputBoundary {
        private AddBookInputData inputData;

        @Override
        public void execute(AddBookInputData addBookInputData) {
            this.inputData = addBookInputData;
            // Simulate some behavior or assertions if needed
        }

        public AddBookInputData getInputData() {
            return inputData;
        }
    }

    @Test
    void testExecute() {
        FakeAddBookInteractor fakeInteractor = new FakeAddBookInteractor();
        AddBookController controller = new AddBookController(fakeInteractor);

        String userName = "testUser";
        String listName = "ReadingList";
        Book book = new CommonBook("title", "author", "novel", "123");

        controller.execute(userName, listName, book);

        AddBookInputData inputData = fakeInteractor.getInputData();
        assertEquals(userName, inputData.getUserName());
        assertEquals(listName, inputData.getListName());
        assertEquals(book, inputData.getBook());
    }
}
