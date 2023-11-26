package user_manage.service.reading_history.show_all_reviews;

public class ShowAllReviewsInputData {
    final private String bookTitle;

    public ShowAllReviewsInputData(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    String getBookTitle() {
        return bookTitle;
    }
}
