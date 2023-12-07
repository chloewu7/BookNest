package user_manage.service.history.add_history;

import org.junit.jupiter.api.Test;
import user_manage.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddingHistoryInteractorTest {


    private class HistoryDataAccessStub implements AddingHistoryDataAccessInterface {

        @Override
        public void addHistoryToUser(String user, String bookName) {
            assertEquals("user", user);
            assertEquals("book", bookName);
        }

        @Override
        public List<String> getHistoryByUserId(String userId) {
            return null;
        }

        @Override
        public User getUserByName(String userName) {
            return null;
        }
    }

    private class MockAddHistoryPresenter implements AddingHistoryOutputBoundary {
        @Override
        public void prepareSuccessView(AddingHistoryOutputData user) {
            assertEquals(user.getMessage(), "History added successfully for user user");
        }
    }

    @Test
    void testExecuteSuccess() {
        HistoryDataAccessStub historyDataAccessStub = new HistoryDataAccessStub();
        MockAddHistoryPresenter mockAddHistoryPresenter = new MockAddHistoryPresenter();

        AddingHistoryInteractor addingHistoryInteractor = new AddingHistoryInteractor(mockAddHistoryPresenter, historyDataAccessStub);

        AddingHistoryInputData addingHistoryInputData = new AddingHistoryInputData("user", "book");
        addingHistoryInteractor.execute(addingHistoryInputData);
    }
}