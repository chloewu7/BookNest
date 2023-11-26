package user_manage.service.reading_review.show_my_reviews;

public class ShowMyReviewsInputData {
    final private String username;

    public ShowMyReviewsInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
