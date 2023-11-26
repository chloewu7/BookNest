package user_manage.service.reading_history.show_all_reviews;

import java.util.List;

public interface ShowAllReviewsDataAccessInterface {
    boolean review_exists(String bookTitle);

    List<String> retrieveAllReviews(String bookTitle);

    float retrieveRating(String bookTitle);
}
