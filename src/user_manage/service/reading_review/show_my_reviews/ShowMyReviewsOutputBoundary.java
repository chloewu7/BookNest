package user_manage.service.reading_review.show_my_reviews;

public interface ShowMyReviewsOutputBoundary {
    void prepareSuccessView(ShowMyReviewsOutputData showMyReviewsOutputData);

    void prepareNoReviewView(String error);
}
