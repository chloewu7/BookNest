package user_manage.service.collection_management.show_all_lists.interface_adapter;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import user_manage.service.collection_management.show_all_lists.ShowAllListsOutputBoundary;
import user_manage.service.collection_management.show_all_lists.ShowAllListsOutputData;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsOutputData;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsPresenter;

import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsState;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsViewModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowAllListsPresenterTest {
    private class MockShowAllListsViewModel extends ShowAllListsViewModel {

        @Override
        public void firePropertyChanged() {
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        }
        public void setState(ShowAllListsState newState) {
            assertEquals(newState.getUserName(), "");
            assertEquals(newState.getListsName().get(0), "Sleep");
            assertEquals(newState.getListsName().get(1), "reading");
        }
    }

    private class MockViewManagerModel extends ViewManagerModel {
        public void setActiveView(String activeView) {
            assertEquals(activeView, "all collection lists");
        }
    }

    @Test
    void testPrepareSuccessView() {
        ShowAllListsPresenterTest.MockShowAllListsViewModel mockShowAllListsViewModel =
                new ShowAllListsPresenterTest.MockShowAllListsViewModel();
        ShowAllListsPresenterTest.MockViewManagerModel mockViewManagerModel =
                new ShowAllListsPresenterTest.MockViewManagerModel();
        ShowAllListsPresenter showAllListsPresenter = new ShowAllListsPresenter(mockShowAllListsViewModel,mockViewManagerModel);
        List<String> listsName = new ArrayList<>();
        listsName.add("Sleep");
        listsName.add("reading");
        ShowAllListsOutputData showAllListsOutputData = new ShowAllListsOutputData(listsName);
        showAllListsPresenter.prepareSuccessView(showAllListsOutputData);
    }
}