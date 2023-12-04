package user_manage.service.reading_review.show_all_reviews.interface_adapter;

import java.util.ArrayList;
import java.util.List;

public class ShowAllReviewsState {

    public float rating;

    public String bookTitle;

    public String username;
    private List<String> reviewList = new ArrayList<>();
    private String noReviewMessage = null;
    private String author;

    public void setAuthor(String author) {
        this.author = author;
    }
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

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public String getAuthor() {
        return author;
    }

}
