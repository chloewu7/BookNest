package user_manage.service.reading_review.show_my_reviews;

import org.junit.jupiter.api.Test;
import user_manage.service.reading_review.show_all_reviews.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowMyReviewsInteractorTest {

    private class NonReviewExistDataAccessStub implements ShowMyReviewsDataAccessInterface {

        @Override
        public boolean user_review_exists(String username) {
            return false;
        }

        @Override
        public List<String> retrieveAllMyReviews(String username) {
            return null;
        }
    }

    private class MockNoReviewsExistPresenter implements ShowMyReviewsOutputBoundary {

        @Override
        public void prepareSuccessView(ShowMyReviewsOutputData showMyReviewsOutputData) {
            fail("Not showing no review exists message when it suppose to tell the user there is no review exists");
        }

        @Override
        public void prepareNoReviewView(String error) {
            assertEquals("<html>You haven't posted any reviews yet. <br>Share your thoughts on the books you've read and <br>Help others discover great reads. <br>Click the 'Write a Review' button to get started.", error);
        }
    }

    private class ReviewExistDataAccessStub implements ShowMyReviewsDataAccessInterface{

        @Override
        public boolean user_review_exists(String username) {
            return true;
        }

        @Override
        public List<String> retrieveAllMyReviews(String username) {
            assertEquals("U", username);
            List<String> output = new ArrayList<>();
            output.add("good");
            return output;
        }
    }

    private class MockReviewsExistPresenter implements ShowMyReviewsOutputBoundary {
        @Override
        public void prepareSuccessView(ShowMyReviewsOutputData showMyReviewsOutputData) {
            List<String> outputReviews= new ArrayList<>();
            outputReviews = showMyReviewsOutputData.getReviewList();
            assertEquals("good",outputReviews.get(0) );
        }

        @Override
        public void prepareNoReviewView(String error) {
            fail("showing no review exists message when there are reviews exist for the book.");
        }
    }

    @Test
    void testNoReviewExistExecute() {
        NonReviewExistDataAccessStub nonReviewExistDataAccessStub = new NonReviewExistDataAccessStub();
        MockNoReviewsExistPresenter mockNoReviewsExistPresenter = new MockNoReviewsExistPresenter();

        ShowMyReviewsInteractor showMyReviewsInteractor = new ShowMyReviewsInteractor(nonReviewExistDataAccessStub,  mockNoReviewsExistPresenter);
        ShowMyReviewsInputData showMyReviewsInputData = new ShowMyReviewsInputData("None");
        showMyReviewsInteractor.execute(showMyReviewsInputData);
    }

    @Test
    void testReviewExistExecute() {
        ReviewExistDataAccessStub ReviewExistDataAccessStub = new ReviewExistDataAccessStub();
        MockReviewsExistPresenter mockReviewsExistPresenter = new MockReviewsExistPresenter();

        ShowMyReviewsInteractor showMyReviewsInteractor = new ShowMyReviewsInteractor(ReviewExistDataAccessStub,
                mockReviewsExistPresenter);
        ShowMyReviewsInputData showMyReviewsInputData = new ShowMyReviewsInputData("U");
        showMyReviewsInteractor.execute(showMyReviewsInputData);
    }
}