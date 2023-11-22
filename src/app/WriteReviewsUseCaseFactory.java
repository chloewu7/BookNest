package app;

import interface_adapter.ViewManagerModel;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.entity.CommonReviewFactory;
import user_manage.entity.Review;
import user_manage.entity.ReviewFactory;
import user_manage.service.reading_review.write_reviews.*;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsController;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsPresenter;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsViewModel;
import view.WriteReviewsView;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;

public class WriteReviewsUseCaseFactory {

    private WriteReviewsUseCaseFactory(){}

    public static WriteReviewsView creat(ViewManagerModel viewManagerModel, WriteReviewsViewModel writeReviewsViewModel, FileReviewDataAccessObject reviewDataAccessObject) {
        try {
            WriteReviewsController writeReviewsController = createWriteReviewUseCase(viewManagerModel, writeReviewsViewModel, reviewDataAccessObject);
            return new WriteReviewsView(writeReviewsController, writeReviewsViewModel);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open review data file.");
        }
        return null;
    }

    private static WriteReviewsController createWriteReviewUseCase(ViewManagerModel viewManagerModel,
                                                                   WriteReviewsViewModel writeReviewsViewModel,
                                                                   WriteReviewsDataAccessInterface reviewsDataAccessObject) throws IOException {
        WriteReviewsOutputBoundary writeReviewsPresenter = new WriteReviewsPresenter(viewManagerModel, writeReviewsViewModel);

        ReviewFactory reviewFactory = new CommonReviewFactory();

        WriteReviewsInteractor writeReviewsInteractor = new WriteReviewsInteractor(
                reviewsDataAccessObject, reviewFactory, writeReviewsPresenter);

        return new WriteReviewsController(writeReviewsInteractor);
    }
}
