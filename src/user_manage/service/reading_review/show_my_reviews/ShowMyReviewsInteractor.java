package user_manage.service.reading_review.show_my_reviews;


import java.util.List;

public class ShowMyReviewsInteractor implements ShowMyReviewsInputBoundary{
    final ShowMyReviewsDataAccessInterface showMyReviewsDataAccessObject;
    final ShowMyReviewsOutputBoundary showMyReviewsPresenter;

    public ShowMyReviewsInteractor(ShowMyReviewsDataAccessInterface showMyReviewsDataAccessObject,
                                    ShowMyReviewsOutputBoundary showMyReviewsPresenter){
        this.showMyReviewsDataAccessObject = showMyReviewsDataAccessObject;
        this.showMyReviewsPresenter = showMyReviewsPresenter;
    }
    @Override
    public void execute() {
        List<String> myReviews = showMyReviewsDataAccessObject.retrieveAllMyReviews();
        if (myReviews.isEmpty()){
            showMyReviewsPresenter.prepareNoReviewView("You haven't posted any reviews yet. " +
                    "Share your thoughts on the books you've read and help others discover great reads. " +
                    "Click the 'Write a Review' button to get started.");
        } else {
            ShowMyReviewsOutputData showMyReviewsOutputData = new ShowMyReviewsOutputData(myReviews);
            showMyReviewsPresenter.prepareSuccessView(showMyReviewsOutputData);
        }
    }
}
