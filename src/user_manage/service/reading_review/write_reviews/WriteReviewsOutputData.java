package user_manage.service.reading_review.write_reviews;

public class WriteReviewsOutputData {

    private final String bookTitle;
    private String creationTime;
    public WriteReviewsOutputData(String bookTitle, String creationTime) {
        this.bookTitle = bookTitle;
        this.creationTime = creationTime;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getCreationTime() {
        return creationTime;
    }
}
