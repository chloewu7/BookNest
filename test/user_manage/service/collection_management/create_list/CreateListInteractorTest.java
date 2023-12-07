package user_manage.service.collection_management.create_list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreateListInteractorTest {

    private static class FakeCreateListDataAccessObject implements CreateListDataAccessInterface {
        private List<String> userLists = List.of("List1", "List2", "List3");

        @Override
        public List<String> getListsName(String userName) {
            return userLists;
        }

        @Override
        public void createCollectionList(String userName, String listName) {
            // Simulate creating a collection list
            userLists = new ArrayList<>(userLists);
            userLists.add(listName);
        }
    }

    private static class FakeCreateListPresenter implements CreateListOutputBoundary {
        private CreateListOutputData preparedOutputData;
        private String errorMessage;

        @Override
        public void prepareSuccessView(CreateListOutputData createListOutputData) {
            this.preparedOutputData = createListOutputData;
        }

        @Override
        public void prepareFailView(String error) {
            this.errorMessage = error;
        }

        public CreateListOutputData getPreparedOutputData() {
            return preparedOutputData;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    @Test
    void testExecuteListAlreadyExists() {
        String userName = "testUser";
        String existingListName = "List1";

        FakeCreateListDataAccessObject fakeDataAccessObject = new FakeCreateListDataAccessObject();
        FakeCreateListPresenter fakePresenter = new FakeCreateListPresenter();
        CreateListInteractor interactor = new CreateListInteractor(fakeDataAccessObject, fakePresenter);

        CreateListInputData inputData = new CreateListInputData(userName, existingListName);
        interactor.execute(inputData);

        // Verify that the presenter is notified with a failure message
        assertEquals("Collection list already exist!", fakePresenter.getErrorMessage());
        assertNull(fakePresenter.getPreparedOutputData());
    }

    @Test
    void testExecuteListNotExists() {
        String userName = "testUser";
        String newListName = "NewList";

        FakeCreateListDataAccessObject fakeDataAccessObject = new FakeCreateListDataAccessObject();
        FakeCreateListPresenter fakePresenter = new FakeCreateListPresenter();
        CreateListInteractor interactor = new CreateListInteractor(fakeDataAccessObject, fakePresenter);

        CreateListInputData inputData = new CreateListInputData(userName, newListName);
        interactor.execute(inputData);

        // Verify that the presenter is notified with a success message and the updated list names
        assertNull(fakePresenter.getErrorMessage());
        assertEquals(List.of("List1", "List2", "List3", "NewList"), fakePresenter.getPreparedOutputData().getListNames());
    }
}
