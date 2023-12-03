package app;

import interface_adapter.ViewManagerModel;
import search.data_access.SearchDataAccessObject;
import search.entity.NewResponseFactory;
import search.entity.ResponseFactory;
import search.service.SearchDataAccessInterface;
import search.service.SearchInputData;
import search.service.SearchInteractor;
import search.service.SearchOutputBoundary;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchPresenter;
import search.service.interface_adapter.SearchViewModel;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.entity.CommonReviewFactory;
import user_manage.entity.ReviewFactory;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsDataAccessInterface;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsInteractor;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsOutputBoundary;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsController;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsPresenter;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsViewModel;
import user_manage.service.reading_review.write_reviews.WriteReviewsDataAccessInterface;
import user_manage.service.reading_review.write_reviews.WriteReviewsInteractor;
import user_manage.service.reading_review.write_reviews.WriteReviewsOutputBoundary;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsController;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsPresenter;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsViewModel;
import view.SearchView;
import view.ShowAllReviewsView;
import view.WriteReviewsView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchUseCaseFactory {

    private SearchUseCaseFactory(){}


    public static SearchController createSearchController(SearchViewModel searchViewModel) {
        SearchDataAccessInterface searchDAO = new SearchDataAccessObject();
        SearchOutputBoundary searchPresenter = new SearchPresenter(searchViewModel);
        ResponseFactory responseFactory = new NewResponseFactory();
        SearchInteractor searchInteractor = new SearchInteractor(searchDAO, searchPresenter, responseFactory);
        SearchController searchController = new SearchController(searchInteractor);

        return searchController;
    }

    public static List<JPanel> create(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel,
                                      ShowAllReviewsViewModel showAllReviewsViewModel,
                                      WriteReviewsViewModel writeReviewsViewModel,
                                      FileReviewDataAccessObject reviewDataAccessObject){
        SearchController searchController = createSearchController(searchViewModel);
        try {
            List<JPanel> searchViewList = new ArrayList<>();
            WriteReviewsController writeReviewsController = createWriteReviewUseCase(viewManagerModel,
                    writeReviewsViewModel, reviewDataAccessObject);
            WriteReviewsView writeReviewsView = new WriteReviewsView(viewManagerModel,
                    writeReviewsController, writeReviewsViewModel);
            ShowAllReviewsController showAllReviewsController = createShowAllReviewUseCase(viewManagerModel,
                    showAllReviewsViewModel, reviewDataAccessObject);
            ShowAllReviewsView showAllReviewsView = new ShowAllReviewsView(viewManagerModel,
                    showAllReviewsController, showAllReviewsViewModel, writeReviewsController, writeReviewsViewModel);
            SearchView searchView = new SearchView(searchController, searchViewModel, viewManagerModel,
                    showAllReviewsController, showAllReviewsViewModel);
            searchViewList.add(searchView);
            searchViewList.add(showAllReviewsView);
            searchViewList.add(writeReviewsView);
            return searchViewList;
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open review data file.");
        }
        return null;
    }

    private static WriteReviewsController createWriteReviewUseCase(ViewManagerModel viewManagerModel,
                                                                   WriteReviewsViewModel writeReviewsViewModel,
                                                                   WriteReviewsDataAccessInterface
                                                                           reviewsDataAccessObject) throws IOException {
        WriteReviewsOutputBoundary writeReviewsPresenter = new WriteReviewsPresenter(viewManagerModel,
                writeReviewsViewModel);

        ReviewFactory reviewFactory = new CommonReviewFactory();

        WriteReviewsInteractor writeReviewsInteractor = new WriteReviewsInteractor(
                reviewsDataAccessObject, reviewFactory, writeReviewsPresenter);

        return new WriteReviewsController(writeReviewsInteractor);
    }

    private static ShowAllReviewsController createShowAllReviewUseCase(ViewManagerModel viewManagerModel,
                                                                       ShowAllReviewsViewModel showAllReviewsViewModel,
                                                                       ShowAllReviewsDataAccessInterface
                                                                               reviewsDataAccessObject)
            throws IOException {
        ShowAllReviewsOutputBoundary showAllReviewsPresenter = new ShowAllReviewsPresenter(viewManagerModel,
                showAllReviewsViewModel);

        ShowAllReviewsInteractor showAllReviewsInteractor = new ShowAllReviewsInteractor(
                reviewsDataAccessObject, showAllReviewsPresenter);

        return new ShowAllReviewsController(showAllReviewsInteractor);
    }
}

