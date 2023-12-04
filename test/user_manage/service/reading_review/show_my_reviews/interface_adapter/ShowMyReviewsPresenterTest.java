package user_manage.service.reading_review.show_my_reviews.interface_adapter;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsOutputData;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowMyReviewsPresenterTest {
    private class MockShowMyReviewViewModel extends ShowMyReviewsViewModel {

        @Override
        public void firePropertyChanged() {
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        }
        public void setState(ShowMyReviewsState newState) {
            assertEquals(newState.getReviewList().get(0), "B");
        }
    }

    private class MockViewManagerModel extends ViewManagerModel {
        public void setActiveView(String activeView) {
            assertEquals(activeView, "show my reviews");
        }
    }

    private class MockViewManagerModel2 extends ViewManagerModel {
        public void setActiveView(String activeView) {
            assertEquals(activeView, "show my reviews");
        }
    }

    private class MockNoReviewViewModel extends ShowMyReviewsViewModel{
        @Override
        public void firePropertyChanged() {
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        }
        public void setState(ShowMyReviewsState newState) {
            assertEquals(newState.getNoReviewMessage(), "no review");
        }
    }

    @Test
    void testPrepareNoReviewView() {
        MockNoReviewViewModel mockNoReviewViewModel = new MockNoReviewViewModel();
        MockViewManagerModel mockViewManagerModel = new MockViewManagerModel();
        ShowMyReviewsPresenter showMyReviewsPresenter = new ShowMyReviewsPresenter(mockViewManagerModel, mockNoReviewViewModel);
        showMyReviewsPresenter.prepareNoReviewView("no review");
    }

    @Test
    void testPrepareSuccessView() {
        MockShowMyReviewViewModel mockShowMyReviewViewModel = new MockShowMyReviewViewModel();
        MockViewManagerModel2 mockViewManagerModel2 = new MockViewManagerModel2();
        ShowMyReviewsPresenter showMyReviewsPresenter = new ShowMyReviewsPresenter(mockViewManagerModel2, mockShowMyReviewViewModel);
        List<String> outputReviews = new ArrayList<>();
        outputReviews.add("B");
        ShowMyReviewsOutputData showMyReviewsOutputData = new ShowMyReviewsOutputData(outputReviews);
        showMyReviewsPresenter.prepareSuccessView(showMyReviewsOutputData);
    }
}