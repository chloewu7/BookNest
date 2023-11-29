package user_manage.service.reading_review.show_my_reviews;

import java.util.List;

public class ShowMyReviewsOutputData {

    private final List<String> reviewList;

    public ShowMyReviewsOutputData(List<String> reviewList) {
        this.reviewList = reviewList;
    }

    public List<String> getReviewList() {
        return reviewList;
    }


}
