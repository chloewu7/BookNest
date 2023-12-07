package user_manage.service.collection_management.show_all_lists.interface_adapter;

import org.junit.jupiter.api.Test;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInputBoundary;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInputData;





import static org.junit.jupiter.api.Assertions.*;

public class ShowAllListsControllerTest {
    public class FakeShowAllListsInteractor implements ShowAllListsInputBoundary {
        private ShowAllListsInputData inputData;

        @Override
        public void execute(ShowAllListsInputData inputData) {
            this.inputData = inputData;
        }

        @Override
        public void executeWhenSearch(ShowAllListsInputData inputData) {
            this.inputData = inputData;
        }

        public ShowAllListsInputData getInputData() {
            return inputData;
        }
    }

    @Test
    public void testExecute() {
        FakeShowAllListsInteractor fakeInteractor = new FakeShowAllListsInteractor();

        ShowAllListsController controller = new ShowAllListsController(fakeInteractor);

        String userName = "testUser";
        controller.execute(userName);

        ShowAllListsInputData inputData = fakeInteractor.getInputData();

        assertEquals(userName, inputData.getUserName());
    }

    @Test
    public void testExecuteWhenSearch() {

        FakeShowAllListsInteractor fakeInteractor = new FakeShowAllListsInteractor();

        ShowAllListsController controller = new ShowAllListsController(fakeInteractor);

        String userName = "testUser";
        controller.executeWhenSearch(userName);

        ShowAllListsInputData inputData = fakeInteractor.getInputData();

        assertEquals(userName, inputData.getUserName());
    }
}
