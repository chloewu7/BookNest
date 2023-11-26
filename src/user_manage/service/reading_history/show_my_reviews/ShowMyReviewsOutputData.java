package user_manage.service.reading_history.show_my_reviews;

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
