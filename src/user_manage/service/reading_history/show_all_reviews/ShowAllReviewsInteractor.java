package user_manage.service.reading_history.show_all_reviews;

import java.util.List;

public class ShowAllReviewsInteractor implements ShowAllReviewsInputBoundary{
    final ShowAllReviewsDataAccessInterface showAllReviewsDataAccessObject;
    final ShowAllReviewsOutputBoundary showAllReviewsPresenter;


    public ShowAllReviewsInteractor(ShowAllReviewsDataAccessInterface showAllReviewsDataAccessObject,
                                    ShowAllReviewsOutputBoundary showAllReviewsPresenter){
        this.showAllReviewsDataAccessObject = showAllReviewsDataAccessObject;
        this.showAllReviewsPresenter = showAllReviewsPresenter;
    }
    //TODO: 修改execute，让DAO传review info as string出来。interactor再把这个string递交给Presenter
    @Override
    public void execute(ShowAllReviewsInputData showAllReviewsInputData) {
        String bookTitle = showAllReviewsInputData.getBookTitle();
        if(showAllReviewsDataAccessObject.review_exists(bookTitle)){
            List<String> reviewList = showAllReviewsDataAccessObject.retrieveAllReviews(bookTitle);
            float rating = showAllReviewsDataAccessObject.retrieveRating(bookTitle);
            ShowAllReviewsOutputData showAllReviewsOutputData = new ShowAllReviewsOutputData(reviewList, rating);
            showAllReviewsPresenter.prepareSuccessView(showAllReviewsOutputData);
        } else {
            showAllReviewsPresenter.prepareNoReviewView("Be the first to share your thoughts! " +
                    "There are currently no reviews for this book. Your review can help others " +
                    "discover and enjoy it. Click the 'Write a Review' button to get started.");
        }
    }
}
