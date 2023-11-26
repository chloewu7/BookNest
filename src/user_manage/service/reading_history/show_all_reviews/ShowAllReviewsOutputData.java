package user_manage.service.reading_history.show_all_reviews;

import java.util.List;

public class ShowAllReviewsOutputData {

    private List<String> reviewList;
    private float rating;

    public ShowAllReviewsOutputData(List<String> reviewList, float rating) {
        this.reviewList = reviewList;
        this.rating = rating;
    }

    public List<String> getReviewList() {
        return reviewList;
    }

    public float getRating() {
        return rating;
    }
}
