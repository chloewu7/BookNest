package app;

import interface_adapter.ViewManagerModel;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsDataAccessInterface;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsInteractor;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsOutputBoundary;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsController;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsPresenter;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchViewModel;
import view.ShowMyReviewsView;
import view.UserCenter.UserCenterView;
import view.UserCenter.UserCenterViewModel;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCenterFactory {
    private UserCenterFactory(){}

    public static List<JPanel> create(ViewManagerModel viewManagerModel, UserCenterViewModel userCenterViewModel,
                                      ShowMyReviewsViewModel showMyReviewsViewModel, FileReviewDataAccessObject reviewDataAccessObject,
                                      SearchController searchController, SearchViewModel searchViewModel) {
        try {
            //TODO:创建showMyHistoryController和showMyCollectionController
            ShowMyReviewsController showMyReviewsController = createShowMyReviewUseCase(viewManagerModel, showMyReviewsViewModel, reviewDataAccessObject);
            //TODO：用刚才新建的的Controller和ViewModel创建一个新的userCenterView（加在参数里）
            List<JPanel> userManageViewList = new ArrayList<>();
            UserCenterView userCenterView =  new UserCenterView(viewManagerModel, userCenterViewModel, showMyReviewsController, showMyReviewsViewModel,
                    searchController, searchViewModel);
            userManageViewList.add(userCenterView);
            ShowMyReviewsView showMyReviewsView = new ShowMyReviewsView(viewManagerModel, showMyReviewsController, showMyReviewsViewModel);
            userManageViewList.add(showMyReviewsView);
            //TODO：新建showMyHistoryView和showMyCollectionView
            //TODO：把新建的View加到 userManageViewList
            return userManageViewList;
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open review data file.");
        }
        return null;
    }

    private static ShowMyReviewsController createShowMyReviewUseCase(ViewManagerModel viewManagerModel,
                                                                   ShowMyReviewsViewModel showMyReviewsViewModel,
                                                                   ShowMyReviewsDataAccessInterface reviewsDataAccessObject) throws IOException {
        ShowMyReviewsOutputBoundary showMyReviewsPresenter = new ShowMyReviewsPresenter(viewManagerModel, showMyReviewsViewModel);

        ShowMyReviewsInteractor showMyReviewsInteractor = new ShowMyReviewsInteractor(
                reviewsDataAccessObject, showMyReviewsPresenter);

        return new ShowMyReviewsController(showMyReviewsInteractor);
    }

    //TODO：创建Controller的Methods
}
