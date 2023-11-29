package user_manage.service.reading_review.write_reviews.interface_adapter;

public class WriteReviewsState {
    private String bookTitle = "";

    private String creationTime = "";
    public WriteReviewsState(){
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getCreationTime() {
        return creationTime;
    }
}
