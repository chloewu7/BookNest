//package user_manage.service.collection_management.show_all_lists;
//
//import org.junit.jupiter.api.Test;
//import user_manage.service.reading_review.show_all_reviews.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ShowAllListsInteractorTest {
//
//    private class NonReviewExistDataAccessStub implements ShowAllReviewsDataAccessInterface {
//
//        @Override
//        public boolean book_review_exists(String bookTitle) {
//            return false;
//        }
//
//        @Override
//        public List<String> retrieveAllBookReviews(String bookTitle) {
//            return null;
//        }
//
//        @Override
//        public float retrieveRating(String bookTitle) {
//            return 0;
//        }
//    }
//
//    private class MockNoReviewsExistPresenter implements ShowAllReviewsOutputBoundary {
//        @Override
//        public void prepareSuccessView(ShowAllReviewsOutputData showAllReviewsOutputData) {
//            fail("Not showing no review exists message when it suppose to tell the user there is no review exists");
//        }
//
//        @Override
//        public void prepareNoReviewView(String error) {
//            assertEquals(error, "<html>Be the first to share your thoughts!<br>" +
//                    "There are currently no reviews for this book. <br>Your review can " +
//                    "help others  discover and enjoy it.<br>Click the 'Write a Review' " +
//                    "button to get started.");
//        }
//    }
//
//    private class ReviewExistDataAccessStub implements ShowAllReviewsDataAccessInterface{
//
//        @Override
//        public boolean book_review_exists(String bookTitle) {
//            return true;
//        }
//
//        @Override
//        public List<String> retrieveAllBookReviews(String bookTitle) {
//            List<String> reviews = new ArrayList<>();
//            reviews.add("excellent book");
//            reviews.add("good");
//            return reviews;
//        }
//
//        @Override
//        public float retrieveRating(String bookTitle) {
//            return 5.0F;
//        }
//    }
//
//    private class MockReviewsExistPresenter implements ShowAllReviewsOutputBoundary {
//        @Override
//        public void prepareSuccessView(ShowAllReviewsOutputData showAllReviewsOutputData) {
//            List<String> outputReviews= new ArrayList<>();
//            outputReviews = showAllReviewsOutputData.getReviewList();
//            assertEquals(outputReviews.get(0),"excellent book" );
//            assertEquals(outputReviews.get(1),"good" );
//            assertEquals(showAllReviewsOutputData.getRating(), 5.0F);
//        }
//
//        @Override
//        public void prepareNoReviewView(String error) {
//            fail("showing no review exists message when there are reviews exist for the book.");
//        }
//    }
//
//    @Test
//    void testNoReviewExistExecute() {
//        ShowAllReviewsInteractorTest.NonReviewExistDataAccessStub nonReviewExistDataAccessStub = new ShowAllReviewsInteractorTest.NonReviewExistDataAccessStub();
//        ShowAllReviewsInteractorTest.MockNoReviewsExistPresenter mockNoReviewsExistPresenter = new ShowAllReviewsInteractorTest.MockNoReviewsExistPresenter();
//
//        ShowAllReviewsInteractor showAllReviewsInteractor = new ShowAllReviewsInteractor(nonReviewExistDataAccessStub,
//                mockNoReviewsExistPresenter);
//        ShowAllReviewsInputData showAllReviewsInputData = new ShowAllReviewsInputData("ezpz4.0");
//        showAllReviewsInteractor.execute(showAllReviewsInputData);
//    }
//
//    @Test
//    void testReviewExistExecute() {
//        ShowAllReviewsInteractorTest.ReviewExistDataAccessStub ReviewExistDataAccessStub = new ShowAllReviewsInteractorTest.ReviewExistDataAccessStub();
//        ShowAllReviewsInteractorTest.MockReviewsExistPresenter mockReviewsExistPresenter = new ShowAllReviewsInteractorTest.MockReviewsExistPresenter();
//
//        ShowAllReviewsInteractor showAllReviewsInteractor = new ShowAllReviewsInteractor(ReviewExistDataAccessStub,
//                mockReviewsExistPresenter);
//        ShowAllReviewsInputData showAllReviewsInputData = new ShowAllReviewsInputData("ezpz4.0");
//        showAllReviewsInteractor.execute(showAllReviewsInputData);
//    }
//}