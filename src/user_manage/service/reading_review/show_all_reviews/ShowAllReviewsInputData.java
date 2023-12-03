package user_manage.service.reading_review.show_all_reviews;

public class ShowAllReviewsInputData {
    final private String bookTitle;

    public ShowAllReviewsInputData(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
