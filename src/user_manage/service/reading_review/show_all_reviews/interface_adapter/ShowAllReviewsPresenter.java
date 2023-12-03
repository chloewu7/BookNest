package user_manage.service.reading_review.show_all_reviews.interface_adapter;

import interface_adapter.ViewManagerModel;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsOutputBoundary;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsOutputData;

public class ShowAllReviewsPresenter implements ShowAllReviewsOutputBoundary {

    private final ShowAllReviewsViewModel showAllReviewsViewModel;
    private ViewManagerModel viewManagerModel;

    public ShowAllReviewsPresenter(ViewManagerModel viewManagerModel, ShowAllReviewsViewModel showAllReviewsViewModel){
        this.viewManagerModel = viewManagerModel;
        this.showAllReviewsViewModel = showAllReviewsViewModel;
    }

    @Override
    public void prepareNoReviewView(String noReviewMessage) {
        ShowAllReviewsState showAllReviewsState = showAllReviewsViewModel.getState();
        showAllReviewsState.setNoReviewMessage(noReviewMessage);
        showAllReviewsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("all reviews");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(ShowAllReviewsOutputData showAllReviewsOutputData) {
        ShowAllReviewsState showAllReviewsState = showAllReviewsViewModel.getState();
        showAllReviewsState.setReviewList(showAllReviewsOutputData.getReviewList());
        showAllReviewsState.setRating(showAllReviewsOutputData.getRating());

        this.showAllReviewsViewModel.setState(showAllReviewsState);

        showAllReviewsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(showAllReviewsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
