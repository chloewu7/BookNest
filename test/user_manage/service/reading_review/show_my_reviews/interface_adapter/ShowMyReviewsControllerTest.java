package user_manage.service.reading_review.show_my_reviews.interface_adapter;

import org.junit.jupiter.api.Test;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsInputBoundary;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsInputData;

import static org.junit.jupiter.api.Assertions.*;

class ShowMyReviewsControllerTest {

    private class ShowMyReviewsInteractorMock implements ShowMyReviewsInputBoundary {

        @Override
        public void execute(ShowMyReviewsInputData showMyReviewsInputData) {
            assertEquals(showMyReviewsInputData.getUsername(), "A");

        }
    }

    @Test
    void testExecute() {
        ShowMyReviewsInteractorMock showMyReviewsInteractorMock = new ShowMyReviewsInteractorMock();
        ShowMyReviewsController showMyReviewsController = new ShowMyReviewsController(showMyReviewsInteractorMock);
        showMyReviewsController.execute("A");
    }
}