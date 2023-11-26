package user_manage.service.reading_history.show_my_reviews;

public interface ShowMyReviewsOutputBoundary {
    void prepareSuccessView(ShowMyReviewsOutputData showMyReviewsOutputData);

    void prepareNoReviewView(String error);
}
