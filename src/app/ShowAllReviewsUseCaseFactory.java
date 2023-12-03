package app;

import interface_adapter.ViewManagerModel;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsDataAccessInterface;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsInteractor;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsOutputBoundary;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsController;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsPresenter;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsViewModel;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsController;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsViewModel;
import view.ShowAllReviewsView;

import javax.swing.*;
import java.io.IOException;

public class ShowAllReviewsUseCaseFactory {
    private ShowAllReviewsUseCaseFactory(){}

    public static ShowAllReviewsView create(ViewManagerModel viewManagerModel, ShowAllReviewsViewModel showAllReviewsViewModel, WriteReviewsController writeReviewsController, WriteReviewsViewModel writeReviewsViewModel, FileReviewDataAccessObject reviewDataAccessObject) {
        try {
            ShowAllReviewsController showAllReviewsController = createShowAllReviewUseCase(viewManagerModel, showAllReviewsViewModel, reviewDataAccessObject);
            return new ShowAllReviewsView(viewManagerModel, showAllReviewsController, showAllReviewsViewModel, writeReviewsController, writeReviewsViewModel);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open review data file.");
        }
        return null;
    }

    private static ShowAllReviewsController createShowAllReviewUseCase(ViewManagerModel viewManagerModel,
                                                                   ShowAllReviewsViewModel showAllReviewsViewModel,
                                                                   ShowAllReviewsDataAccessInterface reviewsDataAccessObject) throws IOException {
        ShowAllReviewsOutputBoundary showAllReviewsPresenter = new ShowAllReviewsPresenter(viewManagerModel, showAllReviewsViewModel);

        ShowAllReviewsInteractor showAllReviewsInteractor = new ShowAllReviewsInteractor(
                reviewsDataAccessObject, showAllReviewsPresenter);

        return new ShowAllReviewsController(showAllReviewsInteractor);
    }
}
