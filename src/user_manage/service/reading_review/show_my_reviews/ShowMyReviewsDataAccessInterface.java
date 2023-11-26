package user_manage.service.reading_review.show_my_reviews;



import java.util.List;

public interface ShowMyReviewsDataAccessInterface {
    boolean user_review_exists(String bookTitle);
    List<String> retrieveAllMyReviews();
}
