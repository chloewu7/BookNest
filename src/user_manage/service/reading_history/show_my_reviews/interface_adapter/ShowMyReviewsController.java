package user_manage.service.reading_history.show_my_reviews.interface_adapter;

import user_manage.service.reading_history.show_my_reviews.ShowMyReviewsInputBoundary;

public class ShowMyReviewsController {
    final ShowMyReviewsInputBoundary showMyReviewsInteractor;

    public ShowMyReviewsController(ShowMyReviewsInputBoundary showMyReviewsInteractor) {
        this.showMyReviewsInteractor = showMyReviewsInteractor;
    }

    public void execute(){
        showMyReviewsInteractor.execute();
    }

}
