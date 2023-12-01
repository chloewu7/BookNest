package app;

import interface_adapter.ViewManagerModel;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsDataAccessInterface;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsInteractor;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsOutputBoundary;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsController;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsPresenter;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;
import view.ShowMyReviewsView;

import javax.swing.*;
import java.io.IOException;

public class ShowMyReviewsUseCaseFactory {

    private ShowMyReviewsUseCaseFactory(){}

    public static ShowMyReviewsView create(ViewManagerModel viewManagerModel, ShowMyReviewsViewModel showMyReviewsViewModel, FileReviewDataAccessObject reviewDataAccessObject) {
        try {
            ShowMyReviewsController showMyReviewsController = createShowReviewUseCase(viewManagerModel, showMyReviewsViewModel, reviewDataAccessObject);
            return new ShowMyReviewsView(showMyReviewsController, showMyReviewsViewModel);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open review data file.");
        }
        return null;
    }

    private static ShowMyReviewsController createShowReviewUseCase(ViewManagerModel viewManagerModel,
                                                                  ShowMyReviewsViewModel showMyReviewsViewModel,
                                                                   ShowMyReviewsDataAccessInterface reviewsDataAccessObject) throws IOException {
        ShowMyReviewsOutputBoundary showMyReviewsPresenter = new ShowMyReviewsPresenter(viewManagerModel, showMyReviewsViewModel);

        ShowMyReviewsInteractor showMyReviewsInteractor = new ShowMyReviewsInteractor(
                reviewsDataAccessObject, showMyReviewsPresenter);

        return new ShowMyReviewsController(showMyReviewsInteractor);
    }
}
