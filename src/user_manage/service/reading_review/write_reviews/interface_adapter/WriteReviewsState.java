package user_manage.service.reading_review.write_reviews.interface_adapter;

public class WriteReviewsState {
    private String bookTitle = "";
    private String creationTime = "";
    private String reviewContent = "";
    private String username = "";
    private Integer rating = 0;
    private String author = "";

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

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewContent() {
        return reviewContent;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public Integer getRating() {
        return rating;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }
}
