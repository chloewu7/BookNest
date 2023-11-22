package user_manage.service.reading_review.write_reviews.interface_adapter;

import search.entity.Book;
import user_manage.service.reading_review.write_reviews.WriteReviewsInputBoundary;
import user_manage.service.reading_review.write_reviews.WriteReviewsInputData;

public class WriteReviewsController {
    final WriteReviewsInputBoundary writeReviewsInputBoundary;

    public WriteReviewsController(WriteReviewsInputBoundary writeReviewsInputBoundary){
        this.writeReviewsInputBoundary = writeReviewsInputBoundary;
    }

    private void execute(Book reviewedBook, String reviewContent, Integer rating){
        WriteReviewsInputData writeReviewsInputData = new WriteReviewsInputData(reviewedBook, reviewContent, rating);
        writeReviewsInputBoundary.execute(writeReviewsInputData);
    }
}
