package user_manage.service.reading_review.write_reviews.interface_adapter;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import user_manage.service.reading_review.write_reviews.WriteReviewsOutputData;

import java.beans.PropertyChangeListener;


import static org.junit.jupiter.api.Assertions.*;

class WriteReviewsPresenterTest {
    private class MockWriteReviewsViewModel extends WriteReviewsViewModel {

        @Override
        public void firePropertyChanged() {
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        }
        public void setState(WriteReviewsState newState) {
            assertEquals("now", newState.getCreationTime());
            assertEquals("title", newState.getBookTitle());
        }
    }

    private class MockViewManagerModel extends ViewManagerModel {
        public void setActiveView(String activeView) {
            assertEquals(activeView, "write reviews");
        }
    }

    @Test
    void testPrepareNoReviewView() {
        WriteReviewsPresenterTest.MockWriteReviewsViewModel mockWriteReviewsViewModel = new MockWriteReviewsViewModel();
        WriteReviewsPresenterTest.MockViewManagerModel mockViewManagerModel = new MockViewManagerModel();
        WriteReviewsPresenter writeReviewsPresenter = new WriteReviewsPresenter(mockViewManagerModel, mockWriteReviewsViewModel);
        WriteReviewsOutputData writeReviewsOutputData = new WriteReviewsOutputData("title", "now");
        writeReviewsPresenter.prepareSuccessView(writeReviewsOutputData);
    }

    @Test
    void testPrepareSuccessView() {
    }
}