package user_manage.service.reading_review.write_reviews;

import user_manage.entity.Review;
import user_manage.entity.ReviewFactory;

import java.time.LocalDateTime;

public class WriteReviewsInteractor implements WriteReviewsInputBoundary {
    final WriteReviewsDataAccessInterface writeReviewsDataAccessObject;

    final ReviewFactory reviewFactory;

    final WriteReviewsOutputBoundary writeReviewsPresenter;

    public WriteReviewsInteractor(WriteReviewsDataAccessInterface writeReviewsDataAccessObject,
                                  ReviewFactory reviewFactory, WriteReviewsOutputBoundary writeReviewsPresenter){
        this.writeReviewsDataAccessObject = writeReviewsDataAccessObject;
        this.reviewFactory = reviewFactory;
        this.writeReviewsPresenter = writeReviewsPresenter;
    }

    @Override
    public void execute(WriteReviewsInputData writeReviewsInputData) {
        String bookTitle = writeReviewsInputData.getReviewedBook();
        String bookAuthor = writeReviewsInputData.getAuthor();
        String reviewer = writeReviewsInputData.getReviewer();
        Integer rating = writeReviewsInputData.getRating();
        String reviewContent = writeReviewsInputData.getReviewContent();
        LocalDateTime current = LocalDateTime.now();
        Review newReview = reviewFactory.create(bookTitle, bookAuthor, reviewer, rating, reviewContent, current);
        writeReviewsDataAccessObject.saveNewReview(newReview);
        WriteReviewsOutputData writeReviewsOutputData = new WriteReviewsOutputData(bookTitle, current.toString());
        writeReviewsPresenter.prepareSuccessView(writeReviewsOutputData);
    }
}
