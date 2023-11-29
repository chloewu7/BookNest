package user_manage.service.reading_review.write_reviews.interface_adapter;

import user_manage.service.reading_review.write_reviews.WriteReviewsInputBoundary;
import user_manage.service.reading_review.write_reviews.WriteReviewsInputData;

public class WriteReviewsController {
    final WriteReviewsInputBoundary writeReviewsInputBoundary;

    public WriteReviewsController(WriteReviewsInputBoundary writeReviewsInputBoundary){
        this.writeReviewsInputBoundary = writeReviewsInputBoundary;
    }

    private void execute(String reviewedBook, String author, String reviewer, String reviewContent, Integer rating){
        WriteReviewsInputData writeReviewsInputData = new WriteReviewsInputData(reviewedBook, author, reviewer, reviewContent, rating);
        writeReviewsInputBoundary.execute(writeReviewsInputData);
    }
}
