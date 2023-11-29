package user_manage.service.reading_review.show_all_reviews;

import java.util.List;

public interface ShowAllReviewsDataAccessInterface {
    boolean book_review_exists(String bookTitle);

    List<String> retrieveAllBookReviews(String bookTitle);

    float retrieveRating(String bookTitle);
}
