package user_manage.service.reading_review.show_my_reviews.interface_adapter;

import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsInputBoundary;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsInputData;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsOutputData;

public class ShowMyReviewsController {
    final ShowMyReviewsInputBoundary showMyReviewsInteractor;

    public ShowMyReviewsController(ShowMyReviewsInputBoundary showMyReviewsInteractor) {
        this.showMyReviewsInteractor = showMyReviewsInteractor;
    }

    public void execute(String username){
        ShowMyReviewsInputData showMyReviewsInputData = new ShowMyReviewsInputData(username);
        showMyReviewsInteractor.execute(showMyReviewsInputData);
    }

}
