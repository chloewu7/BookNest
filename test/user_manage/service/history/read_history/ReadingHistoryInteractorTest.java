package user_manage.service.history.read_history;

import org.junit.jupiter.api.Test;

import user_manage.service.history.add_history.*;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class ReadingHistoryInteractorTest {

    private class HistoryDataAccessStub implements ReadingHistoryDataAccessInterface {

        @Override
        public void addHistoryToUser(String user, String bookName) {
        }

        @Override
        public List<String> getHistoryByUserId(String userId) {
            List<String> history = new ArrayList<>();
            history.add("book1");
            history.add("book2");
            return history;
        }
    }

    private class MockReadingHistoryPresenter implements ReadingHistoryOutputBoundary {

        @Override
        public void presentHistory(ReadingHistoryOutputData outputData) {
            assertEquals(outputData.getHistory().get(0), "book1");
            assertEquals(outputData.getHistory().get(1), "book2");
        }
    }

    @Test
    void testExecuteSuccess() {
        HistoryDataAccessStub historyDataAccessStub = new HistoryDataAccessStub();
        MockReadingHistoryPresenter mockReadingHistoryPresenter = new MockReadingHistoryPresenter();

        ReadingHistoryInteractor readingHistoryInteractor = new ReadingHistoryInteractor(mockReadingHistoryPresenter, historyDataAccessStub);

        ReadingHistoryInputData readingHistoryInputData = new ReadingHistoryInputData("user", "book");
        readingHistoryInteractor.execute(readingHistoryInputData);
    }
}


