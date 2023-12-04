package user_manage.service.reading_review.show_my_reviews.interface_adapter;

import interface_adapter.ViewManagerModel;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsOutputBoundary;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsOutputData;

public class ShowMyReviewsPresenter implements ShowMyReviewsOutputBoundary {

    private final ShowMyReviewsViewModel showMyReviewsViewModel;
    private ViewManagerModel viewManagerModel;

    public ShowMyReviewsPresenter(ViewManagerModel viewManagerModel, ShowMyReviewsViewModel showMyReviewsViewModel){
        this.viewManagerModel = viewManagerModel;
        this.showMyReviewsViewModel = showMyReviewsViewModel;
    }


    @Override
    public void prepareSuccessView(ShowMyReviewsOutputData showMyReviewsOutputData) {
        System.out.println("prepareSuccessReviewView");
        ShowMyReviewsState showMyReviewsState = showMyReviewsViewModel.getState();
        showMyReviewsState.setReviewList(showMyReviewsOutputData.getReviewList());

        this.showMyReviewsViewModel.setState(showMyReviewsState);

        showMyReviewsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(showMyReviewsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareNoReviewView(String noReviewMessage) {
        System.out.println("prepareNoReviewView");
        ShowMyReviewsState showMyReviewsState = showMyReviewsViewModel.getState();
        showMyReviewsState.setNoReviewMessage(noReviewMessage);
        showMyReviewsViewModel.firePropertyChanged();
    }

}
