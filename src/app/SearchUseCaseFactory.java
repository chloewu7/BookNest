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
import user_manage.service.collection_management.add_book.AddBookDataAccessInterface;
import user_manage.service.collection_management.add_book.AddBookInputBoundary;
import user_manage.service.collection_management.add_book.AddBookInteractor;
import user_manage.service.collection_management.add_book.AddBookOutputBoundary;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookController;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookPresenter;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookViewModel;
import user_manage.service.collection_management.create_list.CreateListDataAccessInterface;
import user_manage.service.collection_management.create_list.CreateListInputBoundary;
import user_manage.service.collection_management.create_list.CreateListInteractor;
import user_manage.service.collection_management.create_list.CreateListOutputBoundary;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListController;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListPresenter;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListViewModel;
import user_manage.service.collection_management.show_all_lists.ShowAllListsDataAccessInterface;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInputBoundary;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInteractor;
import user_manage.service.collection_management.show_all_lists.ShowAllListsOutputBoundary;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsController;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsPresenter;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.history.read_history.ReadingHistoryDataAccessInterface;
import user_manage.service.history.read_history.ReadingHistoryInputBoundary;
import user_manage.service.history.read_history.ReadingHistoryInteractor;
import user_manage.service.history.read_history.ReadingHistoryOutputBoundary;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryController;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryPresenter;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;
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
import view.ShowAllListsView;
import view.ShowAllReviewsView;
import view.UserCenter.UserCenterViewModel;
import view.WriteReviewsView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchUseCaseFactory {

    private SearchUseCaseFactory(){}


    public static SearchController createSearchController(SearchViewModel searchViewModel, SearchDataAccessObject
                                                          searchDataAccessObject) {
        SearchOutputBoundary searchPresenter = new SearchPresenter(searchViewModel);
        ResponseFactory responseFactory = new NewResponseFactory();
        SearchInteractor searchInteractor = new SearchInteractor(searchDataAccessObject, searchPresenter, responseFactory);
        SearchController searchController = new SearchController(searchInteractor);

        return searchController;
    }

    public static List<JPanel> create(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel,
                                      SearchDataAccessObject  searchDataAccessObject,
                                      ShowAllReviewsViewModel showAllReviewsViewModel,
                                      WriteReviewsViewModel writeReviewsViewModel,
                                      FileReviewDataAccessObject reviewDataAccessObject,
                                      AddBookViewModel addBookViewModel, AddBookDataAccessInterface
                                              addBookDataAccessObject, ShowAllListsViewModel showAllListsViewModel,
                                      UserCenterViewModel userCenterViewModel, ReadingHistoryViewModel readingHistoryViewModel,
                                      ReadingHistoryDataAccessInterface readingHistoryDAO){

        SearchController searchController = createSearchController(searchViewModel, searchDataAccessObject);
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

            AddBookController addBookController = createAddBookController(viewManagerModel, addBookViewModel,
                    addBookDataAccessObject);

            ReadingHistoryController readingHistoryController =createReadingHistoryController(viewManagerModel,readingHistoryViewModel,
                    readingHistoryDAO);


            SearchView searchView = new SearchView(searchController, searchViewModel, viewManagerModel,
                    showAllReviewsController, showAllReviewsViewModel, addBookController, addBookViewModel,
                    showAllListsViewModel, userCenterViewModel,readingHistoryViewModel,readingHistoryController);

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

    private static AddBookController createAddBookController(ViewManagerModel viewManagerModel,
                                                             AddBookViewModel addBookViewModel,
                                                             AddBookDataAccessInterface addBookDataAccessObject){
        AddBookOutputBoundary addBookOutputPresenter = new AddBookPresenter(addBookViewModel);

        AddBookInputBoundary addBookInteractor = new AddBookInteractor(addBookDataAccessObject, addBookOutputPresenter);

        AddBookController addBookController = new AddBookController(addBookInteractor);

        return addBookController;
    }

    private static ReadingHistoryController createReadingHistoryController(ViewManagerModel viewManagerModel,
                                                                           ReadingHistoryViewModel readingHistoryViewModel,
                                                                           ReadingHistoryDataAccessInterface readingHistoryDAO){
        ReadingHistoryOutputBoundary presenter = new ReadingHistoryPresenter(readingHistoryViewModel,viewManagerModel);
        ReadingHistoryInputBoundary interactor = new ReadingHistoryInteractor(presenter,readingHistoryDAO);
        ReadingHistoryController controller = new ReadingHistoryController(interactor);

        return controller;
    }


}

