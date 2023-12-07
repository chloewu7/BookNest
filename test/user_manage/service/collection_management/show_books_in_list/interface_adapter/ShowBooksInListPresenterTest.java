package user_manage.service.collection_management.show_books_in_list.interface_adapter;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import search.entity.Book;
import user_manage.service.collection_management.show_books_in_list.ShowBooksInListOutputData;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsState;

import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ShowBooksInListPresenterTest {

    private static class FakeShowBooksInListViewModel extends ShowBooksInListViewModel {
        @Override
        public void firePropertyChanged() {
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        }

        public void setState(ShowBooksInListState newState) {
            assertEquals(newState.getUserName(), "");
            assertEquals(newState.getListName(), "");
        }
    }

    private static class FakeViewManagerModel extends ViewManagerModel {
        private String activeView;

        @Override
        public void setActiveView(String activeView) {
            this.activeView = activeView;
        }

        public String getActiveView() {
            return activeView;
        }
    }

    @Test
    void testPrepareSuccessView() {
        FakeShowBooksInListViewModel fakeViewModel = new FakeShowBooksInListViewModel();
        FakeViewManagerModel fakeViewManagerModel = new FakeViewManagerModel();
        ShowBooksInListPresenter presenter = new ShowBooksInListPresenter(fakeViewModel, fakeViewManagerModel);

        Map<String, String> books = new HashMap<>();
        String listName = "";
        ShowBooksInListOutputData outputData = new ShowBooksInListOutputData(listName, books);

        presenter.prepareSuccessView(outputData);

        // Verify that the view model is updated with the correct state
        assertEquals(books, fakeViewModel.getState().getBooks());
        assertEquals(listName, fakeViewModel.getState().getListName());

    }
}
