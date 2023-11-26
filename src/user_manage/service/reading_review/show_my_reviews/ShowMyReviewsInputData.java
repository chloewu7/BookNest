package user_manage.service.reading_review.show_my_reviews;

public class ShowMyReviewsInputData {
    final private String bookTitle;

    public ShowMyReviewsInputData(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    String getBookTitle() {
        return bookTitle;
    }
}
