package user_manage.service.reading_review.show_all_reviews.interface_adapter;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import org.junit.jupiter.api.Test;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsDataAccessInterface;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsOutputBoundary;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsOutputData;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowAllReviewsPresenterTest {

    private class MockShowAllReviewViewModel extends ShowAllReviewsViewModel {

        @Override
        public void firePropertyChanged() {
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        }
        public void setState(ShowAllReviewsState newState) {
            assertEquals(newState.getRating(), 5.0F);
            assertEquals(newState.getReviewList().get(0), "excellent book");
        }
    }

    private class MockViewManagerModel extends ViewManagerModel {
        public void setActiveView(String activeView) {
            assertEquals(activeView, "all reviews");
        }
    }

    @Test
    void testPrepareNoReviewView() {
        MockShowAllReviewViewModel mockShowAllReviewViewModel = new MockShowAllReviewViewModel();
        MockViewManagerModel mockViewManagerModel = new MockViewManagerModel();
        ShowAllReviewsPresenter showAllReviewsPresenter = new ShowAllReviewsPresenter(mockViewManagerModel, mockShowAllReviewViewModel);
        List<String> outputReviews = new ArrayList<>();
        outputReviews.add("excellent book");
        ShowAllReviewsOutputData showAllReviewsOutputData = new ShowAllReviewsOutputData(outputReviews, 5.0F);
        showAllReviewsPresenter.prepareSuccessView(showAllReviewsOutputData);
    }

    @Test
    void testPrepareSuccessView() {
    }
}