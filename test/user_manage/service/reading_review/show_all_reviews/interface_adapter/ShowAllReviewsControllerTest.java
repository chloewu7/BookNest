package user_manage.service.reading_review.show_all_reviews.interface_adapter;

import org.junit.jupiter.api.Test;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsInputBoundary;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsInputData;

import static org.junit.jupiter.api.Assertions.*;

class ShowAllReviewsControllerTest {

    private class ShowAllReviewsInteractorMock implements ShowAllReviewsInputBoundary{

        @Override
        public void execute(ShowAllReviewsInputData showAllReviewsInputData) {
            assertEquals(showAllReviewsInputData.getBookTitle(), "ezpz4.0");
        }
    }

    @Test
    void testExecute() {
        ShowAllReviewsInputData showAllReviewsInputData = new ShowAllReviewsInputData("ezpz4.0");
        ShowAllReviewsInteractorMock showAllReviewsInteractorMock = new ShowAllReviewsInteractorMock();
        ShowAllReviewsController showAllReviewsController = new ShowAllReviewsController(showAllReviewsInteractorMock);
        showAllReviewsController.execute("ezpz4.0");
    }
}