package user_manage.service.collection_management.show_books_in_list.interface_adapter;

import org.junit.jupiter.api.Test;
import user_manage.service.collection_management.show_books_in_list.ShowBooksInListInputBoundary;
import user_manage.service.collection_management.show_books_in_list.ShowBooksInListInputData;
import user_manage.service.collection_management.show_books_in_list.interface_adapter.ShowBooksInListController;

import static org.junit.jupiter.api.Assertions.*;

public class ShowBooksInListControllerTest {

    @Test
    public void testExecute() {
        // Create a spy for the ShowBooksInListInputBoundary
        ShowBooksInListInputBoundarySpy inputBoundarySpy = new ShowBooksInListInputBoundarySpy();

        // Create the controller with the spy input boundary
        ShowBooksInListController controller = new ShowBooksInListController(inputBoundarySpy);

        // Define input parameters
        String userName = "testUser";
        String listName = "testList";

        // Execute the controller
        controller.execute(userName, listName);

        // Verify that the execute method of the input boundary spy was called with the correct input data
        assertEquals(userName, inputBoundarySpy.getUserName());
        assertEquals(listName, inputBoundarySpy.getListName());
    }

    private static class ShowBooksInListInputBoundarySpy implements ShowBooksInListInputBoundary {
        private String userName;
        private String listName;

        @Override
        public void execute(ShowBooksInListInputData inputData) {
            // Capture the input data
            userName = inputData.getUserName();
            listName = inputData.getListName();
        }

        // Getter methods for verification
        public String getUserName() {
            return userName;
        }

        public String getListName() {
            return listName;
        }
    }
}
