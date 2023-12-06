/*package user_manage.service.history.read_history;

import org.junit.jupiter.api.Test;
import user_manage.data_access.FileHistoryDataAccessObject;
import user_manage.entity.*;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryPresenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadingHistoryInteractorTest {

    private class ReadingHistoryInteractor implements ReadingHistoryDataAccessInterface{

        @Override
        public List<String> getHistoryByUserId(String userId) {
            return null;
        }

        @Override
        public void addHistoryToUser(User user, String bookName) {

        }

        @Override
        public User getUserByName(String userName) {
            return null;
        }
    }
    private class ReadingHistoryPresenter implements ReadingHistoryOutputBoundary{

        @Override
        public void presentHistory(ReadingHistoryOutputData outputData) {

            List<String> his = new ArrayList<>();
            his.add("mumu");
            his.add("ww");
            assertEquals(outputData, his);
        }

        @Override
        public void handleFailure(String errorMessage) {

        }
    }


    ReadingHistoryOutputBoundary outputBoundary;

    File file = new File("history.csv");

    HistoryFactory historyFactory = new CommonHisotryFactory();

    ReadingHistoryDataAccessInterface dao = new FileHistoryDataAccessObject(file, historyFactory);

    //ReadingHistoryInteractor interactor = new ReadingHistoryInteractor(outputBoundary,dao);

    ReadingHistoryInputData inputData = new ReadingHistoryInputData("123", "ww");


    //ReadingHistoryOutputData outputData = new ReadingHistoryOutputData();

    ReadingHistoryInteractorTest() throws IOException {
    }

    @Test
    void testExecute() throws IOException {
        ReadingHistoryInteractorTest readingHistoryDataAccessOBJ = new ReadingHistoryInteractorTest();
        ReadingHistoryPresenter presenterTest = new ReadingHistoryPresenter();

        ReadingHistoryInteractor interactor1 = new ReadingHistoryInteractor(presenterTest, readingHistoryDataAccessOBJ);
        ReadingHistoryInputData inputData1 = new ReadingHistoryInputData("123", "ww");
        interactor1.execute(inputData1);

    }
}

 */
