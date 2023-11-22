package user_manage.service.reading_review.write_reviews.interface_adapter;

import interface_adapter.ViewManagerModel;
import search.service.interface_adapter.SearchViewModel;
import user_manage.service.reading_review.write_reviews.WriteReviewsOutputBoundary;
import user_manage.service.reading_review.write_reviews.WriteReviewsOutputData;

public class WriteReviewsPresenter implements WriteReviewsOutputBoundary{
    private final WriteReviewsViewModel writeReviewsViewModel;
    private ViewManagerModel viewManagerModel;

    public WriteReviewsPresenter(ViewManagerModel viewManagerModel, WriteReviewsViewModel writeReviewsViewModel){
        this.viewManagerModel = viewManagerModel;
        this.writeReviewsViewModel = writeReviewsViewModel;
    }

    @Override
    public void prepareSuccessView(WriteReviewsOutputData writeReviewsOutputData) {
        WriteReviewsState writeReviewsState = writeReviewsViewModel.getState();
        writeReviewsState.setBookTitle(writeReviewsOutputData.getBookTitle());
        writeReviewsState.setCreationTime(writeReviewsOutputData.getCreationTime());

        this.writeReviewsViewModel.setState(writeReviewsState);

        writeReviewsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(writeReviewsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
