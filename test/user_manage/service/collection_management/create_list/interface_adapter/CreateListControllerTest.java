package user_manage.service.collection_management.create_list.interface_adapter;

import org.junit.jupiter.api.Test;
import user_manage.service.collection_management.create_list.CreateListInputBoundary;
import user_manage.service.collection_management.create_list.CreateListInputData;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInputBoundary;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInputData;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsController;


import static org.junit.jupiter.api.Assertions.*;

class CreateListControllerTest {

    private class CreateListInteractorMock implements CreateListInputBoundary {

        @Override
        public void execute( CreateListInputData createListInputData) {
            assertEquals(createListInputData.getUserName(), "Monica");
            assertEquals(createListInputData.getListName(), "Like");
        }
    }

    @Test
    void testExecute() {
        CreateListInputData createListInputData = new CreateListInputData("Monica", "Like");
        CreateListControllerTest.CreateListInteractorMock createListInteractorMock =
                new CreateListControllerTest.CreateListInteractorMock();
        CreateListController createListController = new CreateListController(createListInteractorMock);
        createListController.execute("Monica", "Like");
    }
}