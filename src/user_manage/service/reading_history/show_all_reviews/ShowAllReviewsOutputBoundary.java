package user_manage.service.reading_history.show_all_reviews;

public interface ShowAllReviewsOutputBoundary {
    void prepareSuccessView(ShowAllReviewsOutputData showAllReviewsOutputData);

    void prepareNoReviewView(String error);
}
