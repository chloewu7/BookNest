package user_manage.service.collection_management.show_all_lists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowAllListsInteractorTest {

    private static class FakeShowAllListsDataAccess implements ShowAllListsDataAccessInterface {
        @Override
        public List<String> getListsName(String userName) {
            // Simulate some fake data
            return Arrays.asList("List1", "List2", "List3");
        }
    }

    private static class FakeShowAllListsPresenter implements ShowAllListsOutputBoundary {
        private ShowAllListsOutputData preparedOutputData;

        @Override
        public void prepareSuccessView(ShowAllListsOutputData allListsName) {
            this.preparedOutputData = allListsName;
        }

        @Override
        public void prepareShowAllLists(ShowAllListsOutputData allListsName) {
            // Not used in this example
        }
    }

    @Test
    void testExecute() {
        FakeShowAllListsDataAccess fakeDataAccess = new FakeShowAllListsDataAccess();
        FakeShowAllListsPresenter fakePresenter = new FakeShowAllListsPresenter();
        ShowAllListsInteractor interactor = new ShowAllListsInteractor(fakeDataAccess, fakePresenter);

        String userName = "testUser";
        ShowAllListsInputData inputData = new ShowAllListsInputData(userName);

        interactor.execute(inputData);

        // Verify that the fake presenter received the correct data
        assertEquals(Arrays.asList("List1", "List2", "List3"), fakePresenter.preparedOutputData.getAllLists());
    }

    @Test
    void testExecuteWhenSearch() {
        FakeShowAllListsDataAccess fakeDataAccess = new FakeShowAllListsDataAccess();
        FakeShowAllListsPresenter fakePresenter = new FakeShowAllListsPresenter();
        ShowAllListsInteractor interactor = new ShowAllListsInteractor(fakeDataAccess, fakePresenter);

        String userName = "testUser";
        ShowAllListsInputData inputData = new ShowAllListsInputData(userName);

        interactor.executeWhenSearch(inputData);

        // Verify that the fake presenter received the correct data
        assertEquals(Arrays.asList("List1", "List2", "List3"), fakePresenter.preparedOutputData.getAllLists());
    }
}
