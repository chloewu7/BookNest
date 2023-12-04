package user_manage.service.collection_management.show_all_lists.interface_adapter;

import org.junit.jupiter.api.Test;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInputBoundary;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInputData;





import static org.junit.jupiter.api.Assertions.*;

class ShowAllListsControllerTest {

    private class ShowAllListsInteractorMock implements ShowAllListsInputBoundary {

        @Override
        public void execute(ShowAllListsInputData showAllListsInputData) {
            assertEquals(showAllListsInputData.getUserName(), "Monica");
        }
    }

    @Test
    void testExecute() {
        ShowAllListsInputData showAllListsInputData = new ShowAllListsInputData("Monica");
        ShowAllListsControllerTest.ShowAllListsInteractorMock showAllListsInteractorMock =
                new ShowAllListsControllerTest.ShowAllListsInteractorMock();
        ShowAllListsController showAllListsController = new ShowAllListsController(showAllListsInteractorMock);
        showAllListsController.execute("Monica");
    }
}