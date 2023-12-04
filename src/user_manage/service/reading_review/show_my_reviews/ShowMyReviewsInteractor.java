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
    public void execute(ShowMyReviewsInputData showMyReviewsInputData) {
        String username = showMyReviewsInputData.getUsername();
        System.out.println("interactor execute");
        if (! showMyReviewsDataAccessObject.user_review_exists(username)){
            System.out.println("interactor review not exists execute");
            showMyReviewsPresenter.prepareNoReviewView("<html>You haven't posted any reviews yet. <br>Share your " +
                    "thoughts on the books you've read and <br>Help others discover great reads. <br>Click the 'Write " +
                    "a Review' button to get started.");
        } else {
            System.out.println("interactor review exist execute");
            List<String> myReviews = showMyReviewsDataAccessObject.retrieveAllMyReviews(username);
            ShowMyReviewsOutputData showMyReviewsOutputData = new ShowMyReviewsOutputData(myReviews);
            showMyReviewsPresenter.prepareSuccessView(showMyReviewsOutputData);
        }
    }
}
