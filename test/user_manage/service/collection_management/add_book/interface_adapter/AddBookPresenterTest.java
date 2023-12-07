package user_manage.service.collection_management.add_book.interface_adapter;

import org.junit.jupiter.api.Test;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsState;

import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBookPresenterTest {

    private static class MockAddBookViewModel extends AddBookViewModel {
        @Override
        public void firePropertyChanged() {
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        }
        public void setState(AddBookState newState) {
            assertEquals(newState.getUserName(), "");
            assertEquals(newState.getAddBookSuccess(), "Book added successfully");
            assertEquals(newState.getAddBookError(), "Error adding book");
        }
    }

    @Test
    void testPrepareSuccessView() {
        MockAddBookViewModel fakeViewModel = new MockAddBookViewModel();
        AddBookPresenter presenter = new AddBookPresenter(fakeViewModel);

        String successResponse = "Book added successfully";

        presenter.prepareSuccessView(successResponse);

        // Verify that the fake ViewModel was updated correctly
        assertEquals(successResponse, fakeViewModel.getState().getAddBookSuccess());
    }

    @Test
    void testPrepareFailView() {
        MockAddBookViewModel fakeViewModel = new MockAddBookViewModel();
        AddBookPresenter presenter = new AddBookPresenter(fakeViewModel);

        String errorResponse = "Error adding book";

        presenter.prepareFailView(errorResponse);

        // Verify that the fake ViewModel was updated correctly
        assertEquals(errorResponse, fakeViewModel.getState().getAddBookError());
    }
}