package user_manage.service.reading_review.show_all_reviews.interface_adapter;


import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsInputBoundary;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsInputData;

public class ShowAllReviewsController {
    final ShowAllReviewsInputBoundary showAllReviewsInteractor;

    public ShowAllReviewsController(ShowAllReviewsInputBoundary showAllReviewsInteractor) {
        this.showAllReviewsInteractor = showAllReviewsInteractor;
    }

    public void execute(String bookTitle){
        ShowAllReviewsInputData showAllReviewsInputData = new ShowAllReviewsInputData(bookTitle);
        showAllReviewsInteractor.execute(showAllReviewsInputData);
    }
}
