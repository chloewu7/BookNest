package user_manage.service.reading_review.write_reviews;

import org.junit.jupiter.api.Test;
import user_manage.entity.CommonReviewFactory;
import user_manage.entity.Review;
import user_manage.entity.ReviewFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WriteReviewsInteractorTest {

    private class WriteReviewsDataAccessStub implements WriteReviewsDataAccessInterface{
        @Override
        public void saveNewReview(Review newReview) {
            assertEquals("reviewer", newReview.getReviewer());
            assertEquals("bookTitle", newReview.getReviewedBook());
            assertEquals("author", newReview.getBookAuthor());
            assertEquals(5, newReview.getRating());
        }
    }

    private class MockWriteReviewsPresenter implements WriteReviewsOutputBoundary{

        @Override
        public void prepareSuccessView(WriteReviewsOutputData writeReviewsOutputData) {
            assertEquals(writeReviewsOutputData.getBookTitle(), "bookTitle");
        }
    }

    @Test
    void testExecuteSuccess() {
        WriteReviewsDataAccessStub writeReviewsDataAccessStub = new WriteReviewsDataAccessStub();
        MockWriteReviewsPresenter mockWriteReviewsPresenter = new MockWriteReviewsPresenter();
        CommonReviewFactory commonReviewFactory = new CommonReviewFactory();

        WriteReviewsInteractor writeReviewsInteractor = new WriteReviewsInteractor(writeReviewsDataAccessStub,
                commonReviewFactory, mockWriteReviewsPresenter);
        WriteReviewsInputData writeReviewsInputData = new WriteReviewsInputData("bookTitle", "author",
                "reviewer", "reviewContent", 5);
        writeReviewsInteractor.execute(writeReviewsInputData);
    }
}