package user_manage.service.reading_review.write_reviews.interface_adapter;

import org.junit.jupiter.api.Test;
import user_manage.service.reading_review.write_reviews.WriteReviewsInputBoundary;
import user_manage.service.reading_review.write_reviews.WriteReviewsInputData;

import static org.junit.jupiter.api.Assertions.*;

class WriteReviewsControllerTest {
    private class WriteReviewsInteractorMock implements WriteReviewsInputBoundary {
        @Override
        public void execute(WriteReviewsInputData writeReviewsInputData) {
            WriteReviewsInputData review = writeReviewsInputData;
            assertEquals("review", review.getReviewContent());
            assertEquals("reviewer", review.getReviewer());
            assertEquals("author", review.getAuthor());
            assertEquals("title", review.getReviewedBook());
            assertEquals(5, review.getRating());

        }
    }

    @Test
    void testExecute() {
        WriteReviewsInteractorMock writeReviewsInteractorMock = new WriteReviewsInteractorMock();
        WriteReviewsController writeReviewsController = new WriteReviewsController(writeReviewsInteractorMock);
        writeReviewsController.execute("title", "author", "reviewer", "review", 5);
    }
}