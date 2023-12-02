package user_manage.service.reading_review.show_my_reviews.interface_adapter;

import java.util.ArrayList;
import java.util.List;

public class ShowMyReviewsState {
    private List<String> reviewList = new ArrayList<>();

    private String noReviewMessage = null;

    private String username = "";

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

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
