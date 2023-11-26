package user_manage.data_access;

import user_manage.entity.Review;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsDataAccessInterface;

import java.util.List;

public class BookReviewDAOFacade implements ShowAllReviewsDataAccessInterface {

    @Override
    public boolean review_exists(String bookTitle) {
        return false;
    }

    @Override
    public List<Review> retrieveAllReviews(String bookTitle) {
        return null;
    }

    @Override
    public float retrieveRating(String bookTitle) {
        return 0;
    }
}
