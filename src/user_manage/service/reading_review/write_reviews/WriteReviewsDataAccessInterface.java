package user_manage.service.reading_review.write_reviews;

import search.entity.Book;
import user_manage.entity.Review;

public interface WriteReviewsDataAccessInterface {

    void saveNewReview(Review newReview);
}
