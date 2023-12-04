package user_manage.service.reading_review.show_all_reviews;

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
        if(showAllReviewsDataAccessObject.book_review_exists(bookTitle)){
            List<String> reviewList = showAllReviewsDataAccessObject.retrieveAllBookReviews(bookTitle);
            float rating = showAllReviewsDataAccessObject.retrieveRating(bookTitle);
            ShowAllReviewsOutputData showAllReviewsOutputData = new ShowAllReviewsOutputData(reviewList, rating);
            showAllReviewsPresenter.prepareSuccessView(showAllReviewsOutputData);
        } else {
            showAllReviewsPresenter.prepareNoReviewView("<html>Be the first to share your thoughts!<br>" +
                    "There are currently no reviews for this book. <br>" +
                    "Your review can help others  discover and enjoy it.<br>" +
                    "Click the 'Write a Review' button to get started."
);
        }
    }
}
