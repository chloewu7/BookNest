package user_manage.service.reading_review.show_all_reviews.interface_adapter;

import java.util.ArrayList;
import java.util.List;

public class ShowAllReviewsState {
    private List<String> reviewList = new ArrayList<>();

    private String noReviewMessage = null;

    public ShowAllReviewsState(){}

    public List<String> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<String> reviewList) {
        this.reviewList = reviewList;
    }

    public void setNoReviewMessage(String noReviewMessage) {
        this.noReviewMessage = noReviewMessage;
    }

    public String getNoReviewMessage() {
        return noReviewMessage;
    }
}
