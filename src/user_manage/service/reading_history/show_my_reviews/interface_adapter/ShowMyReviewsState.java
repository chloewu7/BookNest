package user_manage.service.reading_history.show_my_reviews.interface_adapter;

import java.util.ArrayList;
import java.util.List;

public class ShowMyReviewsState {
    private List<String> reviewList = new ArrayList<>();

    private String noReviewMessage = null;

    public ShowMyReviewsState() {
    }

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
